package br.com.zup.orange.Proposta.CriarBiometria;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.orange.Proposta.AssociaCartao.Cartao;

@RestController
@RequestMapping("/cartao")
public class BiometriaController {

	@PersistenceContext
	EntityManager em;

	@PostMapping(value = "/{id}/biometria")
	@Transactional
	public ResponseEntity<?> criaBiometriaCartao(@PathVariable("id") Long id,
			@RequestBody @Valid BiometriaRequest biometria, UriComponentsBuilder builder) {

		Cartao cartao = em.find(Cartao.class, id);

		if (cartao == null) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
		}

		Biometria bio = biometria.toModel(cartao);
		em.persist(bio);

		cartao.addBiometria(bio);
		em.merge(cartao);

		return ResponseEntity.created(builder.path("/biometria/{id}").buildAndExpand(bio.getId()).toUri()).build();

	}

}
