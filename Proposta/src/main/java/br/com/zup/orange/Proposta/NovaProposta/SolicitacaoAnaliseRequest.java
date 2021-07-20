package br.com.zup.orange.Proposta.NovaProposta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SolicitacaoAnaliseRequest {

    @NotBlank
    private String documento;
    
    @NotBlank
    private String nome;
    
    @NotNull
    private Long idProposta;


    public SolicitacaoAnaliseRequest(@Valid Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }


    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
