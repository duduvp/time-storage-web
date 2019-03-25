/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.controller;

import br.com.timestorage.dao.CategoriaDAOImpl;
import br.com.timestorage.dao.ComentarioDAOImpl;
import br.com.timestorage.dao.DocumentoAutorDAOImpl;
import br.com.timestorage.dao.DocumentoDAOImpl;
import br.com.timestorage.dao.EditoraDAOImpl;
import br.com.timestorage.dao.GenericDAO;
import br.com.timestorage.dao.ImagemDAOImpl;
import br.com.timestorage.model.Autor;
import br.com.timestorage.model.Categoria;
import br.com.timestorage.model.Comentario;
import br.com.timestorage.model.Documento;
import br.com.timestorage.model.DocumentoAutor;
import br.com.timestorage.model.DocumentoFiltro;
import br.com.timestorage.model.Editora;
import br.com.timestorage.model.Imagem;
import br.com.timestorage.model.Usuario;
import br.com.timestorage.util.Funcoes;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author mateus
 */
@WebServlet(name = "DocumentoCtr", urlPatterns = {"/DocumentoCtr"})
public class DocumentoCtr extends HttpServlet {

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
            throws ServletException, IOException, ParseException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        switch (request.getParameter("metodo")) {
            case "cadastrar":
                GenericDAO daoCategoria = new CategoriaDAOImpl();
                request.setAttribute("categorias", daoCategoria.listar(new Categoria("", 0)));

                GenericDAO daoEditora = new EditoraDAOImpl();
                request.setAttribute("editoras", daoEditora.listar(new Editora("", 0)));
                request.getRequestDispatcher("documento/salvar.jsp").forward(request, response);
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

            case "consulta":
                consultar(request, response);
                break;

            case "visualizar":
                visualizar(request, response);
                break;

            case "comentar":
                comentar(request, response);
                break;
                
            case "removerComentario":
                removerComentario(request, response);
                break;

        }
    }

    private void salvar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Boolean sucesso = false;
        Documento oDocumento = new Documento(0);
        List listaAutores = new ArrayList();
        List<Object> oImagensDocumento = new ArrayList<>();
        int idDocumento = 0;

        try {
            ImagemDAOImpl daoImagem = new ImagemDAOImpl();
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
                                if (idDocumento == 0) {
                                    DocumentoDAOImpl daoCodigo = new DocumentoDAOImpl();
                                    idDocumento = daoCodigo.GerarCodigo();
                                } else if (oDocumento.getIdDocumento() > 0) {
                                    idDocumento = oDocumento.getIdDocumento();
                                }

                                Imagem oImagem = new Imagem();
                                oImagem.setIdImagem(daoImagem.GerarCodigo());
                                oImagem.setDocumento(oDocumento);
                                String arquivo = String.valueOf(oImagem.getIdImagem()) + "." + fileItem.getContentType().split("/")[1];
                                String caminho = "/TimeStorage/images/documents/" + String.valueOf(idDocumento);
                                File diretorio = new File(caminho);
                                if (!diretorio.exists()) {
                                    diretorio.mkdirs(); //mkdir() cria somente um diretório, mkdirs() cria diretórios e subdiretórios.
                                }
                                oImagem.setCaminhoImagem(caminho + "/" + arquivo);
                                fileItem.write(new File(caminho + "/" + arquivo));
                                oImagensDocumento.add(oImagem);
                            } catch (Exception ex) {
                                System.out.println("Problemas ao fazer o upload do arquivo. Erro: " + ex.getMessage());
                                ex.printStackTrace();
                            }
                        }

                    } else {
                        switch (fileItem.getFieldName()) {
                            case "idDocumento":
                                if ((fileItem.getString() != null) && (!fileItem.getString().equals(""))) {
                                    oDocumento.setIdDocumento(Integer.parseInt(fileItem.getString()));
                                }
                                break;

                            case "tituloDocumento":
                                oDocumento.setTituloDocumento(fileItem.getString());
                                break;

                            case "sinopseDocumento":
                                oDocumento.setSinopseDocumento(fileItem.getString());
                                break;

                            case "dataPublicacaoDocumento":
                                if ((fileItem.getString() != null) && (!fileItem.getString().equals(""))) {
                                    oDocumento.setDataPublicacaoDocumento(Funcoes.StringToDate(fileItem.getString()));
                                }
                                break;

                            case "statusDocumento":
                                oDocumento.setStatusDocumento(Integer.parseInt(fileItem.getString()));
                                break;

                            case "categoriaDocumento":
                                oDocumento.setCategoria(new Categoria(Integer.parseInt(fileItem.getString())));
                                break;

                            case "editoraDocumento":
                                oDocumento.setEditora(new Editora(Integer.parseInt(fileItem.getString())));
                                break;

                            case "autores":
                                if (fileItem.getString() != null) {
                                    listaAutores.add(fileItem.getString());
                                }
                                break;
                        }
                    }
                }
            }

            oDocumento.setDataInclusaoDocumento(Funcoes.getPegaDataAtual());
            oDocumento.setUsuario(new Usuario(Integer.parseInt(request.getSession().getAttribute("idUsuario").toString())));

            if (oDocumento.getTituloDocumento().equals("")) {
                throw new Exception("Informe o título do documento!");
            }

            if (oDocumento.getSinopseDocumento().equals("")) {
                throw new Exception("Informe uma sínopse do documento!");
            }

            DocumentoDAOImpl dao = new DocumentoDAOImpl();
            if (oDocumento.getIdDocumento() == 0) {
                oDocumento.setIdDocumento(idDocumento);
                if (idDocumento == 0) {
                    oDocumento.setIdDocumento(dao.GerarCodigo());
                }
                sucesso = dao.inserir(oDocumento);
            } else {
                sucesso = dao.alterar(oDocumento);
            }

            // os autores sempre deleto todos e incluo novamente.
            if (oDocumento.getIdDocumento() > 0) {
                GenericDAO daoAutores = new DocumentoAutorDAOImpl();
                daoAutores.excluir(oDocumento.getIdDocumento());
                if (listaAutores.size() > 0) {
                    for (int i = 0; i < listaAutores.size(); i++) {
                        DocumentoAutor autor = new DocumentoAutor();
                        autor.setDocumento(oDocumento);
                        autor.setAutor(new Autor(Integer.parseInt(listaAutores.get(i).toString())));
                        daoAutores.inserir(autor);
                    }
                }

                if (oImagensDocumento.size() > 0) {
                    for (int i = 0; i < oImagensDocumento.size(); i++) {
                        daoImagem.inserir(oImagensDocumento.get(i));
                    }
                }
                sucesso = true;
            }

            if (!sucesso) {
                request.setAttribute("mensagem", "Problemas ao salvar documento!");
            }
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }

        if (sucesso) {
            request.getRequestDispatcher("documento/listar.jsp").forward(request, response);
            listar(request, response);
        } else {
            request.setAttribute("Documento", oDocumento);
            request.getRequestDispatcher("documento/salvar.jsp").forward(request, response);
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
        } catch (Exception ex) {
            Logger.getLogger(DocumentoCtr.class.getName()).log(Level.SEVERE, null, ex);
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
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DocumentoCtr.class.getName()).log(Level.SEVERE, null, ex);
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

    private void listar(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        DocumentoFiltro oDocumentoFiltro = new DocumentoFiltro("", 2, null, null);
        Boolean consultar = true;
        try {
            if ((request.getParameter("filtroTituloDocumento") != null) && !(request.getParameter("filtroTituloDocumento").equals(""))) {
                oDocumentoFiltro.setTituloDocumento(request.getParameter("filtroTituloDocumento"));
            }

            if ((request.getParameter("filtroStatusDocumento") != null)) {
                oDocumentoFiltro.setStatusDocumento(Integer.parseInt(request.getParameter("filtroStatusDocumento")));
            }

            if ((request.getParameter("filtroDataInicio") != null) && (!request.getParameter("filtroDataInicio").equals(""))
                    && (request.getParameter("filtroDataFim") != null) && (!request.getParameter("filtroDataFim").equals(""))) {
                oDocumentoFiltro.setDataInicio(Funcoes.StringToDate(request.getParameter("filtroDataInicio")));
                oDocumentoFiltro.setDataFim(Funcoes.StringToDate(request.getParameter("filtroDataFim")));
                if (oDocumentoFiltro.getDataInicio().compareTo(oDocumentoFiltro.getDataFim()) > 0) {
                    request.setAttribute("mensagem", "Data inicial maior que a data final!");
                    consultar = false;
                }
            }

            request.setAttribute("documentoFiltro", oDocumentoFiltro);

            if (request.getParameter("consultar") != null) {
                consultar = Boolean.parseBoolean(request.getParameter("consultar"));
            }

            if (consultar) {
                GenericDAO dao = new DocumentoDAOImpl();
                request.setAttribute("documentos", dao.listar(oDocumentoFiltro));
            }
            request.getRequestDispatcher("documento/listar.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Erro ao listar os documento.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void carregar(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            GenericDAO daoDocumento = new DocumentoDAOImpl();
            request.setAttribute("documento", daoDocumento.carregar(Integer.parseInt(request.getParameter("idDocumento"))));

            DocumentoAutorDAOImpl daoDocumentoAutor = new DocumentoAutorDAOImpl();
            request.setAttribute("DocumentoAutor", daoDocumentoAutor.listarDocumentoAutor(Integer.parseInt(request.getParameter("idDocumento"))));

            GenericDAO daoCategoria = new CategoriaDAOImpl();
            request.setAttribute("categorias", daoCategoria.listar(new Categoria("", 0)));

            GenericDAO daoEditora = new EditoraDAOImpl();
            request.setAttribute("editoras", daoEditora.listar(new Editora("", 0)));

            ImagemDAOImpl daoImagens = new ImagemDAOImpl();
            request.setAttribute("ImagensDocumento", daoImagens.listarImagensDocumento(Integer.parseInt(request.getParameter("idDocumento"))));
            request.getRequestDispatcher("documento/salvar.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Erro ao carregar o documento.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("empty-statement")
    private void consultar(HttpServletRequest request, HttpServletResponse response) {
        String titulo = "";
        try {
            if ((request.getParameter("tituloDocumento") != null) && (!request.getParameter("tituloDocumento").equals(""))) {
                titulo = request.getParameter("tituloDocumento").toUpperCase();
            }

            if ((titulo.equals("")) && (request.getParameter("origem") != null) && (request.getParameter("origem").equals("login"))) {
                request.getRequestDispatcher("galeria.jsp").forward(request, response);
            } else {
                DocumentoDAOImpl dao = new DocumentoDAOImpl();
                request.setAttribute("documentos", dao.consultar(titulo));
                request.setAttribute("tituloDocumento", request.getParameter("tituloDocumento"));
                request.getRequestDispatcher("galeria.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao listar os documentos.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void visualizar(HttpServletRequest request, HttpServletResponse response) {
        try {
            ImagemDAOImpl daoImagens = new ImagemDAOImpl();
            ComentarioDAOImpl daoComentarios = new ComentarioDAOImpl();
            DocumentoDAOImpl dao = new DocumentoDAOImpl();
            request.setAttribute("documento", dao.carregarDocumento(Integer.parseInt(request.getParameter("idDocumento")), false));
            request.setAttribute("imagensDocumento", daoImagens.listarImagensDocumento(Integer.parseInt(request.getParameter("idDocumento"))));
            request.setAttribute("comentariosDocumento", daoComentarios.listarComentarios(Integer.parseInt(request.getParameter("idDocumento"))));
            request.setAttribute("idUsuarioLogado", (int) request.getSession().getAttribute("idUsuario"));                    
            request.getRequestDispatcher("documento/visualizar.jsp").forward(request, response);

            /*String targetList = null;
            String viewList = null;                  
            List<Imagem> oListaImagens = daoImagens.listarImagensDocumento(Integer.parseInt(request.getParameter("idDocumento")));            
            for (int i=0; i<oListaImagens.size(); i++){
                if (i == 0){
                    targetList = "<li data-target=\"#myCarousel\" data-slide-to=\"" + i + "\" class=\"active\" ></li> \n";
                    viewList = "<div class=\"carousel-item active\" style=\"height: 400px;\">\n";
                }
                else{
                    targetList = targetList +
                        "<li data-target=\"#myCarousel\" data-slide-to=\"" + i + "\"></li>\n";
                    viewList = viewList +
                        "<div class=\"carousel-item\" style=\"height: 400px\">";    
                }
                
                viewList = viewList +
                    "   <div class=\"container\">\n" +
                    "     <div class=\"carousel-caption text-center\">\n" +
                    "          <div class=\"visualizar\">\n" +
                    "              <img src=\"${pageContext.request.contextPath}/SerializarImagem?metodo=imagemDocumento&caminho=${" + oListaImagens.get(i).getCaminhoImagem() + "}\"/> \n" +
                    "        </div>  \n" +
                    "      </div>\n" +
                    "   </div>\n" +
                    "</div>";
             
            }      
            
            String carrouselContent = 
                    "<div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\"> \n" +
                    "   <ol class=\"carousel-indicators\"> \n" +
                            targetList +
                    "   </ol>\n" +
                    "   <div class=\"carousel-inner\"> \n" +
                            viewList +
                    "   </div>\n" +
                    "   <a class=\"carousel-control-prev\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\">\n" +
                    "       <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>\n" +
                    "       <span class=\"sr-only\">Previous</span>\n" +
                    "   </a>\n" +
                    "   <a class=\"carousel-control-next\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\">\n" +
                    "       <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>\n" +
                    "       <span class=\"sr-only\">Next</span>\n" +
                    "   </a>\n" +
                    "</div>";            
            request.setAttribute("carrousel", carrouselContent);*/
        } catch (Exception ex) {
            System.out.println("Erro ao visualizar o documento.\nMensagem: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void comentar(HttpServletRequest request, HttpServletResponse response) {
        Comentario oComentario = new Comentario();
        try {
            if ((request.getParameter("textoComentario") != null) && !(request.getParameter("textoComentario").equals(""))) {
                oComentario.setTextoComentario(request.getParameter("textoComentario"));
                oComentario.setUsuario(new Usuario((int) request.getSession().getAttribute("idUsuario")));                
                oComentario.setDocumento(new Documento(Integer.parseInt(request.getParameter("idDocumento"))));
                oComentario.setStatusComentario(0);
                GenericDAO dao = new ComentarioDAOImpl();
                dao.inserir(oComentario);
                request.setAttribute("idDocumento", request.getParameter("idDocumento"));
                visualizar(request, response);
            } else {
                request.setAttribute("idDocumento", request.getParameter("idDocumento"));
                visualizar(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }
    }

    private void removerComentario(HttpServletRequest request, HttpServletResponse response) {
        try {
            GenericDAO dao = new ComentarioDAOImpl();
            dao.excluir(Integer.parseInt(request.getParameter("idComentario")));
            request.setAttribute("idDocumento", request.getParameter("idDocumento"));
            visualizar(request, response);
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }
    }

}
