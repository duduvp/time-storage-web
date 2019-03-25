/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.controller;

import br.com.timestorage.dao.CategoriaDAOImpl;
import br.com.timestorage.dao.GenericDAO;
import br.com.timestorage.model.Categoria;
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
@WebServlet(name = "CategoriaCtr", urlPatterns = {"/CategoriaCtr"})
public class CategoriaCtr extends HttpServlet {

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
                request.getRequestDispatcher("categoria/salvar.jsp").forward(request, response);
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
        Categoria oCategoria = new Categoria();
        oCategoria.setDescricaoCategoria(request.getParameter("descricaoCategoria").toUpperCase());
        oCategoria.setStatusCategoria(Integer.parseInt(request.getParameter("statusCategoria")));
        try {
            if (oCategoria.getDescricaoCategoria().equals("")) {
                throw new Exception("Informe a descrição da categoria!");
            }

            GenericDAO dao = new CategoriaDAOImpl();
            if (request.getParameter("idCategoria").equals("")) {
                sucesso = dao.inserir(oCategoria);
            } else {
                oCategoria.setIdCategoria(Integer.parseInt(request.getParameter("idCategoria")));
                sucesso = dao.alterar(oCategoria);
            }

            if (!sucesso) {
                request.setAttribute("mensagem", "Problemas ao salvar categoria!");
            }
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }

        if (sucesso) {
            listar(request, response);
        } else {
            request.getRequestDispatcher("categoria/salvar.jsp").forward(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Categoria oCategoria = new Categoria("", 2);
        try {
            if (!(request.getParameter("filtroDescricaoCategoria") == null)) {
                oCategoria.setDescricaoCategoria(request.getParameter("filtroDescricaoCategoria").toUpperCase() + "%");
                request.setAttribute("filtroDescricaoCategoria", request.getParameter("filtroDescricaoCategoria"));
            }

            if (!(request.getParameter("filtroStatusCategoria") == null)) {
                oCategoria.setStatusCategoria(Integer.parseInt(request.getParameter("filtroStatusCategoria")));
                request.setAttribute("filtroStatusCategoria", request.getParameter("filtroStatusCategoria"));
            }

            GenericDAO dao = new CategoriaDAOImpl();
            request.setAttribute("categorias", dao.listar(oCategoria));
            request.getRequestDispatcher("categoria/listar.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Erro ao listar as categorias.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void carregar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            GenericDAO dao = new CategoriaDAOImpl();
            request.setAttribute("categoria", dao.carregar(Integer.parseInt(request.getParameter("idCategoria"))));
            request.setAttribute("acao", "CategoriaCtr?metodo=listar");
            request.getRequestDispatcher("categoria/salvar.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Erro ao carregar a categoria.\nMensagem: " + ex.getMessage());
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
