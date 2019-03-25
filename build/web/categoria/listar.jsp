<%-- 
    Document   : listar
    Created on : Jun 5, 2018, 7:50:46 PM
    Author     : mateus
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
    </head>
    <body>        
        <%
            String ativo = "";
            String inativo = "";
            String todos = "";
            if (!(request.getAttribute("filtroStatusCategoria") == null)) {
                if (request.getAttribute("filtroStatusCategoria").equals("0")) {
                    ativo = "selected";
                } else if (request.getAttribute("filtroStatusCategoria").equals("1")) {
                    inativo = "selected";
                } else {
                    todos = "selected";
                }
            } else {
                todos = "selected";
            }
        %>

        <%@include file="../administrador/menu.jsp" %>

        <div class="content">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body p-0">  
                                <div class="card-header bg-light">
                                    Manutenção de Categorias
                                </div>                                  
                                <form name="PesquisarCategoria" action="CategoriaCtr?metodo=listar" method="POST">
                                    <div class="row pb-5 p-5">                                        
                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-md-5">
                                                    <div class="form-group">                                                        
                                                        <input name="filtroDescricaoCategoria" placeholder="Descrição" class="form-control" type="text" value="${filtroDescricaoCategoria}" maxlength="200" autofocus/>
                                                    </div>                                            
                                                </div>            

                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <select id="single-select" class="form-control" name="filtroStatusCategoria">
                                                            <option value="0" <%=ativo%>>Ativo</option>
                                                            <option value="1" <%=inativo%>>Inativo</option>
                                                            <option value="2" <%=todos%>>Todos</option>
                                                        </select>  
                                                    </div>                                            
                                                </div>


                                                <div class="col-md-3">                                                    
                                                    <span class="input-group-btn">                                                          
                                                        <button type="submit" class="btn btn-primary btn-block"><i class="fa fa-search"></i> Pesquisar</button>
                                                    </span>                                            
                                                </div>                                                                                                              
                                            </div>                                                                  
                                        </div>
                                    </div>
                                </form>                                                    

                                <div class="row p-5">
                                    <div class="col-md-12">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th class="border-0 text-uppercase small font-weight-bold">ID</th>
                                                    <th class="border-0 text-uppercase small font-weight-bold">Nome</th>
                                                    <th class="border-0 text-uppercase small font-weight-bold">Status</th>
                                                    <th class="border-0 text-uppercase small font-weight-bold">#</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="categoria" items="${categorias}">
                                                    <tr>
                                                        <td>${categoria.idCategoria}</td>
                                                        <td>${categoria.descricaoCategoria}</td> 
                                                        <td>${categoria.descricaoStatus}</td>
                                                        <td>
                                                            <a href="CategoriaCtr?metodo=carregar&idCategoria=${categoria.idCategoria}">
                                                                <i class="fa fa-edit"></i>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>    
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                                <div class="d-flex flex-row text-white p-4">
                                    <form name="CadatrarCategoria" action="CategoriaCtr?metodo=cadastrar" method="POST">
                                        <button type="submit" class="btn btn-success btn-block">Incluir Categoria</button>                                        
                                    </form>                                                                        
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>                                  
    </body>
</html>
