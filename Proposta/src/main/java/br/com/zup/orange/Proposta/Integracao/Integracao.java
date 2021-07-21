package br.com.zup.orange.Proposta.Integracao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zup.orange.Proposta.NovaProposta.SolicitacaoAnaliseRequest;
import br.com.zup.orange.Proposta.AssociaCartao.CartaoRequest;
import br.com.zup.orange.Proposta.NovaProposta.ResultadoAnaliseDto;

@FeignClient(url = "${integracao.url}", name = "integracao")
public interface Integracao {
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao", consumes = "application/json")
    ResultadoAnaliseDto solicitaAnalise(@RequestBody SolicitacaoAnaliseRequest request);

}
