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
public class Editora {
    
    private int idEditora;
    private String descricaoEditora;
    private int statusEditora;    
    private String descricaoStatus;

    public Editora() {
    }

    public Editora(String descricaoEditora, int statusEditora) {
        this.descricaoEditora = descricaoEditora;
        this.statusEditora = statusEditora;
    }    

    public Editora(int idEditora) {
        this.idEditora = idEditora;
    }

    public int getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(int idEditora) {
        this.idEditora = idEditora;
    }

    public String getDescricaoEditora() {
        return descricaoEditora;
    }

    public void setDescricaoEditora(String descricaoEditora) {
        this.descricaoEditora = descricaoEditora;
    }

    public int getStatusEditora() {
        return statusEditora;
    }

    public void setStatusEditora(int statusEditora) {
        this.statusEditora = statusEditora;
        if (this.statusEditora == 0)
            this.descricaoStatus = "Ativo";
        else
            this.descricaoStatus = "Inativo";        
    }
    
    public String getDescricaoStatus() {
        return descricaoStatus;
    }   
    
}
