<%-- 
    Document   : listar
    Created on : Oct 24, 2018, 2:19:08 PM
    Author     : mateus
--%>

<%@page import="br.com.timestorage.model.DocumentoFiltro"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
    </head>
    <body>        
        <%@include file="../administrador/menu.jsp" %>

        <div class="content">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body p-0">  
                                <div class="card-header bg-light">
                                    Manutenção de Documentos
                                </div>                                  
                                <form name="PesquisarDocumento" action="DocumentoCtr?metodo=listar" method="POST">
                                    <div class="row pb-5 p-5">                                        
                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">                                                        
                                                        <input name="filtroTituloDocumento" placeholder="Título" class="form-control" type="text" value="${documentoFiltro.tituloDocumento}" maxlength="200" autofocus/>
                                                    </div>                                            
                                                </div>            

                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <select id="single-select" class="form-control" name="filtroStatusDocumento">
                                                            <option value="0" ${documentoFiltro.statusDocumento == 0 ? "selected" : ""}>Ativo</option>
                                                            <option value="1" ${documentoFiltro.statusDocumento == 1 ? "selected" : ""}>Inativo</option>
                                                            <option value="2" ${documentoFiltro.statusDocumento == 2 ? "selected" : ""}>Todos</option>
                                                        </select>  
                                                    </div>                                            
                                                </div>                                                

                                                <div class="col-md-3">                                                    
                                                    <span class="input-group-btn">                                                          
                                                        <button type="submit" class="btn btn-primary btn-block"><i class="fa fa-search"></i> Pesquisar</button>
                                                    </span>                                            
                                                </div>                                                                                                              
                                                        
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label class="form-control-label">Data Inicial</label>
                                                        <input class="form-control" type="date" name="filtroDataInicio" value="${documentoFiltro.dataInicio}"/>                                                        
                                                    </div>                                            
                                                </div>                                                        

                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label class="form-control-label">Data Final</label>
                                                        <input class="form-control" type="date" name="filtroDataFim" value="${documentoFiltro.dataFim}"/>
                                                    </div>                                            
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
                                                    <th class="border-0 text-uppercase small font-weight-bold">Título</th>
                                                    <th class="border-0 text-uppercase small font-weight-bold">Data Publicação</th>
                                                    <th class="border-0 text-uppercase small font-weight-bold">Status</th>
                                                    <th class="border-0 text-uppercase small font-weight-bold">#</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="documento" items="${documentos}">
                                                    <tr>
                                                        <td>${documento.tituloDocumento}</td>
                                                        <td>${documento.dataPublicacaoFormatada}</td> 
                                                        <td>${documento.descricaoStatus}</td>
                                                        <td>
                                                            <a href="DocumentoCtr?metodo=carregar&idDocumento=${documento.idDocumento}">
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
                                    <form name="CadatrarDocumento" action="DocumentoCtr?metodo=cadastrar" method="POST">
                                        <button type="submit" class="btn btn-success btn-block">Incluir Documento</button>                                        
                                    </form>                                                                        
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>  
                                                    
        <script type="text/javascript">
            if (<%=request.getAttribute("mensagem") != null%>)
                $('#mensagemAtencao').modal('show');
        </script> 
    </body>
</html>
