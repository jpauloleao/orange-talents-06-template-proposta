package br.com.zup.orange.Proposta.AssociaCartao;


import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.orange.Proposta.AvisoViagemCartao.AvisoViagemRequest;
import br.com.zup.orange.Proposta.BloqueioCartao.BloqueioCartaoRequest;
import br.com.zup.orange.Proposta.NovaProposta.SolicitacaoAnaliseRequest;

@FeignClient(url = "${clienteCartao.url}", name = "cartoes")
public interface ClienteCartao {
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	CartaoRequest solicitaNumeroCartao(@RequestBody SolicitacaoAnaliseRequest request);

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/bloqueios", consumes = "application/json")
	String notificaBloqueioFeign(@PathVariable String id, BloqueioCartaoRequest request);
	
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/avisos",consumes = "application/json")
	String notificaViagemFeign(@PathVariable String id, AvisoViagemRequest request);
}
