package br.com.zup.orange.Proposta.AvisoViagemCartao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.orange.Proposta.AssociaCartao.Cartao;
import br.com.zup.orange.Proposta.AssociaCartao.ClienteCartao;
import feign.FeignException.FeignClientException;

@RestController
@RequestMapping(value = "/cartao")
public class AvisoViagemController {

	@PersistenceContext
	EntityManager em;

	@Autowired
	ClienteCartao clienteCartao;

	@Transactional
	@PostMapping("/{id}/viagem")
	public ResponseEntity<?> notificarViagem(@PathVariable Long id,
			@RequestBody @Valid AvisoViagemRequest avisoViagemRequest, HttpServletRequest request) {

		Cartao cartao = em.find(Cartao.class, id);
		@NotBlank
		String ip = request.getLocalAddr();
		@NotBlank
		String userAgent = request.getHeader(HttpHeaders.USER_AGENT);

		if (cartao == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão Inválido");
		}

		AvisoViagem avisoViagem = avisoViagemRequest.toModel(cartao, ip, userAgent);

		String resultado = clienteCartao.notificaViagemFeign(cartao.getNumero(), avisoViagemRequest);
						
		extracted(avisoViagem, resultado);
		
		return ResponseEntity.status(HttpStatus.OK).body("Cartão Notificado Sobre Viagem");
	}

	private void extracted(@Valid AvisoViagem avisoViagem,@NotBlank String resultado) {
		try {
			if(resultado == "CRIADO") {
				em.persist(avisoViagem);
			}
			
		} catch (FeignClientException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possivel bloquear cartão", e);
		}
	}
}
