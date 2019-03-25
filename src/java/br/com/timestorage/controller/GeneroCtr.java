/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.controller;

import br.com.timestorage.dao.GenericDAO;
import br.com.timestorage.dao.GeneroDAOImpl;
import br.com.timestorage.model.Genero;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mateus
 */
@WebServlet(name = "GeneroCtr", urlPatterns = {"/GeneroCtr"})
public class GeneroCtr extends HttpServlet {

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
                request.getRequestDispatcher("genero/salvar.jsp").forward(request, response);
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
        Genero oGenero = new Genero();
        oGenero.setDescricaoGenero(request.getParameter("descricaoGenero").toUpperCase());
        oGenero.setStatusGenero(Integer.parseInt(request.getParameter("statusGenero")));
        try {
            if (oGenero.getDescricaoGenero().equals("")) {
                throw new Exception("Informe a descrição do gênero!");
            }

            GenericDAO dao = new GeneroDAOImpl();
            if (request.getParameter("idGenero").equals("")) {
                sucesso = dao.inserir(oGenero);
            } else {
                oGenero.setIdGenero(Integer.parseInt(request.getParameter("idGenero")));
                sucesso = dao.alterar(oGenero);
            }

            if (!sucesso) {
                request.setAttribute("mensagem", "Problemas ao salvar gênero!");
            }
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }

        if (sucesso) {
            listar(request, response);
        } else {
            request.getRequestDispatcher("genero/salvar.jsp").forward(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Genero oGenero = new Genero("", 2);
        try {
            if (!(request.getParameter("filtroDescricaoGenero") == null)) {
                oGenero.setDescricaoGenero(request.getParameter("filtroDescricaoGenero").toUpperCase() + "%");
                request.setAttribute("filtroDescricaoGenero", request.getParameter("filtroDescricaoGenero"));
            }

            if (!(request.getParameter("filtroStatusGenero") == null)) {
                oGenero.setStatusGenero(Integer.parseInt(request.getParameter("filtroStatusGenero")));
                request.setAttribute("filtroStatusGenero", request.getParameter("filtroStatusGenero"));
            }

            GenericDAO dao = new GeneroDAOImpl();
            List<Object> oListaGeneros = dao.listar(oGenero);
            if ((request.getParameter("modal") != null) && (request.getParameter("modal").equals("true"))) {
                Gson oGson = new Gson();
                PrintWriter out = response.getWriter();
                out.print(oGson.toJson(oListaGeneros));
            } else {
                request.setAttribute("generos", oListaGeneros);
                request.getRequestDispatcher("genero/listar.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao listar os gêneros.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void carregar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            GenericDAO dao = new GeneroDAOImpl();
            if ((request.getParameter("modal") != null) && (request.getParameter("modal").equals("true"))) {
                Gson oGson = new Gson();
                PrintWriter out = response.getWriter();
                out.print(oGson.toJson(dao.carregar(Integer.parseInt(request.getParameter("idGenero")))));
            } else {
                request.setAttribute("genero", dao.carregar(Integer.parseInt(request.getParameter("idGenero"))));
                request.setAttribute("acao", "GeneroCtr?metodo=listar");
                request.getRequestDispatcher("genero/salvar.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao carregar o gênero.\nMensagem: " + ex.getMessage());
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
