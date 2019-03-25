
<%-- 
    Document   : galeria
    Created on : Feb 24, 2019, 8:18:19 PM
    Author     : mateus
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="./utils/image-view/css/magnific-popup.css">      
        <link rel="stylesheet" href="./utils/css/style.css">           
    </head>
    <body>      


        <% if (request.getSession().getAttribute("idUsuario") == null) { %> 
        <%@include file="../usuario/menuSemLogin.jsp"%>         
        <% } else if (request.getSession().getAttribute("tipoUsuario").equals(1)) {
        %>
        <%@include file="../administrador/menu.jsp" %>
        <% } else if (request.getSession().getAttribute("tipoUsuario").equals(0)) {
        %>
        <%@include file="../usuario/menu.jsp" %>
        <% }%>         


        <div class="content">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body p-0">
                                <form name="comentario" action="${pageContext.request.contextPath}/DocumentoCtr?metodo=comentar" method="POST"> 
                                    <input type="hidden" name="idDocumento" value="${documento.idDocumento}"/>
                                    <div class="row pb-5 p-5">                                    
                                        <div class="col-md-12">
                                            <div class="row">
                                                <h3> ${documento.tituloDocumento} </h3>
                                            </div>                                            
                                            <div class="row">
                                                <p style="font-size: 8; color: gray; height: 15px"> Data de publicação: ${documento.dataInclusaoFormatada} </p>
                                            </div>          

                                            <div class="row">
                                                <p> ${documento.sinopseDocumento} </p>                                                
                                            </div>                                      
                                        </div>                                                                          
                                        <br></br>                                        
                                        <div class="col-md-12">                                                                                             
                                            <div class="row">
                                                <div class="col-md-12">                                                                                                                                                           
                                                    <div class="grid img-container justify-content-center no-gutters">
                                                        <div class="grid-sizer col-sm-0 col-md-0 col-lg-0"></div>                                            
                                                        <c:forEach var="imagem" items="${imagensDocumento}">   
                                                            <div class="col-md-4">
                                                                <div class="visualizar">                                                                          
                                                                    <div class="grid-item">
                                                                        <a href="${pageContext.request.contextPath}/SerializarImagem?metodo=imagemDocumento&caminho=${imagem.caminhoImagem}" title="">
                                                                            <div class="project_box_ones">
                                                                                <img src="${pageContext.request.contextPath}/SerializarImagem?metodo=imagemDocumento&caminho=${imagem.caminhoImagem}"/>
                                                                                <div class="product_info">
                                                                                    <div class="product_info_text">
                                                                                        <div class="product_info_text_inner">
                                                                                            <i class="ion ion-plus"></i>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </a>
                                                                    </div> 
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </div>      
                                                </div>                                              
                                            </div>

                                            <br></br>

                                            <div class="col-md-12">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <h4 class="card-title"> <b> Comentários </b> </h4>
                                                    </div>
                                                    <div class="comment-widgets">
                                                        <c:choose>
                                                            <c:when test="${fn:length(comentarios) eq 0}">
                                                                <div class="card-body">
                                                                    <h6 class="card-title"> <b> Nenhum comentário ainda, seja o primeiro a comentar. </b> </h6>                                                        
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:forEach var="comentario" items="${comentariosDocumento}">                                                        
                                                                    <div class="d-flex flex-row comment-row m-t-0">
                                                                        <div class="p-2">
                                                                            <img src="${pageContext.request.contextPath}/SerializarImagem?metodo=profilePhoto&fotoPerfil=${comentario.usuario.fotoPerfilUsuario}" alt="user" width="50" class="rounded-circle">
                                                                        </div>
                                                                        <div class="comment-text w-100">
                                                                            <b><span class="m-b-15 d-block">${comentario.usuario.nomePessoa} ${comentario.usuario.sobrenomePessoa}</span></b>
                                                                            <span class="m-b-15 d-block">${comentario.textoComentario} </span>
                                                                            <div class="comment-footer">
                                                                                <span class="text-muted">${comentario.dataComentarioFormatada}</span>
                                                                                <c:if test="${comentario.usuario.idUsuario == idUsuarioLogado}">
                                                                                    <a href="DocumentoCtr?metodo=removerComentario&idComentario=${comentario.idComentario}&idDocumento=${documento.idDocumento}">
                                                                                        <i class="fa fa-trash"></i>
                                                                                    </a>
                                                                                </c:if>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <br></br>
                                                                </c:forEach>                                                              
                                                            </c:otherwise>
                                                        </c:choose>                                                      
                                                    </div>
                                                </div>
                                            </div>  

                                            <div class="col-md-12">                                                    
                                                <div class="input-group">
                                                    <input type="text" name="textoComentario" class="form-control" value="${textoComentario}" placeholder="Escreva um comentário...">
                                                    <span class="input-group-btn">
                                                        <button type="submit" class="btn btn-primary">Enviar</button>
                                                    </span>
                                                </div>
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

        <script src="./utils/image-view/js/jquery.min.js"></script>
        <script src="./utils/image-view/js/popper.js"></script>
        <script src="./utils/image-view/js/bootstrap.min.js"></script>
        <script src="./utils/image-view/js/waypoints.min.js"></script>
        <script src="./utils/image-view/js/slick.min.js"></script>
        <script src="./utils/image-view/js/imgloaded.js"></script>
        <script src="./utils/image-view/js/isotope.js"></script>
        <script src="./utils/image-view/js/jquery.magnific-popup.min.js"></script>
        <script src="./utils/image-view/js/jquery.counterup.min.js"></script>
        <script src="./utils/image-view/js/wow.min.js"></script>
        <script src="./utils/image-view/js/main.js"></script>                                                    
    </body>
</html>
