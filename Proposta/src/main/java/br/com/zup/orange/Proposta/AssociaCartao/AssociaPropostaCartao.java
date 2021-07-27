package br.com.zup.orange.Proposta.AssociaCartao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import br.com.zup.orange.Proposta.Integracao.ClienteCartaoFeign;
import br.com.zup.orange.Proposta.NovaProposta.EstadoPropostaEnum;
import br.com.zup.orange.Proposta.NovaProposta.Proposta;
import br.com.zup.orange.Proposta.NovaProposta.PropostaRepository;
import br.com.zup.orange.Proposta.NovaProposta.SolicitacaoAnaliseRequest;
import io.opentracing.Span;
import io.opentracing.Tracer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AssociaPropostaCartao {

	@Autowired
	private ClienteCartaoFeign clienteCartaoFeign;
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	private final Tracer tracer;

	public AssociaPropostaCartao(Tracer tracer) {
		this.tracer = tracer;
	}
	
	private static final Logger log = LoggerFactory.getLogger(AssociaPropostaCartao.class);

	@Scheduled(fixedDelayString = "${periodicidade.associa-proposta-cartao}")
	public void associaCartaoProposta() {
		List<Proposta> propostas = propostaRepository.findByEstadoPropostaAndCartaoNumero(EstadoPropostaEnum.ELEGIVEL, null);
		
		log.info("Propostas para serem avaliadas: {}",propostas.size());
		
		Span activeSpan = tracer.activeSpan();
		activeSpan.setTag("user.email", "joao@zup.com.br");
		activeSpan.log("Meu log");
		
		for (Proposta proposta : propostas) {
			CartaoRequest cartaoRequest = clienteCartaoFeign.solicitaNumeroCartao(new SolicitacaoAnaliseRequest(proposta));
			System.out.println(cartaoRequest.toString());
			Assert.notNull(cartaoRequest, "A API cliente não devolveu o cartão");
			
			proposta.associaCartao(cartaoRequest);
			
			propostaRepository.save(proposta);
			
		}
	}	
}
