/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.controller;

import br.com.timestorage.dao.AutorDAOImpl;
import br.com.timestorage.dao.AutorGeneroDAOImpl;
import br.com.timestorage.dao.GenericDAO;
import br.com.timestorage.model.Autor;
import br.com.timestorage.model.AutorGenero;
import br.com.timestorage.model.Genero;
import br.com.timestorage.util.Funcoes;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mateus
 */
@WebServlet(name = "AutorCtr", urlPatterns = {"/AutorCtr"})
public class AutorCtr extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        switch (request.getParameter("metodo")) {
            case "cadastrar":
                request.getRequestDispatcher("autor/salvar.jsp").forward(request, response);
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
            throws ServletException, IOException, ParseException {
        Boolean sucesso = false;
        Autor oAutor = new Autor();
        String[] valores = null;

        oAutor.setNomeAutor(request.getParameter("nomeAutor").toUpperCase());
        oAutor.setStatusAutor(Integer.parseInt(request.getParameter("statusAutor")));
        if ((request.getParameter("dataNascimentoAutor") != null) && (!request.getParameter("dataNascimentoAutor").equals(""))) {
            oAutor.setDataNascimentoAutor(Funcoes.StringToDate(request.getParameter("dataNascimentoAutor")));
        }
        if ((request.getParameter("dataFalecimentoAutor") != null) && (!request.getParameter("dataFalecimentoAutor").equals(""))) {
            oAutor.setDataFalecimentoAutor(Funcoes.StringToDate(request.getParameter("dataFalecimentoAutor")));
        }

        if (request.getParameterValues("generos") != null) {
            valores = request.getParameterValues("generos");
        }

        try {
            if (oAutor.getNomeAutor().equals("")) {
                throw new Exception("Informe o nome do autor!");
            }

            AutorDAOImpl dao = new AutorDAOImpl();
            if (request.getParameter("idAutor").equals("")) {
                oAutor.setIdAutor(dao.inserirAutor(oAutor));

            } else {
                oAutor.setIdAutor(Integer.parseInt(request.getParameter("idAutor")));
                sucesso = dao.alterar(oAutor);
            }

            // os genêros sempre deleto todos e incluo novamente.
            if (oAutor.getIdAutor() > 0) {
                GenericDAO daoGeneros = new AutorGeneroDAOImpl();
                daoGeneros.excluir(oAutor.getIdAutor());
                if ((valores != null) && (valores.length > 0)) {
                    for (int i = 0; i < valores.length; i++) {
                        AutorGenero genero = new AutorGenero();
                        genero.setAutor(oAutor);
                        genero.setGenero(new Genero(Integer.parseInt(valores[i])));
                        daoGeneros.inserir(genero);
                    }
                }
                sucesso = true;
            }

            if (!sucesso) {
                request.setAttribute("mensagem", "Problemas ao salvar autor!");
            }
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex.getMessage());
            sucesso = false;
        }

        if (sucesso) {
            listar(request, response);
        } else {
            request.getRequestDispatcher("autor/salvar.jsp").forward(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Autor oAutor = new Autor("", 2);
        try {
            if (!(request.getParameter("filtroNomeAutor") == null)) {
                oAutor.setNomeAutor(request.getParameter("filtroNomeAutor").toUpperCase() + "%");
                request.setAttribute("filtroNomeAutor", request.getParameter("filtroNomeAutor"));
            }

            if (!(request.getParameter("filtroStatusAutor") == null)) {
                oAutor.setStatusAutor(Integer.parseInt(request.getParameter("filtroStatusAutor")));
                request.setAttribute("filtroStatusAutor", request.getParameter("filtroStatusAutor"));
            }

            GenericDAO dao = new AutorDAOImpl();
            List<Object> oListaAutores = dao.listar(oAutor);
            if ((request.getParameter("modal") != null) && (request.getParameter("modal").equals("true"))) {
                Gson oGson = new Gson();
                PrintWriter out = response.getWriter();
                out.print(oGson.toJson(oListaAutores));
            } else {
                request.setAttribute("autores", oListaAutores);
                request.getRequestDispatcher("autor/listar.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao listar os autores.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void carregar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            GenericDAO daoAutor = new AutorDAOImpl();
            if ((request.getParameter("modal") != null) && (request.getParameter("modal").equals("true"))) {
                Gson oGson = new Gson();
                PrintWriter out = response.getWriter();
                out.print(oGson.toJson(daoAutor.carregar(Integer.parseInt(request.getParameter("idAutor")))));
            } else {
                request.setAttribute("autor", daoAutor.carregar(Integer.parseInt(request.getParameter("idAutor"))));
                AutorGeneroDAOImpl daoAutorGenero = new AutorGeneroDAOImpl();
                request.setAttribute("AutorGenero", daoAutorGenero.listarAutorGenero(Integer.parseInt(request.getParameter("idAutor"))));
                request.getRequestDispatcher("autor/salvar.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao carregar o autor.\nMensagem: " + ex.getMessage());
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AutorCtr.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AutorCtr.class.getName()).log(Level.SEVERE, null, ex);
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
