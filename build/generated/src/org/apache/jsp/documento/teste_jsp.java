package org.apache.jsp.documento;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class teste_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/documento/../cabecalho.jsp");
    _jspx_dependants.add("/documento/../alertas.jsp");
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
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">          \n");
      out.write("        <link rel=\"stylesheet\" href=\"./utils/css/style.css\">   \n");
      out.write("        <link rel=\"stylesheet\" href=\"./assets/dist/vendor/simple-line-icons/css/simple-line-icons.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"./assets/dist//vendor/font-awesome/css/fontawesome-all.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"./assets/dist//css/styles.css\">         \n");
      out.write("        \n");
      out.write("        <link href=\"./utils/image-view/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"./utils/image-view/css/slick.css\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"./utils/image-view/css/magnific-popup.css\">\n");
      out.write("        <link href=\"./utils/image-view/css/main.css\" rel=\"stylesheet\">\n");
      out.write("        \n");
      out.write("        <title>Time Storage</title>\n");
      out.write("    </head>\n");
      out.write("    <body>            \n");
      out.write("        <script src=\"./assets/dist//vendor/jquery/jquery.min.js\"></script>        \n");
      out.write("        <script src=\"./assets/dist//vendor/popper.js/popper.min.js\"></script>\n");
      out.write("        <script src=\"./assets/dist//vendor/bootstrap/js/bootstrap.min.js\"></script>\n");
      out.write("        <script src=\"./assets/dist//vendor/chart.js/chart.min.js\"></script>\n");
      out.write("        <script src=\"./assets/dist//js/carbon.js\"></script>\n");
      out.write("        <script src=\"./assets/dist//js/demo.js\"></script>\n");
      out.write("        <script src=\"utils/js/funcoes.js\"></script>     \n");
      out.write("\n");
      out.write("        <script src=\"./utils/image-view/js/jquery.min.js\"></script>\n");
      out.write("        <script src=\"./utils/image-view/js/popper.js\"></script>\n");
      out.write("        <script src=\"./utils/image-view/js/bootstrap.min.js\"></script>\n");
      out.write("        <script src=\"./utils/image-view/js/waypoints.min.js\"></script>\n");
      out.write("        <script src=\"./utils/image-view/js/slick.min.js\"></script>\n");
      out.write("        <script src=\"./utils/image-view/js/imgloaded.js\"></script>\n");
      out.write("        <script src=\"./utils/image-view/js/isotope.js\"></script>\n");
      out.write("        <script src=\"./utils/image-view/js/jquery.magnific-popup.min.js\"></script>\n");
      out.write("        <script src=\"./utils/image-view/js/jquery.counterup.min.js\"></script>\n");
      out.write("        <script src=\"./utils/image-view/js/wow.min.js\"></script>\n");
      out.write("        <script src=\"./utils/image-view/js/main.js\"></script>\n");
      out.write("        \n");
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
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"grid img-container justify-content-center no-gutters\">\n");
      out.write("            <div class=\"grid-sizer col-sm-12 col-md-6 col-lg-3\"></div>\n");
      out.write("            <div class=\"grid-item  branding architecture  col-sm-6 col-md-3\">\n");
      out.write("                <a href=\"../utils/imagens/geral/logo-timestorage.png\" title=\"\">\n");
      out.write("                    <div class=\"project_box_one\">\n");
      out.write("                        <img src=\"../utils/imagens/geral/logo-timestorage.png\"/>\n");
      out.write("                        <div class=\"product_info\">\n");
      out.write("                            <div class=\"product_info_text\">\n");
      out.write("                                <div class=\"product_info_text_inner\">\n");
      out.write("                                    <i class=\"ion ion-plus\"></i>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </a>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"grid-item  design col-sm-12 col-md-6 col-lg-3\">\n");
      out.write("                <a href=\"../utils/imagens/geral/no-image.jpg\" title=\"\">\n");
      out.write("                    <div class=\"project_box_one\">\n");
      out.write("                        <img src=\"../utils/imagens/geral/no-image.jpg\"/>\n");
      out.write("                        <div class=\"product_info\">\n");
      out.write("                            <div class=\"product_info_text\">\n");
      out.write("                                <div class=\"product_info_text_inner\">\n");
      out.write("                                    <i class=\"ion ion-plus\"></i>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </a>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"grid-item  photography design col-sm-12 col-md-6 col-lg-3\">\n");
      out.write("                <a href=\"../utils/imagens/geral/no-user.png\" title=\"\">\n");
      out.write("                    <div class=\"project_box_one\">\n");
      out.write("                        <img src=\"../utils/imagens/geral/no-user.png\"/>\n");
      out.write("                        <div class=\"product_info\">\n");
      out.write("                            <div class=\"product_info_text\">\n");
      out.write("                                <div class=\"product_info_text_inner\">\n");
      out.write("                                    <i class=\"ion ion-plus\"></i>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </a>\n");
      out.write("            </div>\n");
      out.write("        </div>  \n");
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
