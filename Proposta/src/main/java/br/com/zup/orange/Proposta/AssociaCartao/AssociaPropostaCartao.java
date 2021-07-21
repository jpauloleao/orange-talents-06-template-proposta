package br.com.zup.orange.Proposta.AssociaCartao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import br.com.zup.orange.Proposta.NovaProposta.EstadoPropostaEnum;
import br.com.zup.orange.Proposta.NovaProposta.Proposta;
import br.com.zup.orange.Proposta.NovaProposta.PropostaRepository;
import br.com.zup.orange.Proposta.NovaProposta.SolicitacaoAnaliseRequest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AssociaPropostaCartao {

	@Autowired
	private ClienteCartao clienteCartao;
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	private static final Logger log = LoggerFactory.getLogger(AssociaPropostaCartao.class);

	@Scheduled(fixedDelayString = "${periodicidade.associa-proposta-cartao}")
	public void associaCartaoProposta() {
		List<Proposta> propostas = propostaRepository.findByEstadoPropostaAndCartaoNumero(EstadoPropostaEnum.ELEGIVEL, null);
		
		log.info("Propostas para serem avaliadas: {}",propostas.size());
		
		for (Proposta proposta : propostas) {
			CartaoRequest cartaoRequest = clienteCartao.solicitaNumeroCartao(new SolicitacaoAnaliseRequest(proposta));
			
			Assert.isNull(cartaoRequest, "A API cliente não devolveu o cartão");
			
			proposta.associaCartao(cartaoRequest);
			
			propostaRepository.save(proposta);
		}
	}	
}
