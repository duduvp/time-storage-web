/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.dao;

import br.com.timestorage.model.Autor;
import br.com.timestorage.model.DocumentoAutor;
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
public class DocumentoAutorDAOImpl implements GenericDAO{
    
    private Connection conexao;

    public DocumentoAutorDAOImpl() throws Exception {
        try {
            this.conexao = ConnectionFactory.getConnection();           
            System.out.println("Banco de dados conectado com sucesso.");
        } catch (Exception ex) {
            throw new Exception("Erro ao estabelecer conexão com o banco de dados\nMensagem: " + ex.getMessage());
        }
    }      

    @Override
    public Boolean inserir(Object object) {
        DocumentoAutor oDocumentoAutor = (DocumentoAutor) object;
        PreparedStatement stmt = null;                
        try {
            if (conexao.isClosed())
                conexao = ConnectionFactory.getConnection();            
            stmt = conexao.prepareStatement("insert into autor_documento (id_autor, id_documento) values (?, ?);");
            stmt.setInt(1, oDocumentoAutor.getAutor().getIdAutor());
            stmt.setInt(2, oDocumentoAutor.getDocumento().getIdDocumento());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro na DocumentoAutorDAOImpl ao inserir autor do documento.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na DocumentoAutorDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }          
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
            stmt = conexao.prepareStatement("delete from autor_documento where id_documento = ?");
            stmt.setInt(1, idObject);            
            stmt.executeUpdate();            
        } catch (Exception ex) {
            System.out.println("Erro na AutorGeneroDAOImpl ao excluir autor do documento.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();            
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na DocumentoAutorDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
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
    
    public List<DocumentoAutor> listarDocumentoAutor(int idDocumento) {        
        List<DocumentoAutor> oListaAutores = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;        
        try {    
            if (conexao.isClosed())
                conexao = ConnectionFactory.getConnection();
            stmt = conexao.prepareStatement("select d.*, a.nome_autor from autor_documento d inner join autor a on d.id_autor = a.id_autor where d.id_documento = ?");
            stmt.setInt(1, idDocumento);
            rs = stmt.executeQuery();            
            while (rs.next()) {
                Autor oAutor = new Autor();
                oAutor.setIdAutor(rs.getInt("id_autor"));
                oAutor.setNomeAutor(rs.getString("nome_autor"));                

                DocumentoAutor oDocumentoAutor = new DocumentoAutor();                
                oDocumentoAutor.setAutor(oAutor);
                oListaAutores.add(oDocumentoAutor);
            }
        } catch (Exception ex) {
            System.out.println("Erro na DocumentoAutorDAOImpl ao listar autores do documento.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na DocumentoAutorDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oListaAutores;        
    }    
    
}
