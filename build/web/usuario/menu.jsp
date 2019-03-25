<%-- 
    Document   : menu
    Created on : 12/09/2018, 08:56:18
    Author     : Cliente
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

                <a class="navbar-brand" href="PaginaInicial">
                    <div class="card-header text-center h4 font-weight-light">
                        TimeStorage
                    </div>                     
                </a>

                <a href="#" class="btn btn-link sidebar-toggle d-md-down-none">
                    <i class="fa fa-bars"></i>
                </a>

                <ul class="navbar-nav ml-auto">
                    <li class="nav-item d-md-down-none">
                        <a href="#">
                            <i class="fa fa-bell"></i>
                            <span class="badge badge-pill badge-danger">5</span>
                        </a>
                    </li>

                    <li class="nav-item d-md-down-none">
                        <a href="#">
                            <i class="fa fa-envelope-open"></i>
                            <span class="badge badge-pill badge-danger">5</span>
                        </a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-user"></i>                            
                            <span class="small ml-1 d-md-down-none"><%=request.getSession().getAttribute("nomeCompleto")%></span>
                        </a>

                        <div class="dropdown-menu dropdown-menu-right">
                            <div class="dropdown-header">Conta</div>

                            <a href="UsuarioCtr?metodo=carregar&idUsuario=<%=request.getSession().getAttribute("idUsuario")%>" class="dropdown-item">
                                <i class="fa fa-user"></i> Perfil
                            </a>

                            <a href="#" class="dropdown-item">
                                <i class="fa fa-envelope"></i> Mensagens
                            </a>

                            <div class="dropdown-header">Configurações</div>

                            <a href="#" class="dropdown-item">
                                <i class="fa fa-bell"></i> Notificações
                            </a>

                            <a href="#" class="dropdown-item">
                                <i class="fa fa-wrench"></i> Configurações
                            </a>

                            <a href="UsuarioCtr?metodo=sair" class="dropdown-item">
                                <i class="fa fa-lock"></i> Sair
                            </a>
                        </div>
                    </li>
                </ul>
            </nav>
        </div>

        <div class="main-container">
            <div class="sidebar">
                <div>
                    <center class="m-t-30">                                        
                        <div id="imagem-perfil-menu" class="perfil">
                            <% if ((request.getSession().getAttribute("fotoPerfilUsuario") != null) && (!request.getSession().getAttribute("fotoPerfilUsuario").equals(""))) {%>
                            <img src="${pageContext.request.contextPath}/SerializarImagem?metodo=profilePhoto&fotoPerfil=<%=request.getSession().getAttribute("fotoPerfilUsuario")%>" class="img-fluid rounded-circle" width="150" height="150"/>
                            <%} else {%>
                            <img src="./utils/imagens/geral/no-user.png" class="img-fluid rounded-circle" width="150" height="150"/>
                            <%}%>    
                        </div>                         
                        <h6 class="nav-title"><%=request.getSession().getAttribute("nomeCompleto")%></h6>
                    </center>
                </div>                 
                <nav class="sidebar-nav">
                    <ul class="nav">                        
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
