package br.com.zup.orange.Proposta.NovaProposta;

import javax.validation.Valid;

public class PropostaResponse {

	private String nome;
	
	private String email;
	
	private EstadoPropostaEnum estadoProposta;

	public PropostaResponse(@Valid Proposta proposta) {
		super();
		this.nome = proposta.getNome();
		this.email = proposta.getEmail();
		this.estadoProposta = proposta.getEstadoProposta();
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public EstadoPropostaEnum getEstadoProposta() {
		return estadoProposta;
	}
	
}
