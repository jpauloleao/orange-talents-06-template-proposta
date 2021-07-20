package br.com.zup.orange.Proposta.NovaProposta;

public enum EstadoAnaliseEnum {

	COM_RESTRICAO(EstadoPropostaEnum.NAO_ELEGIVEL),SEM_RESTRICAO(EstadoPropostaEnum.ELEGIVEL);
	
	private EstadoPropostaEnum estadoProposta;

	private EstadoAnaliseEnum(EstadoPropostaEnum estadoProposta) {
		this.estadoProposta = estadoProposta;
	}
	
	public EstadoPropostaEnum getEstadoProposta() {
		return estadoProposta;
	}
	
}