/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.dao;

import java.util.List;

/**
 *
 * @author mateus
 */
public interface GenericDAO {
    
    public Boolean inserir(Object object);
    
    public Boolean alterar(Object object);

    public void excluir(int idObject);
    
    public List<Object> listar(Object object);
    
    public Object carregar(int idObject);
}
