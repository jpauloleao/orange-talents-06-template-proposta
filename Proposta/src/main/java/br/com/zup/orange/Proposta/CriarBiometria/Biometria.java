package br.com.zup.orange.Proposta.CriarBiometria;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.orange.Proposta.AssociaCartao.Cartao;

@Entity
public class Biometria {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
	    @NotNull
	    private byte[] digital;
	    
	    private LocalDateTime dataCriacao;
	    
	    @NotNull
	    @ManyToOne
	    @Valid
	    private Cartao cartao;

	    @Deprecated
	    public Biometria() {
	    }

	    public Biometria(@NotBlank String digital, @NotBlank @Valid Cartao cartao) {
	        this.digital = Base64.getEncoder().encode(digital.getBytes());
	        this.cartao = cartao;
	        this.dataCriacao = LocalDateTime.now();
	    }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(digital);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Biometria other = (Biometria) obj;
			if (!Arrays.equals(digital, other.digital))
				return false;
			return true;
		}
		
		public Long getId() {
			return id;
		}

		@Override
		public String toString() {
			return "Biometria [digital=" + Arrays.toString(digital) + ", dataCriacao=" + dataCriacao + "]";
		}
		
		

}
