package org.apache.jsp.documento;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class teste3_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

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
      out.write("<head>\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("<style>\n");
      out.write("body {font-family: Arial, Helvetica, sans-serif;}\n");
      out.write("\n");
      out.write("#myImg {\n");
      out.write("  border-radius: 5px;\n");
      out.write("  cursor: pointer;\n");
      out.write("  transition: 0.3s;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#myImg:hover {opacity: 0.7;}\n");
      out.write("\n");
      out.write("/* The Modal (background) */\n");
      out.write(".modal {\n");
      out.write("  display: none; /* Hidden by default */\n");
      out.write("  position: fixed; /* Stay in place */\n");
      out.write("  z-index: 1; /* Sit on top */\n");
      out.write("  padding-top: 100px; /* Location of the box */\n");
      out.write("  left: 0;\n");
      out.write("  top: 0;\n");
      out.write("  width: 100%; /* Full width */\n");
      out.write("  height: 100%; /* Full height */\n");
      out.write("  overflow: auto; /* Enable scroll if needed */\n");
      out.write("  background-color: rgb(0,0,0); /* Fallback color */\n");
      out.write("  background-color: rgba(0,0,0,0.9); /* Black w/ opacity */\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Modal Content (image) */\n");
      out.write(".modal-content {\n");
      out.write("  margin: auto;\n");
      out.write("  display: block;\n");
      out.write("  width: 80%;\n");
      out.write("  max-width: 700px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Caption of Modal Image */\n");
      out.write("#caption {\n");
      out.write("  margin: auto;\n");
      out.write("  display: block;\n");
      out.write("  width: 80%;\n");
      out.write("  max-width: 700px;\n");
      out.write("  text-align: center;\n");
      out.write("  color: #ccc;\n");
      out.write("  padding: 10px 0;\n");
      out.write("  height: 150px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Add Animation */\n");
      out.write(".modal-content, #caption {  \n");
      out.write("  -webkit-animation-name: zoom;\n");
      out.write("  -webkit-animation-duration: 0.6s;\n");
      out.write("  animation-name: zoom;\n");
      out.write("  animation-duration: 0.6s;\n");
      out.write("}\n");
      out.write("\n");
      out.write("@-webkit-keyframes zoom {\n");
      out.write("  from {-webkit-transform:scale(0)} \n");
      out.write("  to {-webkit-transform:scale(1)}\n");
      out.write("}\n");
      out.write("\n");
      out.write("@keyframes zoom {\n");
      out.write("  from {transform:scale(0)} \n");
      out.write("  to {transform:scale(1)}\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* The Close Button */\n");
      out.write(".close {\n");
      out.write("  position: absolute;\n");
      out.write("  top: 15px;\n");
      out.write("  right: 35px;\n");
      out.write("  color: #f1f1f1;\n");
      out.write("  font-size: 40px;\n");
      out.write("  font-weight: bold;\n");
      out.write("  transition: 0.3s;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".close:hover,\n");
      out.write(".close:focus {\n");
      out.write("  color: #bbb;\n");
      out.write("  text-decoration: none;\n");
      out.write("  cursor: pointer;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* 100% Image Width on Smaller Screens */\n");
      out.write("@media only screen and (max-width: 700px){\n");
      out.write("  .modal-content {\n");
      out.write("    width: 100%;\n");
      out.write("  }\n");
      out.write("}\n");
      out.write("</style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<img id=\"myImg\" src=\"https://www.w3schools.com/howto/img_snow_wide.jpg\" alt=\"Snow\" style=\"width:100%;max-width:300px\">\n");
      out.write("\n");
      out.write("<!-- The Modal -->\n");
      out.write("<div id=\"myModal\" class=\"modal\">\n");
      out.write("  <span class=\"close\">&times;</span>\n");
      out.write("  <img class=\"modal-content\" id=\"img01\">\n");
      out.write("  <div id=\"caption\"></div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("// Get the modal\n");
      out.write("var modal = document.getElementById('myModal');\n");
      out.write("\n");
      out.write("// Get the image and insert it inside the modal - use its \"alt\" text as a caption\n");
      out.write("var img = document.getElementById('myImg');\n");
      out.write("var modalImg = document.getElementById(\"img01\");\n");
      out.write("var captionText = document.getElementById(\"caption\");\n");
      out.write("img.onclick = function(){\n");
      out.write("  modal.style.display = \"block\";\n");
      out.write("  modalImg.src = this.src;\n");
      out.write("  captionText.innerHTML = this.alt;\n");
      out.write("}\n");
      out.write("\n");
      out.write("// Get the <span> element that closes the modal\n");
      out.write("var span = document.getElementsByClassName(\"close\")[0];\n");
      out.write("\n");
      out.write("// When the user clicks on <span> (x), close the modal\n");
      out.write("span.onclick = function() { \n");
      out.write("  modal.style.display = \"none\";\n");
      out.write("}\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("</body>\n");
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
