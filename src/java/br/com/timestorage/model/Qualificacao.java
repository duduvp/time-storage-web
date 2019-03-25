/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.model;

/**
 *
 * @author mateus
 */
public class Qualificacao {
    
    private int idQualificacao;
    private int valorQualificacao;
    private String observacaoQualificacao;
    private Usuario usuario;
    private Documento documento;

    public Qualificacao() {
    }

    public int getIdQualificacao() {
        return idQualificacao;
    }

    public void setIdQualificacao(int idQualificacao) {
        this.idQualificacao = idQualificacao;
    }

    public int getValorQualificacao() {
        return valorQualificacao;
    }

    public void setValorQualificacao(int valorQualificacao) {
        this.valorQualificacao = valorQualificacao;
    }

    public String getObservacaoQualificacao() {
        return observacaoQualificacao;
    }

    public void setObservacaoQualificacao(String observacaoQualificacao) {
        this.observacaoQualificacao = observacaoQualificacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
    
}
