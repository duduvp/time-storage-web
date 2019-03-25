/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.dao;

import br.com.timestorage.model.Usuario;
import br.com.timestorage.util.ConnectionFactory;
import br.com.timestorage.util.Funcoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author mateus
 */
public class UsuarioDAOImpl implements GenericDAO {

    private Connection conexao;
    private int parametro;

    public UsuarioDAOImpl() throws Exception {
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
        Usuario oUsuario = (Usuario) object;
        PreparedStatement stmt = null;
        String sql = "insert into usuario (tipo_usuario, senha_usuario, id_pessoa, foto_perfil_usuario) values (?, ?, ?, ?);";
        try {            
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, oUsuario.getTipoUsuario());
            stmt.setString(2, Funcoes.converterSHA(oUsuario.getSenhaUsuario()));
            PessoaDAOImpl dao = new PessoaDAOImpl();
            stmt.setInt(3, dao.inserir(oUsuario));
            stmt.setString(4, oUsuario.getFotoPerfilUsuario());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro na UsuarioDAOImpl ao inserir usuário.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na UsuarioDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Boolean alterar(Object object) {
        Usuario oUsuario = (Usuario) object;
        PreparedStatement stmt = null;
        String sql = "update usuario set tipo_usuario = ?, foto_perfil_usuario = ? where id_usuario = ?;";
        try {
            PessoaDAOImpl dao = new PessoaDAOImpl();
            if (dao.alterar(oUsuario)) {
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, oUsuario.getTipoUsuario());
                stmt.setString(2, oUsuario.getFotoPerfilUsuario());
                stmt.setInt(3, oUsuario.getIdUsuario());
                stmt.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Erro na UsuarioDAOImpl ao alterar usuário.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Erro na UsuarioDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object carregar(int idObject) {
        Usuario oUsuario = new Usuario();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select u.*, p.* from usuario u inner join pessoa p on u.id_pessoa = p.id_pessoa where u.id_usuario = ?;";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            if (rs.next()) {
                oUsuario.setIdPessoa(rs.getInt("id_pessoa"));
                oUsuario.setNomePessoa(rs.getString("nome_pessoa"));
                oUsuario.setSobrenomePessoa(rs.getString("sobrenome_pessoa"));
                oUsuario.setEmailPessoa(rs.getString("email_pessoa"));
                oUsuario.setTelefonePessoa(rs.getString("telefone_pessoa"));
                oUsuario.setDataNascimentoPessoa(rs.getDate("data_nascimento_pessoa"));
                oUsuario.setDataCadastroPessoa(rs.getDate("data_cadastro_pessoa"));
                oUsuario.setStatusPessoa(rs.getInt("status_pessoa"));
                oUsuario.setIdUsuario(rs.getInt("id_usuario"));
                oUsuario.setTipoUsuario(rs.getInt("tipo_usuario"));
                oUsuario.setSenhaUsuario(rs.getString("senha_usuario"));
                oUsuario.setFotoPerfilUsuario(rs.getString("foto_perfil_usuario"));
            }
        } catch (Exception ex) {
            System.out.println("Erro na UsuarioDAOImpl ao carregar usuário.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro na UsuarioDAOImpl ao fechar conexão com o banco de dados.\nMensagem: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oUsuario;
    }

    public Usuario logar(Usuario oUsuario) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "select u.*, p.* from usuario u inner join pessoa p on p.id_pessoa = u.id_pessoa "
                    + "where p.email_pessoa = ? and u.senha_usuario = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oUsuario.getEmailPessoa());
            stmt.setString(2, Funcoes.converterSHA(oUsuario.getSenhaUsuario()));;
            rs = stmt.executeQuery();
            if (rs.next()) {
                oUsuario.setIdUsuario(rs.getInt("id_usuario"));
                oUsuario.setNomePessoa(rs.getString("nome_pessoa"));
                oUsuario.setSobrenomePessoa(rs.getString("sobrenome_pessoa"));
                oUsuario.setTipoUsuario(rs.getInt("tipo_usuario"));
                oUsuario.setFotoPerfilUsuario(rs.getString("foto_perfil_usuario"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao logar UsuarioDAOImpl.\nErro:  " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
        }
        return oUsuario;
    }

    public Boolean verificarEmailExite(String email) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select u.*, p.* from usuario u inner join pessoa p on p.id_pessoa = u.id_pessoa "
                + "where p.email_pessoa = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar se email existe na UsuarioDAOImpl.\nErro:  " + e.getMessage());
        } 
        return true;
    }
}
