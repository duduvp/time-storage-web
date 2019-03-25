package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/cabecalho.jsp");
    _jspx_dependants.add("/alertas.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">    \n");
      out.write("        <link rel=\"stylesheet\" href=\"./utils/css/style.css\">   \n");
      out.write("        <link rel=\"stylesheet\" href=\"./assets/dist/vendor/simple-line-icons/css/simple-line-icons.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"./assets/dist//vendor/font-awesome/css/fontawesome-all.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"./assets/dist//css/styles.css\">   \n");
      out.write("        <title>Time Storage</title>\n");
      out.write("    </head>\n");
      out.write("    <body>            \n");
      out.write("        <script src=\"./assets/dist//vendor/jquery/jquery.min.js\"></script>        \n");
      out.write("        <script src=\"./assets/dist//vendor/popper.js/popper.min.js\"></script>\n");
      out.write("        <script src=\"./assets/dist//vendor/bootstrap/js/bootstrap.min.js\"></script>\n");
      out.write("        <script src=\"./assets/dist//vendor/chart.js/chart.min.js\"></script>\n");
      out.write("        <script src=\"./assets/dist//js/carbon.js\"></script>\n");
      out.write("        <script src=\"./assets/dist//js/demo.js\"></script>\n");
      out.write("        <script src=\"utils/js/funcoes.js\"></script>\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("    </head>\n");
      out.write("    <body>       \n");
      out.write("        <div class=\"modal fade\" id=\"mensagemAtencao\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\n");
      out.write("            <div class=\"modal-dialog\" role=\"document\">\n");
      out.write("                <div class=\"modal-content\">\n");
      out.write("                    <div class=\"modal-header bg-warning border-0\">\n");
      out.write("                        <h5 class=\"modal-title text-white\">Atenção</h5>\n");
      out.write("                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n");
      out.write("                            <span aria-hidden=\"true\">&times;</span>\n");
      out.write("                        </button>\n");
      out.write("                    </div>                                    \n");
      out.write("\n");
      out.write("                    <div class=\"modal-body p-5\">\n");
      out.write("                        ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${mensagem}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"modal-footer border-0\">\n");
      out.write("                        <button type=\"button\" class=\"btn btn-warning text-white\" data-dismiss=\"modal\">OK</button>                        \n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div> \n");
      out.write("\n");
      out.write("        <div class=\"modal fade\" id=\"mensagemSucesso\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\n");
      out.write("            <div class=\"modal-dialog\" role=\"document\">\n");
      out.write("                <div class=\"modal-content\">\n");
      out.write("                    <div class=\"modal-header bg-success border-0\">\n");
      out.write("                        <h5 class=\"modal-title text-white\">Sucesso</h5>\n");
      out.write("                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n");
      out.write("                            <span aria-hidden=\"true\">&times;</span>\n");
      out.write("                        </button>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"modal-body p-5\">\n");
      out.write("                        ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${mensagemSucesso}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"modal-footer border-0\">\n");
      out.write("                        <button type=\"button\" class=\"btn btn-success\" data-dismiss=\"modal\">OK</button>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>                    \n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("    </body>           \n");
      out.write("</html>\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"teste/css/main.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>    \n");
      out.write("        <header class=\"default-header\">\n");
      out.write("            <div class=\"header-wrap\">\n");
      out.write("                <div style=\"float: right;\">\n");
      out.write("                    <a href=\"galeria.jsp\" class=\"primary-btn\">Buscar por documentos<span class=\"fas fa-search\"></span></a>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </header>\n");
      out.write("        <section class=\"banner-area relative\">\n");
      out.write("            <div class=\"overlay overlay-bg\"></div>\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"row fullscreen align-items-center justify-content-between\">\n");
      out.write("                    <div class=\"col-lg-6 col-md-7 col-sm-8\">\n");
      out.write("                        <div class=\"banner-content\">\n");
      out.write("                            <h1>Explore o Acervo Digital <br> a qualquer momento</h1>\n");
      out.write("                            <p>Já estão disponíveis, para consulta, instrumentos de pesquisa e milhares de documentos. \n");
      out.write("                                Este sistema tem por finalidade facilitar a pesquisa através do meio virtual. \n");
      out.write("                            </p>\n");
      out.write("                        </div>\n");
      out.write("                        <div>\n");
      out.write("                            <img src=\"teste/img/logoteste1.png\" alt=\"\" class=\"img-fluid\" width=\"300\" height=\"200\" style=\"float: right; opacity: 0.9;\">\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-md-5\">                        \n");
      out.write("                        <div class=\"card p-4\">                            \n");
      out.write("                            <form name=\"loginUsuario\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/UsuarioCtr?metodo=login\" method=\"POST\">\n");
      out.write("                                <div class=\"card-header text-center h4 font-weight-light\" style=\"border: none; background-color: #ffffff; font-family: sans-serif;\">\n");
      out.write("                                    TimeStorage\n");
      out.write("                                </div>                                                                                         \n");
      out.write("\n");
      out.write("                                <div class=\"card-body py-5\">                                    \n");
      out.write("                                    <div class=\"form-group\">\n");
      out.write("                                        <label class=\"form-control-label\">Email</label>\n");
      out.write("                                        <div class=\"input-group mb-3\">                                        \n");
      out.write("                                            <div class=\"input-group-prepend\">\n");
      out.write("                                                <span class=\"input-group-text\"><i class=\"fa fa-user\" style=\"color: #808080;\"></i></span>\n");
      out.write("                                            </div>                                        \n");
      out.write("                                            <input type=\"email\" class=\"form-control\" name=\"emailPessoa\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${emailPessoa}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" maxlength=\"200\" required autofocus>\n");
      out.write("                                        </div> \n");
      out.write("                                    </div>\n");
      out.write("\n");
      out.write("                                    <div class=\"form-group\">\n");
      out.write("                                        <label class=\"form-control-label\">Senha</label>\n");
      out.write("                                        <div class=\"input-group mb-3\">                                        \n");
      out.write("                                            <div class=\"input-group-prepend\">\n");
      out.write("                                                <span class=\"input-group-text\"><i class=\"fa fa-lock\" style=\"color: #808080;\"></i></span>\n");
      out.write("                                            </div>                                        \n");
      out.write("                                            <input type=\"password\" class=\"form-control\" name=\"senhaUsuario\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${senhaUsuario}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" required>\n");
      out.write("                                        </div>                                                                                                                      \n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("\n");
      out.write("                                <div class=\"card-footer\">\n");
      out.write("                                    <div class=\"row\">\n");
      out.write("                                        <div class=\"col-6\">\n");
      out.write("                                            <button type=\"submit\" class=\"btn btn-primary px-5\" >Login</button>\n");
      out.write("                                        </div>\n");
      out.write("\n");
      out.write("                                        <div class=\"col-6\">\n");
      out.write("                                            <a href=\"UsuarioCtr?metodo=cadastrar\" class=\"btn btn-link\">Registrar-se</a>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                            </form>    \n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </section>\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            if (");
      out.print(request.getAttribute("mensagem") != null);
      out.write(")\n");
      out.write("                $('#mensagemAtencao').modal('show');\n");
      out.write("        </script>    \n");
      out.write("        <script src=\"teste/js/main.js\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
