/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.model;

import br.com.timestorage.util.Funcoes;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author mateus
 */
public class Comentario {
    
    private int idComentario;
    private String textoComentario;
    private int statusComentario;
    private Date dataComentario;
    private Usuario usuario;
    private Documento documento;
    private String dataComentarioFormatada;    

    public Comentario() {
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

    public int getStatusComentario() {
        return statusComentario;
    }

    public void setStatusComentario(int statusComentario) {
        this.statusComentario = statusComentario;
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

    public Date getDataComentario() {
        return dataComentario;
    }

    public void setDataComentario(Date dataComentario) throws ParseException {
        this.dataComentario = dataComentario;
        this.dataComentarioFormatada = Funcoes.FormartDate(this.dataComentario, "dd/MM/yyyy");
    }

    public String getDataComentarioFormatada() {
        return dataComentarioFormatada;
    }
    
}
