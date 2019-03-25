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
public class Referencia {
    
    private int idReferencia;
    private String descricaoReferencia;
    private String linkReferencia;
    private String statusReferencia;
    private Documento documento;
    private Usuario usuario;

    public Referencia() {
    }

    public int getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(int idReferencia) {
        this.idReferencia = idReferencia;
    }

    public String getDescricaoReferencia() {
        return descricaoReferencia;
    }

    public void setDescricaoReferencia(String descricaoReferencia) {
        this.descricaoReferencia = descricaoReferencia;
    }

    public String getLinkReferencia() {
        return linkReferencia;
    }

    public void setLinkReferencia(String linkReferencia) {
        this.linkReferencia = linkReferencia;
    }

    public String getStatusReferencia() {
        return statusReferencia;
    }

    public void setStatusReferencia(String statusReferencia) {
        this.statusReferencia = statusReferencia;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
