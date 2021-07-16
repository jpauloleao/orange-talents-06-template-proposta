package br.com.zup.orange.Proposta.NovaProposta;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


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
	private String endereco;
	
	@NotNull
	@Positive
	private BigDecimal salario;

	public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String endereco,
			@NotNull @NotEmpty @Positive BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}
	
	
	
}
