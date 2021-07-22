package br.com.zup.orange.Proposta.CriarBiometria;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.zup.orange.Proposta.AssociaCartao.Cartao;

public class BiometriaRequest {

	@NotBlank
	private String digital;

	@Deprecated
	public BiometriaRequest() {
	}

	@JsonCreator(mode = Mode.PROPERTIES)
	public BiometriaRequest(@NotBlank String digital) {
		this.digital = digital;
	}

	public Biometria toModel(@Valid Cartao cartao) {
		return new Biometria(digital, cartao);
	}

	public String getDigital() {
		return digital;
	}
}
