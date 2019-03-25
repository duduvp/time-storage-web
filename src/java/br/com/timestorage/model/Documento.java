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
public class Documento {
    
    private int idDocumento;
    private String tituloDocumento;
    private String sinopseDocumento;
    private Date dataPublicacaoDocumento;
    private int statusDocumento;
    private String descricaoStatus;
    private Date dataInclusaoDocumento;
    private Usuario usuario;
    private Categoria categoria;
    private Editora editora;
    private String dataPublicacaoFormatada;
    private String dataInclusaoFormatada;

    public Documento() {
    }
    
    public Documento(String tituloDocumento, Date dataPublicacaoDocumento, int statusDocumento) {
        this.tituloDocumento = tituloDocumento;
        this.dataPublicacaoDocumento = dataPublicacaoDocumento;
        this.statusDocumento = statusDocumento;
    }    
    
    public Documento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTituloDocumento() {
        return tituloDocumento;
    }

    public void setTituloDocumento(String tituloDocumento) {
        this.tituloDocumento = tituloDocumento;
    }

    public String getSinopseDocumento() {
        return sinopseDocumento;
    }

    public void setSinopseDocumento(String sinopseDocumento) {
        this.sinopseDocumento = sinopseDocumento;
    }

    public Date getDataPublicacaoDocumento() {
        return dataPublicacaoDocumento;
    }

    public void setDataPublicacaoDocumento(Date dataPublicacaoDocumento) throws ParseException {
        this.dataPublicacaoDocumento = dataPublicacaoDocumento;
        this.dataPublicacaoFormatada = Funcoes.FormartDate(this.dataPublicacaoDocumento, "dd/MM/yyyy");
    }

    public int getStatusDocumento() {
        return statusDocumento;
    }

    public void setStatusDocumento(int statusDocumento) {
        this.statusDocumento = statusDocumento;
        if (this.statusDocumento == 0)
            this.descricaoStatus = "Ativo";
        else
            this.descricaoStatus = "Inativo";         
    }

    public Date getDataInclusaoDocumento() {
        return dataInclusaoDocumento;
    }

    public void setDataInclusaoDocumento(Date dataInclusaoDocumento) throws ParseException {
        this.dataInclusaoDocumento = dataInclusaoDocumento;
        this.dataInclusaoFormatada = Funcoes.FormartDate(this.dataInclusaoDocumento, "dd/MM/yyyy");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }     

    public String getDescricaoStatus() {
        return descricaoStatus;
    }

    public String getDataPublicacaoFormatada() {
        return dataPublicacaoFormatada;
    }

    public String getDataInclusaoFormatada() {
        return dataInclusaoFormatada;
    }
    
}
