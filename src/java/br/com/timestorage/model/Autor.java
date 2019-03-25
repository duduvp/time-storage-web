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
public class Autor {

    private int idAutor;
    private String nomeAutor;
    private Date dataNascimentoAutor;
    private Date dataFalecimentoAutor;
    private int statusAutor;    
    private String descricaoStatus;
    private String dataNascimentoFormatada;
    private String dataFalecimentoFormatada;

    public Autor() {
    }

    public Autor(String nomeAutor, int statusAutor) {
        this.nomeAutor = nomeAutor;
        this.statusAutor = statusAutor;
    } 

    public Autor(int idAutor) {
        this.idAutor = idAutor;
    }   
    
    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Date getDataNascimentoAutor() {
        return dataNascimentoAutor;
    }

    public void setDataNascimentoAutor(Date dataNascimentoAutor) throws ParseException {
        this.dataNascimentoAutor = dataNascimentoAutor;
        this.dataNascimentoFormatada = Funcoes.FormartDate(this.dataNascimentoAutor, "dd/MM/yyyy");
    }

    public Date getDataFalecimentoAutor() {
        return dataFalecimentoAutor;
    }

    public void setDataFalecimentoAutor(Date dataFalecimentoAutor) throws ParseException {
        this.dataFalecimentoAutor = dataFalecimentoAutor;
        this.dataFalecimentoFormatada = Funcoes.FormartDate(this.dataFalecimentoAutor, "dd/MM/yyyy");
    }

    public int getStatusAutor() {
        return statusAutor;
    }

    public void setStatusAutor(int statusAutor) {
        this.statusAutor = statusAutor;
        if (this.statusAutor == 0)
            this.descricaoStatus = "Ativo";
        else
            this.descricaoStatus = "Inativo";         
    }

    public String getDescricaoStatus() {
        return descricaoStatus;
    }

    public String getDataNascimentoFormatada() {
        return dataNascimentoFormatada;
    }

    public String getDataFalecimentoFormatada() {
        return dataFalecimentoFormatada;
    }

}
