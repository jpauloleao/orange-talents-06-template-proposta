package br.com.zup.orange.Proposta.AssociaCartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.orange.Proposta.NovaProposta.SolicitacaoAnaliseRequest;


@FeignClient(url = "${clienteCartao.url}", name = "cartao")
public interface ClienteCartao {
	@RequestMapping(method = RequestMethod.POST, value = "/api/cartoes")
	CartaoRequest solicitaNumeroCartao(@RequestBody SolicitacaoAnaliseRequest request);
}
