/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.dao;

import br.com.timestorage.model.AutorGenero;
import br.com.timestorage.model.Genero;
import br.com.timestorage.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mateus
 */
public class AutorGeneroDAOImpl implements GenericDAO{
    
    private Connection conexao;

    public AutorGeneroDAOImpl() throws Exception {
        try {
            this.conexao = ConnectionFactory.getConnection();           
            System.out.println("Banco de dados conectado com sucesso.");
        } catch (Exception ex) {
            throw new Exception("Erro ao estabelecer conexão com o banco de dados\nMensagem: " + ex.getMessage());
        }
    }  
    
    @Override
    public Boolean inserir(Object object) {
        AutorGenero oAutorGenero = (AutorGenero) object;
        PreparedStatement stmt = null;                
        try {
            if (conexao.isClosed())
                conexao = ConnectionFactory.getConnection();            
            stmt = conexao.prepareStatement("insert into autor_genero (id_autor, id_genero) values (?, ?);");
            stmt.setInt(1, oAutorGenero.getAutor().getIdAutor());
            stmt.setInt(2, oAutorGenero.getGenero().getIdGenero());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro na AutorGeneroDAOImpl ao inserir gênero do autor.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na AutorGeneroDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }                           
    }    
    
    public List<AutorGenero> listarAutorGenero(int idAutor) {        
        List<AutorGenero> oListaGeneros = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;        
        try {    
            if (conexao.isClosed())
                conexao = ConnectionFactory.getConnection();
            stmt = conexao.prepareStatement("select a.*, g.descricao_genero from autor_genero a inner join genero g on a.id_genero = g.id_genero where a.id_autor = ?");
            stmt.setInt(1, idAutor);
            rs = stmt.executeQuery();            
            while (rs.next()) {
                Genero oGenero = new Genero();
                oGenero.setIdGenero(rs.getInt("id_genero"));
                oGenero.setDescricaoGenero(rs.getString("descricao_genero"));                

                AutorGenero oAutorGenero = new AutorGenero();                
                oAutorGenero.setGenero(oGenero);
                oListaGeneros.add(oAutorGenero);
            }
        } catch (Exception ex) {
            System.out.println("Erro na AutorGeneroDAOImpl ao listar gênero dos autores.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na AutorGeneroDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oListaGeneros;        
    }

    @Override
    public Boolean alterar(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(int idObject) {        
        PreparedStatement stmt = null;                
        try {
            if (conexao.isClosed())
                conexao = ConnectionFactory.getConnection();            
            stmt = conexao.prepareStatement("delete from autor_genero where id_autor = ?");
            stmt.setInt(1, idObject);            
            stmt.executeUpdate();            
        } catch (Exception ex) {
            System.out.println("Erro na AutorGeneroDAOImpl ao excluir gênero do autor.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();            
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na AutorGeneroDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }          
    }

    @Override
    public List<Object> listar(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object carregar(int idObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
