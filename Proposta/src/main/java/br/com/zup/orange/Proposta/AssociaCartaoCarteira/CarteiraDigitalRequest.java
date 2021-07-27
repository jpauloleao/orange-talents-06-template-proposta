package br.com.zup.orange.Proposta.AssociaCartaoCarteira;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.zup.orange.Proposta.AssociaCartao.Cartao;

public class CarteiraDigitalRequest {

	@Email
	@NotBlank
	private String email;

	@Enumerated(EnumType.STRING)
	private TiposCarteira carteira;

	@JsonCreator(mode = Mode.PROPERTIES)
	public CarteiraDigitalRequest(@Email @NotBlank String email) {
		super();
		this.email = email;
	}

	public TiposCarteira getCarteira() {
		return carteira;
	}

	public void setCarteira(TiposCarteira carteira) {
		this.carteira = carteira;
	}

	public CarteiraDigital toModel(@Valid Cartao cartao) { // TODO Auto-generated
		return new CarteiraDigital(email, carteira, cartao);
	}

}
