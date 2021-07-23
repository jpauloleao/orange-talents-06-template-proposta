package br.com.zup.orange.Proposta.BloqueioCartao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.orange.Proposta.AssociaCartao.Cartao;

@RestController
@RequestMapping("/cartao")
public class BloqueioCartaoController {

	@PersistenceContext
	EntityManager em;

	@PostMapping("/{id}/bloqueiaCartao")
	@Transactional
	public ResponseEntity<?> block(@PathVariable Long id, HttpServletRequest request) {

		Cartao cartao = em.find(Cartao.class, id);
		String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
		String ip = request.getRemoteAddr();
		
		if (cartao == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão Inválido");
		}

		if (cartao.verificaStatusCartao(ip, userAgent)) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Cartão já Bloqueado");
		}

		cartao.bloqueiaCartao(ip, userAgent);
		em.merge(cartao);
		
		return ResponseEntity.ok().body("Bloqueio Realizado");
	}
}
