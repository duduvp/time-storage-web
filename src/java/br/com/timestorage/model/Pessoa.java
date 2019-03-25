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
public class Pessoa {

    private int idPessoa;
    private String nomePessoa;
    private String sobrenomePessoa;
    private String emailPessoa;
    private String telefonePessoa;
    private Date dataNascimentoPessoa;
    private Date dataCadastroPessoa;
    private int statusPessoa;
    private String descricaoStatus;
    private String dataNascimentoFormatada;
    private String dataCadastroFormatada;

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getSobrenomePessoa() {
        return sobrenomePessoa;
    }

    public void setSobrenomePessoa(String sobrenomePessoa) {
        this.sobrenomePessoa = sobrenomePessoa;
    }

    public String getEmailPessoa() {
        return emailPessoa;
    }

    public void setEmailPessoa(String emailPessoa) {
        this.emailPessoa = emailPessoa;
    }

    public String getTelefonePessoa() {
        return telefonePessoa;
    }

    public void setTelefonePessoa(String telefonePessoa) {
        this.telefonePessoa = telefonePessoa;
    }

    public Date getDataNascimentoPessoa() {
        return dataNascimentoPessoa;
    }

    public void setDataNascimentoPessoa(Date dataNascimentoPessoa) throws ParseException {
        this.dataNascimentoPessoa = dataNascimentoPessoa;
        setDataNascimentoFormatada(Funcoes.FormartDate(dataNascimentoPessoa, "dd/MM/yyyy"));
    }

    public Date getDataCadastroPessoa() {
        return dataCadastroPessoa;
    }

    public void setDataCadastroPessoa(Date dataCadastroPessoa) throws ParseException {
        this.dataCadastroPessoa = dataCadastroPessoa;
        setDataCadastroFormatada(Funcoes.FormartDate(dataCadastroPessoa, "dd/MM/yyyy"));
    }

    public int getStatusPessoa() {
        return statusPessoa;
    }

    public void setStatusPessoa(int statusPessoa) {
        this.statusPessoa = statusPessoa;
        if (this.statusPessoa == 0) {
            this.descricaoStatus = "Ativo";
        } else {
            this.descricaoStatus = "Inativo";
        }
    }

    public String getDescricaoStatus() {
        return descricaoStatus;
    }

    public String getDataNascimentoFormatada() {
        return dataNascimentoFormatada;
    }

    public void setDataNascimentoFormatada(String dataNascimentoFormatada) {
        this.dataNascimentoFormatada = dataNascimentoFormatada;
    }

    public String getDataCadastroFormatada() {
        return dataCadastroFormatada;
    }

    public void setDataCadastroFormatada(String dataCadastroFormatada) {
        this.dataCadastroFormatada = dataCadastroFormatada;
    }

}
