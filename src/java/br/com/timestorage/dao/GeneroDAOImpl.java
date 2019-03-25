/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.dao;

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
public class GeneroDAOImpl implements GenericDAO {

    private Connection conexao;
    private int parametro;

    public GeneroDAOImpl() throws Exception {
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
        Genero oGenero = (Genero) object;
        PreparedStatement stmt = null;
        String sql = "insert into genero (descricao_genero, status_genero) values (?, ?);";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oGenero.getDescricaoGenero());
            stmt.setInt(2, oGenero.getStatusGenero());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro na GeneroDAOImpl ao inserir gênero.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na GeneroDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Boolean alterar(Object object) {
        Genero oGenero = (Genero) object;
        PreparedStatement stmt = null;
        String sql = "update genero set descricao_genero = ?, status_genero = ? where id_genero = ?;";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oGenero.getDescricaoGenero());
            stmt.setInt(2, oGenero.getStatusGenero());
            stmt.setInt(3, oGenero.getIdGenero());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro na GeneroDAOImpl ao alterar gênero.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na GeneroDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
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
        Genero oGeneroFiltro = (Genero) object;
        List<Object> oListaGenero = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select g.* from genero g where g.id_genero > 0";
        try {
            if (!oGeneroFiltro.getDescricaoGenero().equals("")) {
                sql = String.format("%s and g.descricao_genero like ?", sql);
            }
            if (!(oGeneroFiltro.getStatusGenero() == 2)) {
                sql = String.format("%s and g.status_genero = ?", sql);
            }
            sql = String.format("%s order by g.id_genero", sql);

            stmt = conexao.prepareStatement(sql);

            if (!oGeneroFiltro.getDescricaoGenero().equals("")) {
                stmt.setString(indice(), oGeneroFiltro.getDescricaoGenero());
            }
            if (!(oGeneroFiltro.getStatusGenero() == 2)) {
                stmt.setInt(indice(), oGeneroFiltro.getStatusGenero());
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                Genero oGenero = new Genero();
                oGenero.setIdGenero(rs.getInt("id_genero"));
                oGenero.setDescricaoGenero(rs.getString("descricao_genero"));
                oGenero.setStatusGenero(rs.getInt("status_genero"));
                oListaGenero.add(oGenero);
            }
        } catch (Exception ex) {
            System.out.println("Erro na GeneroDAOImpl ao listar gênero.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na GeneroDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oListaGenero;
    }

    @Override
    public Object carregar(int idObject) {
        Genero oGenero = new Genero();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select g.* from genero g where g.id_genero = ?;";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            if (rs.next()) {
                oGenero.setIdGenero(rs.getInt("id_genero"));
                oGenero.setDescricaoGenero(rs.getString("descricao_genero"));
                oGenero.setStatusGenero(rs.getInt("status_genero"));
            }

        } catch (Exception ex) {
            System.out.println("Erro na GeneroDAOImpl ao carregar gênero.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na GeneroDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oGenero;
    }
}
