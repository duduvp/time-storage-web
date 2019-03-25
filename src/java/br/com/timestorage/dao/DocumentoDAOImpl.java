/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.dao;

import br.com.timestorage.model.Autor;
import br.com.timestorage.model.Categoria;
import br.com.timestorage.model.Documento;
import br.com.timestorage.model.DocumentoFiltro;
import br.com.timestorage.model.Editora;
import br.com.timestorage.model.Usuario;
import br.com.timestorage.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mateus
 */
public class DocumentoDAOImpl implements GenericDAO {

    private Connection conexao;
    private int parametro;

    public DocumentoDAOImpl() throws Exception {
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
        Documento oDocumento = (Documento) object;
        PreparedStatement stmt = null;        
        String sql = "insert into documento (titulo_documento, sinopse_documento, data_publicacao_documento, "
                + "status_documento, data_inclusao_documento, id_usuario, id_categoria, id_editora, id_documento) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oDocumento.getTituloDocumento());
            stmt.setString(2, oDocumento.getSinopseDocumento());
            stmt.setDate(3, null);
            if (oDocumento.getDataPublicacaoDocumento() != null) {
                stmt.setDate(3, new java.sql.Date(oDocumento.getDataPublicacaoDocumento().getTime()));
            }
            stmt.setInt(4, oDocumento.getStatusDocumento());
            stmt.setDate(5, new java.sql.Date(oDocumento.getDataInclusaoDocumento().getTime()));
            stmt.setInt(6, oDocumento.getUsuario().getIdUsuario());
            stmt.setInt(7, oDocumento.getCategoria().getIdCategoria());
            stmt.setInt(8, oDocumento.getEditora().getIdEditora());
            stmt.setInt(9, oDocumento.getIdDocumento());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro na DocumentoDAOImpl ao inserir documento.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na DocumentoDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }        
    }

    @Override
    public Boolean alterar(Object object) {
        Documento oDocumento = (Documento) object;
        PreparedStatement stmt = null;        
        String sql = "update documento set titulo_documento = ?, sinopse_documento = ?, data_publicacao_documento = ?, "
                + "status_documento = ?, data_inclusao_documento = ?, id_usuario = ?, id_categoria = ?, id_editora = ? where id_documento = ?;";                
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oDocumento.getTituloDocumento());
            stmt.setString(2, oDocumento.getSinopseDocumento());
            stmt.setDate(3, null);
            if (oDocumento.getDataPublicacaoDocumento() != null) {
                stmt.setDate(3, new java.sql.Date(oDocumento.getDataPublicacaoDocumento().getTime()));
            }
            stmt.setInt(4, oDocumento.getStatusDocumento());
            stmt.setDate(5, new java.sql.Date(oDocumento.getDataInclusaoDocumento().getTime()));
            stmt.setInt(6, oDocumento.getUsuario().getIdUsuario());
            stmt.setInt(7, oDocumento.getCategoria().getIdCategoria());
            stmt.setInt(8, oDocumento.getEditora().getIdEditora());
            stmt.setInt(9, oDocumento.getIdDocumento());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro na DocumentoDAOImpl ao alterar o documento.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na DocumentoDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
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
        DocumentoFiltro oDocumentoFiltro = (DocumentoFiltro) object;
        List<Object> oListaDocumento = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select d.*, p.nome_pessoa, p.sobrenome_pessoa, c.descricao_categoria, e.descricao_editora "
                + "from documento d "
                + "inner join usuario u on u.id_usuario = d.id_usuario "
                + "inner join pessoa p on p.id_pessoa = u.id_pessoa "
                + "inner join categoria c on c.id_categoria = d.id_categoria "
                + "inner join editora e on e.id_editora = d.id_editora "
                + "where d.id_documento > 0 ";
        try {
            if (!oDocumentoFiltro.getTituloDocumento().equals("")) {
                sql = String.format("%s and upper(d.titulo_documento) like ?", sql);
            }
            if (!(oDocumentoFiltro.getStatusDocumento() == 2)) {
                sql = String.format("%s and d.status_documento = ?", sql);
            }
            if ((oDocumentoFiltro.getDataInicio() != null) && (oDocumentoFiltro.getDataFim()!= null)) {
                sql = String.format("%s and d.data_publicacao_documento between ? and ?", sql);
            }
            sql = String.format("%s order by d.id_documento", sql);

            stmt = conexao.prepareStatement(sql);

            if (!oDocumentoFiltro.getTituloDocumento().equals("")) {
                stmt.setString(indice(), "%" + oDocumentoFiltro.getTituloDocumento().toUpperCase() + "%");
            }
            if (!(oDocumentoFiltro.getStatusDocumento() == 2)) {
                stmt.setInt(indice(), oDocumentoFiltro.getStatusDocumento());
            }
            if ((oDocumentoFiltro.getDataInicio() != null) && (oDocumentoFiltro.getDataFim()!= null)) {
                stmt.setDate(indice(), new java.sql.Date(oDocumentoFiltro.getDataInicio().getTime()));
                stmt.setDate(indice(), new java.sql.Date(oDocumentoFiltro.getDataFim().getTime()));
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                Documento oDocumento = new Documento();
                oDocumento.setIdDocumento(rs.getInt("id_documento"));
                oDocumento.setTituloDocumento(rs.getString("titulo_documento"));
                oDocumento.setSinopseDocumento(rs.getString("sinopse_documento"));
                oDocumento.setDataPublicacaoDocumento(rs.getDate("data_publicacao_documento"));
                oDocumento.setStatusDocumento(rs.getInt("status_documento"));
                oDocumento.setDataInclusaoDocumento(rs.getDate("data_inclusao_documento"));

                Usuario oUsuario = new Usuario(rs.getInt("id_usuario"));
                oUsuario.setNomePessoa(rs.getString("nome_pessoa"));
                oUsuario.setSobrenomePessoa(rs.getString("sobrenome_pessoa"));
                oDocumento.setUsuario(oUsuario);

                Categoria oCategoria = new Categoria(rs.getInt("id_categoria"));
                oCategoria.setDescricaoCategoria(rs.getString("descricao_categoria"));
                oDocumento.setCategoria(oCategoria);

                Editora oEditora = new Editora(rs.getInt("id_editora"));
                oEditora.setDescricaoEditora(rs.getString("descricao_editora"));
                oDocumento.setEditora(oEditora);

                oListaDocumento.add(oDocumento);
            }
        } catch (Exception ex) {
            System.out.println("Erro na DocumentoDAOImpl ao listar documento.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na DocumentoDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oListaDocumento;
    }

    @Override
    public Object carregar(int idObject) {        
        Documento oDocumento = new Documento();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select d.*, p.nome_pessoa, p.sobrenome_pessoa, c.descricao_categoria, e.descricao_editora "
                + "from documento d "
                + "inner join usuario u on u.id_usuario = d.id_usuario "
                + "inner join pessoa p on p.id_pessoa = u.id_pessoa "
                + "inner join categoria c on c.id_categoria = d.id_categoria "
                + "inner join editora e on e.id_editora = d.id_editora "
                + "where d.id_documento = ? ";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idObject);

            rs = stmt.executeQuery();
            if (rs.next()) {                
                oDocumento.setIdDocumento(rs.getInt("id_documento"));
                oDocumento.setTituloDocumento(rs.getString("titulo_documento"));
                oDocumento.setSinopseDocumento(rs.getString("sinopse_documento"));
                oDocumento.setDataPublicacaoDocumento(rs.getDate("data_publicacao_documento"));
                oDocumento.setStatusDocumento(rs.getInt("status_documento"));
                oDocumento.setDataInclusaoDocumento(rs.getDate("data_inclusao_documento"));

                Usuario oUsuario = new Usuario(rs.getInt("id_usuario"));
                oUsuario.setNomePessoa(rs.getString("nome_pessoa"));
                oUsuario.setSobrenomePessoa(rs.getString("sobrenome_pessoa"));
                oDocumento.setUsuario(oUsuario);

                Categoria oCategoria = new Categoria(rs.getInt("id_categoria"));
                oCategoria.setDescricaoCategoria(rs.getString("descricao_categoria"));
                oDocumento.setCategoria(oCategoria);

                Editora oEditora = new Editora(rs.getInt("id_editora"));
                oEditora.setDescricaoEditora(rs.getString("descricao_editora"));
                oDocumento.setEditora(oEditora);                
            }
        } catch (Exception ex) {
            System.out.println("Erro na DocumentoDAOImpl ao carregar documento.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na DocumentoDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oDocumento;        
    }
    
    public int GerarCodigo(){       
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer idDocumento = null;        
        try {
            stmt = conexao.prepareStatement("select nextval('documento_id_documento_seq')");
            rs = stmt.executeQuery();

            if (rs.next()) {
                idDocumento = rs.getInt("nextval");
            }
        } catch (Exception ex) {
            System.out.println("Erro na DocumentoDAOImpl ao gerar código do documento.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na DocumentoDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return idDocumento;        
    }

    
    public Object carregarDocumento(int idObject, Boolean carregarNormal) {
        Documento oDocumento = new Documento();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select d.* from documento d where d.id_documento = ?";        
        try {            
            stmt = conexao.prepareStatement(sql);    
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            if (rs.next()) {                
                oDocumento.setIdDocumento(rs.getInt("id_documento"));
                oDocumento.setTituloDocumento(rs.getString("titulo_documento"));
                oDocumento.setSinopseDocumento(rs.getString("sinopse_documento"));
                if (!carregarNormal){
                    oDocumento.setSinopseDocumento(oDocumento.getSinopseDocumento().replaceAll("\n","<br/>"));
                }
                oDocumento.setDataInclusaoDocumento(rs.getDate("data_inclusao_documento"));                
            }
        } catch (Exception ex) {
            System.out.println("Erro na DocumentoDAOImpl ao carregar documento.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na DocumentoDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oDocumento;        
    } 
    
    public List<Object> consultar(String titulo) {        
        List<Object> oListaDocumentos = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select d.* from documento d where d.id_documento > 0";        
        try {
            if (!titulo.equals("")) 
                sql = String.format("%s and upper(d.titulo_documento) like ?", sql);             
            sql = String.format("%s order by d.data_inclusao_documento", sql);
            
            stmt = conexao.prepareStatement(sql);
            
            if (!titulo.equals("")) {
                this.parametro++;
                stmt.setString(this.parametro, titulo + "%"); 
            }
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                Documento oDocumento = new Documento();
                oDocumento.setIdDocumento(rs.getInt("id_documento"));
                oDocumento.setTituloDocumento(rs.getString("titulo_documento"));
                oDocumento.setSinopseDocumento(rs.getString("sinopse_documento"));
                if (rs.getString("sinopse_documento").length() > 200)
                    oDocumento.setSinopseDocumento(rs.getString("sinopse_documento").substring(0, 200) + "...");
                oDocumento.setDataInclusaoDocumento(rs.getDate("data_inclusao_documento"));
                oListaDocumentos.add(oDocumento);
            }
        } catch (Exception ex) {
            System.out.println("Erro na DocumentoDAOImpl ao listar documento.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na DocumentoDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oListaDocumentos;        
    }     
    
}
