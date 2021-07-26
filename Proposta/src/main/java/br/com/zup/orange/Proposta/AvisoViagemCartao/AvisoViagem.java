package br.com.zup.orange.Proposta.AvisoViagemCartao;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.orange.Proposta.AssociaCartao.Cartao;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@NotBlank
	private String destino;
	
	@NotNull
	@Future
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate terminoViagem;
	
	private LocalDateTime avisoViagem;
	
	@NotBlank
	private String ip;
	
	@NotBlank
	private String userAgent;
	
	@ManyToOne
	@NotNull
	private Cartao cartao;

	public AvisoViagem(@NotBlank String destino, @NotNull @Future LocalDate terminoViagem,
			@NotBlank String ip, @NotBlank String userAgent, Cartao cartao) {
		super();
		this.destino = destino;
		this.terminoViagem = terminoViagem;
		this.ip = ip;
		this.userAgent = userAgent;
		this.cartao = cartao;
		this.avisoViagem = LocalDateTime.now();
	}
	
	
}
