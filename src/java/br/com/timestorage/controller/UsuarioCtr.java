/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.controller;

import br.com.timestorage.dao.GenericDAO;
import br.com.timestorage.dao.UsuarioDAOImpl;
import br.com.timestorage.model.Usuario;
import br.com.timestorage.util.Funcoes;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author mateus
 */
@WebServlet(name = "UsuarioCtr", urlPatterns = {"/UsuarioCtr"})
public class UsuarioCtr extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws ParseException if an ParseException error occurs
     * @throws NoSuchAlgorithmException if an NoSuchAlgorithmException error
     * occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, NoSuchAlgorithmException, FileUploadException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        switch (request.getParameter("metodo")) {
            case "cadastrar":                
                request.getRequestDispatcher("usuario/cadastrar.jsp").forward(request, response);
                break;

            case "registrar":
                registrar(request, response);
                break;

            case "listar":
                listar(request, response);
                break;

            case "carregar":
                carregar(request, response, 0);
                break;

            case "login":
                logar(request, response);
                break;

            case "telaLogin":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case "salvar":
                salvar(request, response);
                break;

            case "sair":
                HttpSession sessao = request.getSession(true);
                sessao.invalidate();
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

        }
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, NoSuchAlgorithmException {
        Boolean sucesso = false;

        Usuario oUsuario = new Usuario(0, 0);
        oUsuario.setNomePessoa(request.getParameter("nomePessoa").toUpperCase());
        oUsuario.setSobrenomePessoa(request.getParameter("sobrenomePessoa").toUpperCase());
        oUsuario.setEmailPessoa(request.getParameter("emailPessoa").toLowerCase());
        oUsuario.setTelefonePessoa(request.getParameter("telefonePessoa"));
        oUsuario.setDataNascimentoPessoa(Funcoes.StringToDate(request.getParameter("dataNascimentoPessoa")));
        oUsuario.setDataCadastroPessoa(Funcoes.getPegaDataAtual());
        oUsuario.setSenhaUsuario(request.getParameter("senhaUsuario"));

        try {
            if (!request.getParameter("senhaUsuario").equals(request.getParameter("confirmarSenhaUsuario"))) {
                throw new Exception("Senhas não conferem!");
            }

            UsuarioDAOImpl dao = new UsuarioDAOImpl();

            if (!dao.verificarEmailExite(oUsuario.getEmailPessoa())) {
                throw new Exception("Email já está sendo utilizado por outro usuário!");
            }

            sucesso = dao.inserir(oUsuario);

            if (!sucesso) {
                request.setAttribute("mensagem", "Problemas ao salvar usuário!");
            }
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }

        if (!sucesso) {
            request.setAttribute("usuario", oUsuario);
            request.setAttribute("confirmarSenhaUsuario", request.getParameter("confirmarSenhaUsuario"));
            request.getRequestDispatcher("usuario/cadastrar.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void logar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!request.getParameter("emailPessoa").equals("") && !request.getParameter("senhaUsuario").equals("")) {
            try {
                Usuario oUsuario = new Usuario();
                oUsuario.setEmailPessoa(request.getParameter("emailPessoa"));
                oUsuario.setSenhaUsuario(request.getParameter("senhaUsuario"));

                UsuarioDAOImpl dao = new UsuarioDAOImpl();
                oUsuario = dao.logar(oUsuario);
                if (oUsuario.getIdUsuario() > 0) {
                    // Criando sessão ativa.
                    HttpSession sessao = request.getSession(true);

                    // Criando os campos da sessão.
                    sessao.setAttribute("idUsuario", oUsuario.getIdUsuario());
                    sessao.setAttribute("nomePessoa", oUsuario.getNomePessoa());
                    sessao.setAttribute("sobrenomePessoa", oUsuario.getSobrenomePessoa());
                    sessao.setAttribute("nomeCompleto", oUsuario.getNomePessoa() + " " + oUsuario.getSobrenomePessoa());
                    sessao.setAttribute("tipoUsuario", oUsuario.getTipoUsuario());                    
                    sessao.setAttribute("fotoPerfilUsuario", oUsuario.getFotoPerfilUsuario());
                    request.getRequestDispatcher("PaginaInicial").forward(request, response);                    
                } else {
                    // Envia o email e senha para não perder o que foi digitado.
                    request.setAttribute("emailPessoa", oUsuario.getEmailPessoa());
                    request.setAttribute("senhaUsuario", oUsuario.getSenhaUsuario());
                    request.setAttribute("mensagem", "Email ou senha inválidos!");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } catch (Exception e) {
                System.out.println("Problemas ao no servlet ao Logar. Erro: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            request.setAttribute("mensagem", "Preencha o email e senha para fazer Login!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private void carregar(HttpServletRequest request, HttpServletResponse response, int idUsuario)
            throws ServletException, IOException {
        try {
            GenericDAO dao = new UsuarioDAOImpl();
            if (idUsuario > 0) {
                request.setAttribute("usuario", dao.carregar(idUsuario));
            } else {
                request.setAttribute("usuario", dao.carregar(Integer.parseInt(request.getParameter("idUsuario"))));
            }
            request.getRequestDispatcher("usuario/salvar.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Erro ao carregar o usuário.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void salvar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, FileUploadException {
        Boolean sucesso = false;
        Usuario oUsuario = new Usuario();

        try {
            if (ServletFileUpload.isMultipartContent(request)) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(50 * 1024 * 1024); // 50mb
                List items = upload.parseRequest(request);
                Iterator it = items.iterator();

                while (it.hasNext()) {
                    FileItem fileItem = (FileItem) it.next();
                    if (!fileItem.isFormField()) {
                        if (fileItem.getSize() > 0) {
                            try {   
                                String arquivo = String.valueOf(oUsuario.getIdUsuario()) + "." + fileItem.getContentType().split("/")[1]; 
                                String caminho = "/TimeStorage/images/profile/" + arquivo;
                                fileItem.write(new File(caminho));
                                oUsuario.setFotoPerfilUsuario(arquivo);
                            } catch (Exception ex) {
                                System.out.println("Problemas ao fazer o upload do arquivo. Erro: " + ex.getMessage());
                                ex.printStackTrace();
                            }
                        }

                    } else {
                        switch (fileItem.getFieldName()) {
                            case "idUsuario":
                                oUsuario.setIdUsuario(Integer.parseInt(fileItem.getString()));
                                break;

                            case "idPessoa":
                                oUsuario.setIdPessoa(Integer.parseInt(fileItem.getString()));
                                break;

                            case "tipoUsuario":
                                oUsuario.setTipoUsuario(Integer.parseInt(fileItem.getString()));
                                break;

                            case "nomePessoa":
                                oUsuario.setNomePessoa(fileItem.getString());
                                break;

                            case "sobrenomePessoa":
                                oUsuario.setSobrenomePessoa(fileItem.getString());
                                break;

                            case "emailPessoa":
                                oUsuario.setEmailPessoa(fileItem.getString());
                                break;

                            case "telefonePessoa":
                                oUsuario.setTelefonePessoa(fileItem.getString());
                                break;

                            case "dataNascimentoPessoa":
                                oUsuario.setDataNascimentoPessoa(Funcoes.StringToDate(fileItem.getString()));
                                break;
                        }
                    }
                }
            }

            GenericDAO dao = new UsuarioDAOImpl();
            sucesso = dao.alterar(oUsuario);

            if (!sucesso) {
                request.setAttribute("mensagem", "Problemas ao salvar usuário!");
            }
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }

        if (sucesso) {
            request.setAttribute("mensagemSucesso", "Dados salvos com sucesso!");
        }
        carregar(request, response, oUsuario.getIdUsuario());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioCtr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioCtr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileUploadException ex) {
            Logger.getLogger(UsuarioCtr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioCtr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioCtr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileUploadException ex) {
            Logger.getLogger(UsuarioCtr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
