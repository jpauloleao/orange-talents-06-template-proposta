package br.com.zup.orange.Proposta.AssociaCartao;

import javax.validation.constraints.NotBlank;

public class CartaoRequest {

	@NotBlank
	private String id;

	@NotBlank
	private String idProposta;

	public CartaoRequest() {
		// TODO Auto-generated constructor stub
	}


	public String getId() {
		return id;
	}

	public String getIdProposta() {
		return idProposta;
	}
	

}
