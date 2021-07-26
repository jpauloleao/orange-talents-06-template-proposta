package br.com.zup.orange.Proposta.AvisoViagemCartao;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.orange.Proposta.AssociaCartao.Cartao;

public class AvisoViagemRequest {

	@NotBlank
	private String destino;

	@NotNull
	@Future
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate terminoViagem;

	public AvisoViagemRequest(@NotBlank String destino, @NotNull @Future LocalDate terminoViagem) {
		super();
		this.destino = destino;
		this.terminoViagem = terminoViagem;

	}

	public AvisoViagem toModel(@Valid Cartao cartao, @NotBlank String ip, @NotBlank String userAgent) {
		return new AvisoViagem(destino, terminoViagem, ip, userAgent, cartao);
	}

	public LocalDate getTerminoViagem() {
		return terminoViagem;
	}

	public String getDestino() {
		return destino;
	}
}
