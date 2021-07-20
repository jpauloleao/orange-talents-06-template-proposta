package br.com.zup.orange.Proposta.NovaProposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.orange.Proposta.Validacoes.UniqueValue;
import br.com.zup.orange.Proposta.Validacoes.ValidaDocumento;



public class NovaPropostaRequest {

	
	@NotBlank
	@ValidaDocumento(message = "Documento Invalido")
	@UniqueValue(domainClass = Proposta.class, fieldName = "documento")
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
	

	public NovaPropostaRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Proposta toModel() {
		return new Proposta(documento, email, nome, endereco, salario);
	}
	
}
