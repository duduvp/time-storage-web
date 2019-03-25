/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.dao;

import br.com.timestorage.model.Autor;
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
public class AutorDAOImpl implements GenericDAO {

    private Connection conexao;
    private int parametro;

    public AutorDAOImpl() throws Exception {
        try {
            this.conexao = ConnectionFactory.getConnection();
            this.parametro = 0;
            System.out.println("Banco de dados conectado com sucesso.");
        } catch (Exception ex) {
            throw new Exception("Erro ao estabelecer conexão com o banco de dados\nMensagem: " + ex.getMessage());
        }
    }

    private int indice() {
        this.parametro++;
        return this.parametro;
    }

    @Override
    public Boolean inserir(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Integer inserirAutor(Object object) {
        Autor oAutor = (Autor) object;
        PreparedStatement stmt = null;
        ResultSet rs = null;    
        Integer idAutor = null;
        try {
            stmt = conexao.prepareStatement("insert into autor (nome_autor, data_nascimento_autor, data_falecimento_autor, status_autor) values (?, ?, ?, ?) returning id_autor;");
            stmt.setString(1, oAutor.getNomeAutor());
            stmt.setDate(2, null);
            if (oAutor.getDataNascimentoAutor() != null) {
                stmt.setDate(2, new java.sql.Date(oAutor.getDataNascimentoAutor().getTime()));
            }
            stmt.setDate(3, null);
            if (oAutor.getDataFalecimentoAutor() != null) {
                stmt.setDate(3, new java.sql.Date(oAutor.getDataFalecimentoAutor().getTime()));
            }
            stmt.setInt(4, oAutor.getStatusAutor());
            rs = stmt.executeQuery();           

            if (rs.next()) {
                idAutor = rs.getInt("id_autor");
            }            
        } catch (Exception ex) {
            System.out.println("Erro na AutorDAOImpl ao inserir autor.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();            
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na AutorDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }   
        return idAutor;
    }

    @Override
    public Boolean alterar(Object object) {
        Autor oAutor = (Autor) object;
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("update autor set nome_autor = ?, data_nascimento_autor = ?, data_falecimento_autor = ?, status_autor = ? where id_autor = ?;");
            stmt.setString(1, oAutor.getNomeAutor());
            stmt.setDate(2, null);
            if (oAutor.getDataNascimentoAutor() != null) {
                stmt.setDate(2, new java.sql.Date(oAutor.getDataNascimentoAutor().getTime()));
            }
            stmt.setDate(3, null);
            if (oAutor.getDataFalecimentoAutor() != null) {
                stmt.setDate(3, new java.sql.Date(oAutor.getDataFalecimentoAutor().getTime()));
            }
            stmt.setInt(4, oAutor.getStatusAutor());
            stmt.setInt(5, oAutor.getIdAutor());
            stmt.executeUpdate();   
            return true;
        } catch (Exception ex) {
            System.out.println("Erro na AutorDAOImpl ao inserir autor.\nMensagem: " + ex.getMessage());
            ex.printStackTrace(); 
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na AutorDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }               
    }

    @Override
    public void excluir(int idObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> listar(Object object) {
        Autor oAutorFiltro = (Autor) object;        
        List<Object> oListaAutor = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select a.* from autor a where a.id_autor > 0";
        try {
            if (!oAutorFiltro.getNomeAutor().equals("")) {
                sql = String.format("%s and a.nome_autor like ?", sql);
            }
            if (!(oAutorFiltro.getStatusAutor()== 2)) {
                sql = String.format("%s and a.status_autor = ?", sql);
            }
            sql = String.format("%s order by a.id_autor", sql);

            stmt = conexao.prepareStatement(sql);

            if (!oAutorFiltro.getNomeAutor().equals("")) {
                stmt.setString(indice(), oAutorFiltro.getNomeAutor());
            }
            if (!(oAutorFiltro.getStatusAutor() == 2)) {
                stmt.setInt(indice(), oAutorFiltro.getStatusAutor());
            }

            rs = stmt.executeQuery();            
            while (rs.next()) {
                Autor oAutor = new Autor();
                oAutor.setIdAutor(rs.getInt("id_autor"));
                oAutor.setNomeAutor(rs.getString("nome_autor"));
                oAutor.setStatusAutor(rs.getInt("status_autor"));
                oAutor.setDataNascimentoAutor(rs.getDate("data_nascimento_autor"));
                oAutor.setDataFalecimentoAutor(rs.getDate("data_falecimento_autor"));                
                oListaAutor.add(oAutor);
            }
        } catch (Exception ex) {
            System.out.println("Erro na AutorDAOImpl ao listar autor.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na AutorDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oListaAutor;        
    }

    @Override
    public Object carregar(int idObject) {           
        Autor oAutor = new Autor();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select a.* from autor a where a.id_autor = ?;";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idObject);       
            rs = stmt.executeQuery();            
            while (rs.next()) {                
                oAutor.setIdAutor(rs.getInt("id_autor"));
                oAutor.setNomeAutor(rs.getString("nome_autor"));
                oAutor.setStatusAutor(rs.getInt("status_autor"));                
                oAutor.setDataNascimentoAutor(rs.getDate("data_nascimento_autor"));
                oAutor.setDataFalecimentoAutor(rs.getDate("data_falecimento_autor"));                
            }
        } catch (Exception ex) {
            System.out.println("Erro na AutorDAOImpl ao carregar autor.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na AutorDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oAutor;
    }

}
