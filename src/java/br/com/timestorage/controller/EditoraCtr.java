/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.controller;

import br.com.timestorage.dao.EditoraDAOImpl;
import br.com.timestorage.dao.GenericDAO;
import br.com.timestorage.model.Editora;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mateus
 */
@WebServlet(name = "EditoraCtr", urlPatterns = {"/EditoraCtr"})
public class EditoraCtr extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        switch (request.getParameter("metodo")) {
            case "cadastrar":
                request.getRequestDispatcher("editora/salvar.jsp").forward(request, response);
                break;

            case "salvar":
                salvar(request, response);
                break;

            case "listar":
                listar(request, response);
                break;

            case "carregar":
                carregar(request, response);
                break;

        }
    }

    private void salvar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean sucesso = false;
        Editora oEditora = new Editora();
        oEditora.setDescricaoEditora(request.getParameter("descricaoEditora").toUpperCase());
        oEditora.setStatusEditora(Integer.parseInt(request.getParameter("statusEditora")));
        try {
            if (oEditora.getDescricaoEditora().equals("")) {
                throw new Exception("Informe a descrição da editora!");
            }

            GenericDAO dao = new EditoraDAOImpl();
            if (request.getParameter("idEditora").equals("")) {
                sucesso = dao.inserir(oEditora);
            } else {
                oEditora.setIdEditora(Integer.parseInt(request.getParameter("idEditora")));
                sucesso = dao.alterar(oEditora);
            }

            if (!sucesso) {
                request.setAttribute("mensagem", "Problemas ao salvar editora!");
            }
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }

        if (sucesso) {
            listar(request, response);
        } else {
            request.getRequestDispatcher("editora/salvar.jsp").forward(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Editora oEditora = new Editora("", 2);
        try {
            if (!(request.getParameter("filtroDescricaoEditora") == null)) {
                oEditora.setDescricaoEditora(request.getParameter("filtroDescricaoEditora").toUpperCase() + "%");
                request.setAttribute("filtroDescricaoEditora", request.getParameter("filtroDescricaoEditora"));
            }

            if (!(request.getParameter("filtroStatusEditora") == null)) {
                oEditora.setStatusEditora(Integer.parseInt(request.getParameter("filtroStatusEditora")));
                request.setAttribute("filtroStatusEditora", request.getParameter("filtroStatusEditora"));
            }

            GenericDAO dao = new EditoraDAOImpl();
            request.setAttribute("editoras", dao.listar(oEditora));
            request.getRequestDispatcher("editora/listar.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Erro ao listar as editoras.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void carregar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            GenericDAO dao = new EditoraDAOImpl();
            request.setAttribute("editora", dao.carregar(Integer.parseInt(request.getParameter("idEditora"))));
            request.setAttribute("acao", "EditoraCtr?metodo=listar");
            request.getRequestDispatcher("editora/salvar.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Erro ao carregar a editora.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
