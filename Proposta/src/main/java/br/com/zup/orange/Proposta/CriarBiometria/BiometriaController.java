package br.com.zup.orange.Proposta.CriarBiometria;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.zup.orange.Proposta.AssociaCartao.CartaoRepository;

@RestController
@RequestMapping("/cartao")
public class BiometriaController {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private BiometriaRepository biometriaRepository;

	@PostMapping(value = "/{id}/biometria")
	@Transactional
	public ResponseEntity<?> criaBiometriaCartao(@PathVariable("id") Long id,
			@RequestBody @Valid BiometriaRequest biometria, UriComponentsBuilder builder) {

		Optional<Cartao> cartao = cartaoRepository.findById(id);

		if (cartao.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
		}

		Biometria bio = biometria.toModel(cartao.get());
		biometriaRepository.save(bio);

		cartao.get().addBiometria(bio);
		cartaoRepository.save(cartao.get());

		return ResponseEntity.created(builder.path("/biometria/{id}").buildAndExpand(bio.getId()).toUri()).build();

	}

}
