package br.com.zup.orange.Proposta.AssociaCartaoCarteira;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.orange.Proposta.AssociaCartao.Cartao;

@Entity
public class CarteiraDigital {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Email
    @NotBlank
    private String email;

	@Enumerated(EnumType.STRING)
	@NotNull
	private TiposCarteira carteira; 
	
	@ManyToOne
	@NotNull
    private Cartao cartao;

	public CarteiraDigital(@Email @NotBlank String email, @NotNull TiposCarteira carteira, @NotNull Cartao cartao) {
		super();
		this.email = email;
		this.carteira = carteira;
		this.cartao = cartao;
	}
	
	@Deprecated
	public CarteiraDigital() {
		// TODO Auto-generated constructor stub
	}
	
	public TiposCarteira getCarteira() {
		return carteira;
	}
	
	public Long getId() {
		return id;
	}
	
}
