package br.com.zup.orange.Proposta.AssociaCartao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.zup.orange.Proposta.BloqueioCartao.BloqueiaCartao;
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
	@NotNull
	private Proposta proposta;

	@OneToMany(mappedBy = "cartao")
	private Set<Biometria> biometrias = new HashSet<>();

	@Enumerated(EnumType.STRING)
	private CartaoStatus statusCartao = CartaoStatus.DESBLOQUEADO;

	@OneToOne(mappedBy = "cartao", cascade = CascadeType.MERGE)
	private BloqueiaCartao bloqueiaCartao;

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
	
	public String getNumero() {
		return numero;
	}

	public void addBiometria(@Valid Biometria biometria) {
		this.biometrias.add(biometria);
	}

	@Override
	public String toString() {
		return "Cartao [numero=" + numero + ", proposta=" + proposta + ", biometrias=" + biometrias + "]";
	}
	
	public void bloqueiaCartao(@NotBlank String ip, @NotBlank String userAgent) {
		this.statusCartao = CartaoStatus.BLOQUEADO;
		this.bloqueiaCartao = new BloqueiaCartao(ip, userAgent, this);
	}
	
	public boolean verificaStatusCartao(String ip, String user) {	
		Assert.state(ip != null, "O ip é necessario para o bloqueio do cartão");
        Assert.state(user != null, "O User Agent é necessario para o bloqueio do cartão");
		if(statusCartao == null) {
			this.statusCartao = CartaoStatus.DESBLOQUEADO;
		}
		return this.statusCartao.equals(CartaoStatus.BLOQUEADO);
	}
	
	

	
}
