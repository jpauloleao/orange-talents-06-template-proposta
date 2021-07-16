package br.com.zup.orange.Proposta.NovaProposta;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/proposta")
@RestController
public class PropostaController {

	@PersistenceContext
	EntityManager em;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> novaProposta(@RequestBody @Valid NovaPropostaRequest propostaRequest, UriComponentsBuilder builder){
		Proposta proposta = propostaRequest.toModel();
		
		em.persist(proposta); 
		
		return ResponseEntity.created(builder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri()).build();
	}
	
}
