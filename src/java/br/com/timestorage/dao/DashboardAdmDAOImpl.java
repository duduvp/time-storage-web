/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.dao;

import br.com.timestorage.model.DashboardAdm;
import br.com.timestorage.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author mateus
 */
public class DashboardAdmDAOImpl {
    
    private Connection conexao;

    public DashboardAdmDAOImpl() throws Exception {
        try {
            this.conexao = ConnectionFactory.getConnection();            
            System.out.println("Banco de dados conectado com sucesso.");
        } catch (Exception ex) {
            throw new Exception("Erro ao estabelecer conexão com o banco de dados\nMensagem: " + ex.getMessage());
        }
    }    
    
    public DashboardAdm dadosDashboard(){
        DashboardAdm oDashboardAdm = new DashboardAdm();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select count(a.*) as qtd_autores, "+ 
            "(select count(*) from editora) as qtd_editoras, "+
            "(select count(*) from genero) as qtd_genero, "+
            "(select count(*) from documento) as qtd_documentos "+
            "from autor a;";
        try {
            stmt = conexao.prepareStatement(sql);            
            rs = stmt.executeQuery();
            if (rs.next()) {
                oDashboardAdm.setQtdAutores(rs.getInt("qtd_autores"));
                oDashboardAdm.setQtdDocumentos(rs.getInt("qtd_documentos"));
                oDashboardAdm.setQtdEditoras(rs.getInt("qtd_editoras"));
                oDashboardAdm.setQtdeGeneros(rs.getInt("qtd_genero"));
            }
        } catch (Exception ex) {
            System.out.println("Erro na DashboardAdmDAOImpl ao carregar dados do dashboard.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na DashboardAdmDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }        
        return oDashboardAdm;
    }
    
}
