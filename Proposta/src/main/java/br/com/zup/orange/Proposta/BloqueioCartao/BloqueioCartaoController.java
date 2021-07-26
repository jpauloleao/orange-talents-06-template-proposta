package br.com.zup.orange.Proposta.BloqueioCartao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.orange.Proposta.AssociaCartao.Cartao;
import br.com.zup.orange.Proposta.AssociaCartao.ClienteCartao;
import feign.FeignException;

@RestController
@RequestMapping("/cartao")
public class BloqueioCartaoController {

	@PersistenceContext
	EntityManager em;

	@Autowired
	ClienteCartao clienteCartao;

	@PostMapping("/{id}/bloqueiaCartao")
	@Transactional
	public ResponseEntity<?> bloquearCartao(@PathVariable Long id, HttpServletRequest request) {

		Cartao cartao = em.find(Cartao.class, id);
		String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
		String ip = request.getRemoteAddr();

		if (cartao == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão Inválido");
		}

		if (cartao.verificaStatusCartao(ip, userAgent)) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Cartão já Bloqueado");
		}

		String response =  apiExternaNotificaBloqueio(cartao.getNumero(), new BloqueioCartaoRequest("API-PROPOSTA"));

		if(response == "BLOQUEADO") {
			cartao.bloqueiaCartao(ip, userAgent);
			em.merge(cartao);	
		}

		return ResponseEntity.status(HttpStatus.OK).body("Cartão Bloqueado");

	}

	private String apiExternaNotificaBloqueio(String identificador, BloqueioCartaoRequest resp) {
		String bloqueioCartaoResponse;
		try {
			bloqueioCartaoResponse = clienteCartao.notificaBloqueio(identificador, resp);
			System.out.println(bloqueioCartaoResponse);
		} catch (FeignException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possivel bloquear cartão", e);
		}

		return bloqueioCartaoResponse;
	}

}
