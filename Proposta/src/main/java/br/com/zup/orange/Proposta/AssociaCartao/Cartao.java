package br.com.zup.orange.Proposta.AssociaCartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.orange.Proposta.NovaProposta.Proposta;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String numero;
	
	@OneToOne
	private Proposta proposta;
	
	@Deprecated
	public Cartao() {

	}
	
	public Cartao(@NotNull @Valid Proposta proposta, @NotBlank String numero) {
		this.proposta = proposta;
		this.numero = numero;
	}
	
	public Long getId() {
		return id;
	}
}
