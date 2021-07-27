package br.com.zup.orange.Proposta.NovaProposta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.orange.Proposta.Integracao.Integracao;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RequestMapping("/proposta")
@RestController
public class PropostaController {

	@PersistenceContext
	EntityManager em;

	@Autowired
	Integracao integracao;

	private static final Logger log = LoggerFactory.getLogger(PropostaController.class);


	@PostMapping
	@Transactional
	public ResponseEntity<?> novaProposta(@RequestBody @Valid NovaPropostaRequest propostaRequest,
			UriComponentsBuilder builder) {


		Proposta proposta = propostaRequest.toModel();
		em.persist(proposta);

		ResultadoAnaliseDto propostaAnalisada = integracao.solicitaAnalise(new SolicitacaoAnaliseRequest(proposta));

		// Atualiza proposta com Status Proposta definido
		proposta.atualizaStatusProposta(propostaAnalisada.getEstadoAnalise().getEstadoProposta());
		em.merge(proposta);

		log.info("Proposta cadastrada: {} ", proposta.toString());

		return ResponseEntity.created(builder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri()).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> acompanhamentoProposta(@PathVariable Long id) {
		Proposta proposta = em.find(Proposta.class, id);

		if (proposta == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(new PropostaResponse(proposta));
	}

}
