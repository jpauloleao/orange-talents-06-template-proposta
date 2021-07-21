package br.com.zup.orange.Proposta.NovaProposta;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.zup.orange.Proposta.AssociaCartao.Cartao;
import br.com.zup.orange.Proposta.AssociaCartao.CartaoRequest;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String documento;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String endereco;

	@NotNull
	@Positive
	private BigDecimal salario;

	@Enumerated(EnumType.STRING)
	private EstadoPropostaEnum estadoProposta;

	@OneToOne(mappedBy = "proposta", cascade = CascadeType.MERGE)
	private Cartao cartao;

	public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	@Deprecated
	public Proposta() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "Proposta [email=" + email + ", nome=" + nome + ", estadoProposta=" + estadoProposta + "]";
	}

	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getEndereco() {
		return endereco;
	}

	public EstadoPropostaEnum getEstadoProposta() {
		return estadoProposta;
	}

	public void atualizaStatusProposta(EstadoPropostaEnum estadoProposta) {
		this.estadoProposta = estadoProposta;
	}

	
	public void associaCartao(CartaoRequest cartao) {
		this.cartao = new Cartao(this, cartao.getId());
	}

}
