<%-- 
    Document   : listar
    Created on : Jun 3, 2018, 11:09:21 PM
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
            if (!(request.getAttribute("filtroStatusEditora") == null)) {
                if (request.getAttribute("filtroStatusEditora").equals("0")) {
                    ativo = "selected";
                } else if (request.getAttribute("filtroStatusEditora").equals("1")) {
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
                                    Manutenção de Editoras
                                </div>                                  
                                <form name="PesquisarEditora" action="EditoraCtr?metodo=listar" method="POST">
                                    <div class="row pb-5 p-5">                                        
                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-md-5">
                                                    <div class="form-group">                                                        
                                                        <input name="filtroDescricaoEditora" placeholder="Descrição" class="form-control" type="text" value="${filtroDescricaoEditora}" maxlength="200" autofocus/>
                                                    </div>                                            
                                                </div>            

                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <select id="single-select" class="form-control" name="filtroStatusEditora">
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
                                                <c:forEach var="editora" items="${editoras}">
                                                    <tr>
                                                        <td>${editora.idEditora}</td>
                                                        <td>${editora.descricaoEditora}</td> 
                                                        <td>${editora.descricaoStatus}</td>
                                                        <td>
                                                            <a href="EditoraCtr?metodo=carregar&idEditora=${editora.idEditora}">
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
                                    <form name="CadatrarEditora" action="EditoraCtr?metodo=cadastrar" method="POST">
                                        <button type="submit" class="btn btn-success btn-block">Incluir Editora</button>                                        
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