package br.com.zup.orange.Proposta.AssociaCartao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.orange.Proposta.CriarBiometria.Biometria;
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

	
	@OneToMany(mappedBy = "cartao")
	private Set<Biometria> biometrias = new HashSet<>();

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
	
	public void addBiometria(@Valid Biometria biometria) {
        this.biometrias.add(biometria);
    }

	@Override
	public String toString() {
		return "Cartao [numero=" + numero + ", proposta=" + proposta + ", biometrias=" + biometrias + "]";
	}
	
	
}
