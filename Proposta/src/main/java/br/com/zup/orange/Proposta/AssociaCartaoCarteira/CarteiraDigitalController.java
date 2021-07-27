package br.com.zup.orange.Proposta.AssociaCartaoCarteira;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import br.com.zup.orange.Proposta.Integracao.ClienteCartaoFeign;
import feign.FeignException.FeignClientException;

@RestController
@RequestMapping("/cartao")
public class CarteiraDigitalController {

	@PersistenceContext
	EntityManager em;

	@Autowired
	ClienteCartaoFeign clienteCartaoFeign;

	@PostMapping("/{id}/associaCarteira")
	@Transactional
	public ResponseEntity<?> associaCarteiraCartao(@PathVariable Long id,
			@Valid @RequestBody CarteiraDigitalRequest carteiraRequest, UriComponentsBuilder builder) {

		Cartao cartao = em.find(Cartao.class, id);

		// 404
		if (cartao == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão Inválido");
		}

		// 422
		cartao.verificaCartaoAssociadoCarteira(carteiraRequest.getCarteira());

		CarteiraDigital carteira = new CarteiraDigital();
		if (verificaResposta(cartao, carteiraRequest).getResultado().equals("ASSOCIADA")) {
			carteira = carteiraRequest.toModel(cartao);
			em.persist(carteira);	
		}
		
		return ResponseEntity.created(builder.path("/cartao/carteira/{id}").buildAndExpand(carteira.getId()).toUri()).build();
	}

	private ResultadoCarteira verificaResposta(@Valid Cartao cartao, @Valid CarteiraDigitalRequest carteiraRequest) {
		try {
			ResultadoCarteira response = clienteCartaoFeign.associaCarteira(cartao.getNumero(), carteiraRequest);
			System.out.println(response.getResultado());
			return response;

		} catch (FeignClientException exception) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Não foi possivel adicionar a Carteira");
		}
	}

}
