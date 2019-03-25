/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.model;

import java.util.Date;

/**
 *
 * @author mateus
 */
public class DocumentoFiltro {
    
    private String tituloDocumento;
    private int statusDocumento;
    private Date dataInicio;
    private Date dataFim;

    public DocumentoFiltro() {
    }    
    
    public DocumentoFiltro(String tituloDocumento, int statusDocumento, Date dataInicio, Date dataFim) {
        this.tituloDocumento = tituloDocumento;
        this.statusDocumento = statusDocumento;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getTituloDocumento() {
        return tituloDocumento;
    }

    public void setTituloDocumento(String tituloDocumento) {
        this.tituloDocumento = tituloDocumento;
    }

    public int getStatusDocumento() {
        return statusDocumento;
    }

    public void setStatusDocumento(int statusDocumento) {
        this.statusDocumento = statusDocumento;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }    
    
}
