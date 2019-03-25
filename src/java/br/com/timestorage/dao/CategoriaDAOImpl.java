/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.dao;

import br.com.timestorage.model.Categoria;
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
public class CategoriaDAOImpl implements GenericDAO {

    private Connection conexao;
    private int parametro;

    public CategoriaDAOImpl() throws Exception {
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
        Categoria oCategoria = (Categoria) object;
        PreparedStatement stmt = null;
        String sql = "insert into categoria (descricao_categoria, status_categoria) values (?, ?);";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oCategoria.getDescricaoCategoria());
            stmt.setInt(2, oCategoria.getStatusCategoria());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro na CategoriaDAOImpl ao inserir categoria.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na CategoriaDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Boolean alterar(Object object) {
        Categoria oCategoria = (Categoria) object;
        PreparedStatement stmt = null;
        String sql = "update categoria set descricao_categoria = ?, status_categoria = ? where id_categoria = ?;";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oCategoria.getDescricaoCategoria());
            stmt.setInt(2, oCategoria.getStatusCategoria());
            stmt.setInt(3, oCategoria.getIdCategoria());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro na CategoriaDAOImpl ao alterar categoria.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na CategoriaDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
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
        Categoria oCategoriaFiltro = (Categoria) object;
        List<Object> oListaCategoria = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select c.* from categoria c where c.id_categoria > 0";
        try {
            if (!oCategoriaFiltro.getDescricaoCategoria().equals("")) {
                sql = String.format("%s and c.descricao_categoria like ?", sql);
            }
            if (!(oCategoriaFiltro.getStatusCategoria() == 2)) {
                sql = String.format("%s and c.status_categoria = ?", sql);
            }
            sql = String.format("%s order by c.id_categoria", sql);

            stmt = conexao.prepareStatement(sql);

            if (!oCategoriaFiltro.getDescricaoCategoria().equals("")) {
                stmt.setString(indice(), oCategoriaFiltro.getDescricaoCategoria());
            }
            if (!(oCategoriaFiltro.getStatusCategoria() == 2)) {
                stmt.setInt(indice(), oCategoriaFiltro.getStatusCategoria());
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                Categoria oCategoria = new Categoria();
                oCategoria.setIdCategoria(rs.getInt("id_categoria"));
                oCategoria.setDescricaoCategoria(rs.getString("descricao_categoria"));
                oCategoria.setStatusCategoria(rs.getInt("status_categoria"));
                oListaCategoria.add(oCategoria);
            }
        } catch (Exception ex) {
            System.out.println("Erro na CategoriaDAOImpl ao listar categoria.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na CategoriaDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oListaCategoria;
    }

    @Override
    public Object carregar(int idObject) {
        Categoria oCategoria = new Categoria();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select c.* from categoria c where c.id_categoria = ?;";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            if (rs.next()) {
                oCategoria.setIdCategoria(rs.getInt("id_categoria"));
                oCategoria.setDescricaoCategoria(rs.getString("descricao_categoria"));
                oCategoria.setStatusCategoria(rs.getInt("status_categoria"));
            }
        } catch (Exception ex) {
            System.out.println("Erro na CategoriaDAOImpl ao carregar categoria.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na CategoriaDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oCategoria;
    }

}
