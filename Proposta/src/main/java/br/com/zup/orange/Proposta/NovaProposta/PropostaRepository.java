package br.com.zup.orange.Proposta.NovaProposta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long>{
	
	List<Proposta> findByEstadoPropostaAndCartaoNumero(EstadoPropostaEnum estado, String numero);

}
