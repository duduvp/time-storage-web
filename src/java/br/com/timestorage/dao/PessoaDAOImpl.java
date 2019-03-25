/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.dao;

import br.com.timestorage.model.Pessoa;
import br.com.timestorage.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mateus
 */
public class PessoaDAOImpl {

    private Connection conexao;
    private int parametro;

    public PessoaDAOImpl() throws Exception {
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

    public int inserir(Object object) {
        Pessoa oPessoa = (Pessoa) object;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        String sql = "insert into pessoa (nome_pessoa, sobrenome_pessoa, email_pessoa, telefone_pessoa, "
                + "data_nascimento_pessoa, data_cadastro_pessoa, status_pessoa) values (?, ?, ?, ?, ?, ?, ?) returning id_pessoa;";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oPessoa.getNomePessoa());
            stmt.setString(2, oPessoa.getSobrenomePessoa());
            stmt.setString(3, oPessoa.getEmailPessoa());
            stmt.setString(4, oPessoa.getTelefonePessoa());
            stmt.setDate(5, new java.sql.Date(oPessoa.getDataNascimentoPessoa().getTime()));
            stmt.setDate(6, new java.sql.Date(oPessoa.getDataCadastroPessoa().getTime()));
            stmt.setInt(7, oPessoa.getStatusPessoa());
            rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("id_pessoa");
        } catch (Exception ex) {
            System.out.println("Erro na PessoaDAOImpl ao inserir pessoa.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
            return 0;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na PessoaDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public Boolean alterar(Object object) {
        Pessoa oPessoa = (Pessoa) object;
        PreparedStatement stmt = null;
        String sql = "update pessoa set nome_pessoa = ?, sobrenome_pessoa = ?, email_pessoa = ?, telefone_pessoa = ?, "
                + "data_nascimento_pessoa = ?, status_pessoa = ? where id_pessoa = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oPessoa.getNomePessoa());
            stmt.setString(2, oPessoa.getSobrenomePessoa());
            stmt.setString(3, oPessoa.getEmailPessoa());
            stmt.setString(4, oPessoa.getTelefonePessoa());
            stmt.setDate(5, new java.sql.Date(oPessoa.getDataNascimentoPessoa().getTime()));
            stmt.setInt(6, oPessoa.getStatusPessoa());
            stmt.setInt(7, oPessoa.getIdPessoa());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro na PessoaDAOImpl ao alterar pessoa.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na PessoaDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public List<Object> listar(Object object) {
        Pessoa oPessoaFiltro = (Pessoa) object;
        List<Object> oListaPessoa = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select p.* from pessoa p where p.id_pessoa > 0";
        try {
            if (!oPessoaFiltro.getNomePessoa().equals("")) {
                sql = String.format("%s and p.nome_pessoa like ?", sql);
            }
            if (!oPessoaFiltro.getSobrenomePessoa().equals("")) {
                sql = String.format("%s and p.sobrenome_pessoa = ?", sql);
            }
            sql = String.format("%s order by p.nome_pessoa", sql);

            stmt = conexao.prepareStatement(sql);

            if (!oPessoaFiltro.getNomePessoa().equals("")) {
                stmt.setString(indice(), oPessoaFiltro.getNomePessoa());
            }
            if (!oPessoaFiltro.getSobrenomePessoa().equals("")) {
                stmt.setString(indice(), oPessoaFiltro.getSobrenomePessoa());
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                Pessoa oPessoa = new Pessoa();
                oPessoa.setIdPessoa(rs.getInt("id_pessoa"));
                oPessoa.setNomePessoa(rs.getString("nome_pessoa"));
                oPessoa.setSobrenomePessoa(rs.getString("sobrenome_pessoa"));
                oPessoa.setEmailPessoa(rs.getString("email_pessoa"));
                oPessoa.setTelefonePessoa(rs.getString("telefone_pessoa"));
                oPessoa.setDataNascimentoPessoa(rs.getDate("data_nascimento_pessoa"));
                oPessoa.setDataCadastroPessoa(rs.getDate("data_cadastro_pessoa"));
                oPessoa.setStatusPessoa(rs.getInt("status_pessoa"));
                oListaPessoa.add(oPessoa);
            }
        } catch (Exception ex) {
            System.out.println("Erro na PessoaDAOImpl ao listar pessoa.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na PessoaDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oListaPessoa;
    }
}
