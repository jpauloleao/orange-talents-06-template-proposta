package br.com.zup.orange.Proposta.AssociaCartaoCarteira;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.orange.Proposta.AssociaCartao.Cartao;

public class CarteiraDigitalRequest {

	@Email
	@NotBlank
	private String email;

	@Enumerated(EnumType.STRING)
	@NotNull
	private TiposCarteira carteira;

	public CarteiraDigitalRequest(@Email @NotBlank String email, @NotNull TiposCarteira carteira) {
		super();
		this.email = email;
		this.carteira = carteira;
	}

	public TiposCarteira getCarteira() {
		return carteira;
	}

	public CarteiraDigital toModel(@Valid Cartao cartao) { // TODO Auto-generated
		return new CarteiraDigital(email, carteira, cartao);
	}

}
