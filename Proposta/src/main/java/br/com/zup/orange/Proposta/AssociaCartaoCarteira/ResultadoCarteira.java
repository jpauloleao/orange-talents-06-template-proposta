package br.com.zup.orange.Proposta.AssociaCartaoCarteira;

import javax.validation.constraints.NotBlank;

public class ResultadoCarteira {
	
	@NotBlank
	private String resultado;

	@NotBlank
	private String id;
	
	public ResultadoCarteira(@NotBlank String resultado, @NotBlank String id) {
		super();
		this.resultado = resultado;
		this.id = id;
	}

	public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
