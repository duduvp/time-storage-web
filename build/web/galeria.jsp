<%-- 
    Document   : galeria
    Created on : Feb 24, 2019, 8:18:19 PM
    Author     : mateus
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
      
    </head>
    <body>
        <% if (request.getSession().getAttribute("idUsuario") == null) { %> 
            <%@include file="usuario/menuSemLogin.jsp"%>                  
        <% } else if (request.getSession().getAttribute("tipoUsuario").equals(1)) { %>
            <%@include file="administrador/menu.jsp" %>
        <% } else if (request.getSession().getAttribute("tipoUsuario").equals(0)) { %>
            <%@include file="usuario/menu.jsp" %>
        <% } %>         
        
        <div class="content">
             <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body p-0">  
                                <div class="card-header bg-light">
                                    Galeria
                                </div>     

                                <form name="galeria" action="${pageContext.request.contextPath}/DocumentoCtr?metodo=consulta" method="POST">   
                                    <div class="row pb-5 p-5">                                    
                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-md-12">                                                    
                                                    <div class="input-group">
                                                        <input type="text" name="tituloDocumento" class="form-control" value="${tituloDocumento}" placeholder="Buscar por documentos...">
                                                        <span class="input-group-btn">
                                                            <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i> Pesquisar</button>
                                                        </span>
                                                    </div>
                                                </div>
                                                
                                                <br></br>
                                                <br></br>
                                                
                                                <c:forEach var="documento" items="${documentos}">  
                                                    <div class="col-md-12">
                                                        <div class="col-md-12">
                                                            <a href="${pageContext.request.contextPath}/DocumentoCtr?metodo=visualizar&idDocumento=${documento.idDocumento}" style="color: #4169E1;"> 
                                                                <h4 style="height: 10px"> ${documento.tituloDocumento} </h4> 
                                                            </a>
                                                            <p style="font-size: 8; color: gray; height: 15px"> Data de publicação: ${documento.dataInclusaoFormatada} </p>
                                                            <p> ${documento.sinopseDocumento} </p>                                                            
                                                        </div>                                             
                                                    </div>
                                                    <div class="col-md-12" style="height: 20px">

                                                    </div>
                                                </c:forEach> 
                                            </div>
                                        </div>                                       
                                    </div>                                      

                                    <div class="card-footer bg-light text-center">
                                        <label>Copyright &copy; Todos os direitos reservados - 2018 </label>
                                    </div>
                                </form>                                                        
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>     
    </body>
</html>
