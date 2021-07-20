package br.com.zup.orange.Proposta.NovaProposta;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ResultadoAnaliseDto {
	
	private String documento;
	
	private String nome;
	
	private String idProposta;
	
	@Enumerated(EnumType.STRING)
	private EstadoAnaliseEnum estadoAnalise;

	public ResultadoAnaliseDto(String documento, String nome, String idProposta, EstadoAnaliseEnum resultadoSolicitacao) {
	        this.documento = documento;
	        this.nome = nome;
	        this.idProposta = idProposta;
	        this.estadoAnalise = resultadoSolicitacao;
	}


	public EstadoAnaliseEnum getEstadoAnalise() {
		return estadoAnalise;
	}

}
