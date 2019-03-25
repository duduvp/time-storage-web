/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.dao;

import br.com.timestorage.model.Documento;
import br.com.timestorage.model.Imagem;
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
public class ImagemDAOImpl implements GenericDAO{
    
    private Connection conexao;

    public ImagemDAOImpl() throws Exception {
        try {
            this.conexao = ConnectionFactory.getConnection();           
            System.out.println("Banco de dados conectado com sucesso.");
        } catch (Exception ex) {
            throw new Exception("Erro ao estabelecer conexão com o banco de dados\nMensagem: " + ex.getMessage());
        }
    }      

    @Override
    public Boolean inserir(Object object) {
        Imagem oImagem = (Imagem) object;
        PreparedStatement stmt = null;                
        try {
            if (conexao.isClosed())
                conexao = ConnectionFactory.getConnection();            
            stmt = conexao.prepareStatement("insert into imagem (id_imagem, descricao_imagem, id_documento, caminho_imagem) values (?, ?, ?, ?);");
            stmt.setInt(1, oImagem.getIdImagem());
            stmt.setString(2, oImagem.getDescricaoImagem());
            stmt.setInt(3, oImagem.getDocumento().getIdDocumento());
            stmt.setString(4, oImagem.getCaminhoImagem());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro na ImagemDAOImpl ao inserir imagem do documento.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na ImagemDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> listar(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object carregar(int idObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int GerarCodigo(){       
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer idDocumento = null;        
        try {
            if (conexao.isClosed())
                conexao = ConnectionFactory.getConnection();             
            stmt = conexao.prepareStatement("select nextval('imagem_id_imagem_seq')");
            rs = stmt.executeQuery();

            if (rs.next()) {
                idDocumento = rs.getInt("nextval");
            }
        } catch (Exception ex) {
            System.out.println("Erro na ImagemDAOImpl ao gerar código da imagem.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na ImagemDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return idDocumento;        
    }  
    
    public List<Imagem> listarImagensDocumento(int idDocumento){
        List<Imagem> oListaImagens = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;        
        try {    
            if (conexao.isClosed())
                conexao = ConnectionFactory.getConnection();
            stmt = conexao.prepareStatement("select i.* from imagem i where i.id_documento = ?");
            stmt.setInt(1, idDocumento);
            rs = stmt.executeQuery();            
            while (rs.next()) {
                Imagem oImagem = new Imagem();
                oImagem.setIdImagem(rs.getInt("id_imagem"));
                oImagem.setCaminhoImagem(rs.getString("caminho_imagem"));
                oImagem.setDocumento(new Documento(rs.getInt("id_documento")));
                oListaImagens.add(oImagem);
            }
        } catch (Exception ex) {
            System.out.println("Erro na ImagemDAOImpl ao listar imagens do documento.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na ImagemDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oListaImagens;          
    }
    
}
