<%-- 
    Document   : menuSemLogin
    Created on : Mar 3, 2019, 9:51:55 AM
    Author     : mateus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../cabecalho.jsp" %>
    </head>
    <body class="sidebar-fixed header-fixed">
        <div class="page-header">
            <nav class="navbar page-header">
                <a href="#" class="btn btn-link sidebar-mobile-toggle d-md-none mr-auto">
                    <i class="fa fa-bars"></i>
                </a>

                <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
                    <div class="card-header text-center h4 font-weight-light">
                        TimeStorage
                    </div>                     
                </a>

                <a href="#" class="btn btn-link sidebar-toggle d-md-down-none">
                    <i class="fa fa-bars"></i>
                </a>
                    
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-user"></i>                            
                            <span class="small ml-1 d-md-down-none">Convidado</span>
                        </a>

                        <div class="dropdown-menu dropdown-menu-right">
                            <div class="dropdown-header">Conta</div>

                            <a href="${pageContext.request.contextPath}/index.jsp" class="dropdown-item">
                                <i class="fa fa-home"></i> Login
                            </a>   
                                
                            <a href="UsuarioCtr?metodo=cadastrar" class="dropdown-item">
                                <i class="fa fa-user-plus"></i> Registrar-se
                            </a>                                                                                 
                        </div>
                    </li>
                </ul>                  
            </nav>
        </div>

        <div class="main-container">
            <div class="sidebar">               
                <nav class="sidebar-nav">
                    <ul class="nav">
                        <li class="nav-title"></li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/galeria.jsp" class="nav-link">
                                <i class="fa fa-camera"></i> Galeria
                            </a>
                        </li> 
                    </ul>
                    
                </nav>
            </div> 
        </div>    
    </body>
</html>
