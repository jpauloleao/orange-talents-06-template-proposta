package br.com.zup.orange.Proposta.BloqueioCartao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.orange.Proposta.AssociaCartao.Cartao;

@Entity
public class BloqueiaCartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDateTime instanteBloqueio;

	@NotBlank
	private String ip;

	@NotBlank
	private String userAgent;

	@NotNull
	@OneToOne
	private Cartao cartao;

	public BloqueiaCartao(@NotBlank String ip, @NotBlank String userAgent,
			@NotNull Cartao cartao) {
		super();
		this.ip = ip;
		this.userAgent = userAgent;
		this.cartao = cartao;
		this.instanteBloqueio = LocalDateTime.now();
	}
	
	@Deprecated
	public BloqueiaCartao() {
		// TODO Auto-generated constructor stub
	}

}
