/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.dao;

import br.com.timestorage.model.Comentario;
import br.com.timestorage.model.Documento;
import br.com.timestorage.model.Usuario;
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
public class ComentarioDAOImpl implements GenericDAO {
    
    private Connection conexao;
    private int parametro;

    public ComentarioDAOImpl() throws Exception {
        try {
            this.conexao = ConnectionFactory.getConnection();
            this.parametro = 0;
            System.out.println("Banco de dados conectado com sucesso.");
        } catch (Exception ex) {
            throw new Exception("Erro ao estabelecer conexão com o banco de dados\nMensagem: " + ex.getMessage());
        }
    }    

    @Override
    public Boolean inserir(Object object) {
        Comentario oComentario = (Comentario) object;
        PreparedStatement stmt = null;
        String sql = "insert into comentario (texto_comentario, status_comentario, id_usuario, id_documento) values (?, ?, ?, ?);";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oComentario.getTextoComentario());
            stmt.setInt(2, oComentario.getStatusComentario());
            stmt.setInt(3, oComentario.getUsuario().getIdUsuario());
            stmt.setInt(4, oComentario.getDocumento().getIdDocumento());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro no ComentarioDAOImpl ao inserir categoria.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro no ComentarioDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
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
        String sql = "delete from comentario where id_comentario = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idObject);            
            stmt.executeUpdate();            
        } catch (Exception ex) {
            System.out.println("Erro no ComentarioDAOImpl ao inserir categoria.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();           
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro no ComentarioDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }        
    }

    public List<Comentario> listarComentarios(int idDocumento) {        
        List<Comentario> oListaComentarios = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select c.*, p.nome_pessoa, p.sobrenome_pessoa, u.foto_perfil_usuario from comentario c "
                + "inner join usuario u on c.id_usuario = u.id_usuario "
                + "inner join pessoa p on p.id_pessoa = u.id_pessoa "
                + "where c.id_documento = ? and c.status_comentario = 0 order by c.id_comentario";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDocumento);            
            rs = stmt.executeQuery();
            while (rs.next()) {
                Comentario oComentario = new Comentario();
                oComentario.setIdComentario(rs.getInt("id_comentario"));
                oComentario.setTextoComentario(rs.getString("texto_comentario"));
                oComentario.setStatusComentario(rs.getInt("status_comentario"));
                oComentario.setDataComentario(rs.getDate("data_comentario"));
                Usuario oUsuario = new Usuario(rs.getInt("id_usuario"));
                oUsuario.setNomePessoa(rs.getString("nome_pessoa"));
                oUsuario.setSobrenomePessoa(rs.getString("sobrenome_pessoa"));
                oUsuario.setFotoPerfilUsuario(rs.getString("foto_perfil_usuario"));
                oComentario.setUsuario(oUsuario);
                oComentario.setDocumento(new Documento(rs.getInt("id_documento")));
                oListaComentarios.add(oComentario);
            }
        } catch (Exception ex) {
            System.out.println("Erro no ComentarioDAOImpl ao listar comentários.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro no ComentarioDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oListaComentarios;
    }

    @Override
    public Object carregar(int idObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> listar(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
