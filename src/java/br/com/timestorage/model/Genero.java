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
public class Genero {
    
    private int idGenero;
    private String descricaoGenero;
    private int statusGenero;    
    private String descricaoStatus;

    public Genero() {
    }

    public Genero(String descricaoGenero, int statusGenero) {
        this.descricaoGenero = descricaoGenero;
        this.statusGenero = statusGenero;
    }
    
    public Genero(int idGenero) {
        this.idGenero = idGenero;
    }    
    
    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getDescricaoGenero() {
        return descricaoGenero;
    }

    public void setDescricaoGenero(String descricaoGenero) {
        this.descricaoGenero = descricaoGenero;
    }

    public int getStatusGenero() {
        return this.statusGenero;
    }

    public void setStatusGenero(int statusGenero) {
        this.statusGenero = statusGenero;
        if (this.statusGenero == 0)
            this.descricaoStatus = "Ativo";
        else
            this.descricaoStatus = "Inativo";        
    }
    
    public String getDescricaoStatus() {
        return descricaoStatus;
    }     
    
}
