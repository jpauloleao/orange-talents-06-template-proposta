package br.com.zup.orange.Proposta.BloqueioCartao;

import javax.validation.constraints.NotBlank;

public class BloqueioCartaoRequest {
	
	@NotBlank
	private String sistemaResponsavel;

	public BloqueioCartaoRequest(@NotBlank String sistemaResponsavel) {
	        this.sistemaResponsavel = sistemaResponsavel;
	}
	
	@Deprecated
	public BloqueioCartaoRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
	
}
