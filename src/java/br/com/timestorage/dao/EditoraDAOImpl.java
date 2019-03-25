/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.dao;

import br.com.timestorage.model.Editora;
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
public class EditoraDAOImpl implements GenericDAO {

    private Connection conexao;
    private int parametro;

    public EditoraDAOImpl() throws Exception {
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
        Editora oEditora = (Editora) object;
        PreparedStatement stmt = null;
        String sql = "insert into editora (descricao_editora, status_editora) values (?, ?);";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oEditora.getDescricaoEditora());
            stmt.setInt(2, oEditora.getStatusEditora());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro na EditoraDAOImpl ao inserir editora.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na EditoraDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Boolean alterar(Object object) {
        Editora oEditora = (Editora) object;
        PreparedStatement stmt = null;
        String sql = "update editora set descricao_editora = ?, status_editora = ? where id_editora = ?;";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oEditora.getDescricaoEditora());
            stmt.setInt(2, oEditora.getStatusEditora());
            stmt.setInt(3, oEditora.getIdEditora());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro na EditoraDAOImpl ao alterar editora.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na EditoraDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
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
        Editora oEditoraFiltro = (Editora) object;
        List<Object> oListaEditora = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select e.* from editora e where e.id_editora > 0";
        try {
            if (!oEditoraFiltro.getDescricaoEditora().equals("")) {
                sql = String.format("%s and e.descricao_editora like ?", sql);
            }
            if (!(oEditoraFiltro.getStatusEditora() == 2)) {
                sql = String.format("%s and e.status_editora = ?", sql);
            }
            sql = String.format("%s order by e.id_editora", sql);

            stmt = conexao.prepareStatement(sql);

            if (!oEditoraFiltro.getDescricaoEditora().equals("")) {
                stmt.setString(indice(), oEditoraFiltro.getDescricaoEditora());
            }
            if (!(oEditoraFiltro.getStatusEditora() == 2)) {
                stmt.setInt(indice(), oEditoraFiltro.getStatusEditora());
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                Editora oEditora = new Editora();
                oEditora.setIdEditora(rs.getInt("id_editora"));
                oEditora.setDescricaoEditora(rs.getString("descricao_editora"));
                oEditora.setStatusEditora(rs.getInt("status_editora"));
                oListaEditora.add(oEditora);
            }
        } catch (Exception ex) {
            System.out.println("Erro na EditoraDAOImpl ao listar editora.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na EditoraDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oListaEditora;
    }

    @Override
    public Object carregar(int idObject) {
        Editora oEditora = new Editora();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select e.* from editora e where e.id_editora = ?;";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            if (rs.next()) {
                oEditora.setIdEditora(rs.getInt("id_editora"));
                oEditora.setDescricaoEditora(rs.getString("descricao_editora"));
                oEditora.setStatusEditora(rs.getInt("status_editora"));
            }

        } catch (Exception ex) {
            System.out.println("Erro na EditoraDAOImpl ao carregar editora.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na EditoraDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oEditora;
    }

}
