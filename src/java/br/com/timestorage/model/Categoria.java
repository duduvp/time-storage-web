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
public class Categoria {
    
    private int idCategoria;
    private String descricaoCategoria;
    private int statusCategoria;    
    private String descricaoStatus;

    public Categoria() {
    }

    public Categoria(String descricaoCategoria, int statusCategoria) {
        this.descricaoCategoria = descricaoCategoria;
        this.statusCategoria = statusCategoria;
    }  

    public Categoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }

    public int getStatusCategoria() {
        return statusCategoria;
    }

    public void setStatusCategoria(int statusCategoria) {
        this.statusCategoria = statusCategoria;
        if (this.statusCategoria == 0)
            this.descricaoStatus = "Ativo";
        else
            this.descricaoStatus = "Inativo";           
    }

    public String getDescricaoStatus() {
        return descricaoStatus;
    }
    
}
