<%-- 
    Document   : salvar
    Created on : Sep 18, 2018, 1:21:23 PM
    Author     : mateus
--%>

<%@page import="java.util.List"%>
<%@page import="br.com.timestorage.model.AutorGenero"%>
<%@page import="br.com.timestorage.model.Autor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <%@include file="../administrador/menu.jsp" %>
        <%@include file="../genero/pesquisar.jsp" %>

        <div class="content">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body p-0">  
                                <div class="card-header bg-light">
                                    Manutenção de Autores
                                </div>                                  

                                <%
                                    Autor oAutor = null;
                                    List<AutorGenero> oAutorGenero = null;
                                    String inativo = "";
                                    if (!(request.getAttribute("autor") == null)) {
                                        oAutor = (Autor) request.getAttribute("autor");
                                        if (oAutor.getStatusAutor() == 1) {
                                            inativo = "selected";
                                        }
                                    }

                                    if (!(request.getAttribute("AutorGenero") == null)) {
                                        oAutorGenero = (List<AutorGenero>) request.getAttribute("AutorGenero");
                                    }
                                %>

                                <form name="cadastrarAutor" action="${pageContext.request.contextPath}/AutorCtr?metodo=salvar" method="POST">
                                    <input type="hidden" name="idAutor" value="${autor.idAutor}"/>
                                    <div class="row pb-5 p-5">                                        
                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="form-control-label">Nome</label>
                                                        <input class="form-control" type="text" name="nomeAutor" value="${autor.nomeAutor}" maxlength="200" autofocus/>                                                        
                                                    </div>                                            
                                                </div>            

                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="form-control-label">Status</label>
                                                        <select id="single-select" class="form-control" name="statusAutor">
                                                            <option value="0">Ativo</option>
                                                            <option value="1" <%=inativo%>>Inativo</option>
                                                        </select>                                                         
                                                    </div>                                            
                                                </div>                                                                                                            
                                            </div>                                                                  
                                        </div>

                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="form-control-label">Data Nascimento</label>
                                                        <input class="form-control" type="date" name="dataNascimentoAutor" value="${autor.dataNascimentoAutor}"/>                                                        
                                                    </div>                                            
                                                </div>            

                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="form-control-label">Data Falecimento</label>
                                                        <input class="form-control" type="date" name="dataFalecimentoAutor" value="${autor.dataFalecimentoAutor}"/>                                                         
                                                    </div>                                            
                                                </div>                                                                                                            
                                            </div>                                                                  
                                        </div>   

                                        <div class="col-md-6">
                                            <div class="row">                                                    
                                                <div class="col-md-12">
                                                    <label class="form-control-label">Gêneros</label>
                                                    <div class="form-group">                                                        
                                                        <table class="table" id="tabelaGeneros">
                                                            <thead>
                                                                <tr>
                                                                    <th class="border-0 text-uppercase small font-weight-bold" hidden></th>   
                                                                    <th class="border-0 text-uppercase small font-weight-bold">ID</th>
                                                                    <th class="border-0 text-uppercase small font-weight-bold">Nome</th>                                                                    
                                                                    <th class="border-0 text-uppercase small font-weight-bold">#</th>                                                                    
                                                                </tr>
                                                            </thead>
                                                            <tbody id="itensGeneros">
                                                                <% if ((oAutorGenero != null) && (oAutorGenero.size() > 0)) {%>    
                                                                <c:forEach var="autorGenero" items="${AutorGenero}">
                                                                    <tr>
                                                                        <td hidden><input type="hidden" name="generos" value="${autorGenero.genero.idGenero}" /></td>                                                                        
                                                                        <td>${autorGenero.genero.idGenero}</td>
                                                                        <td>${autorGenero.genero.descricaoGenero}</td>                                                                         
                                                                        <td>
                                                                            <button class="btn btn-light" onclick="deleteRow(this);">
                                                                                <i class="fa fa-trash"></i>
                                                                            </button>
                                                                        </td>                                                                        
                                                                    </tr>
                                                                </c:forEach>
                                                                <%}%>
                                                            </tbody>
                                                        </table>
                                                    </div>                                            
                                                </div> 
                                                <div class="col-md-4">                                                    
                                                    <span class="input-group-btn">                                                          
                                                        <button type="button" data-toggle="modal" data-target="#pesquisarGenero" class="btn btn-secondary btn-block">
                                                            Adicionar
                                                        </button>
                                                    </span>                                            
                                                </div>                                                            
                                            </div>
                                        </div>    
                                    </div>

                                    <div class="card-footer bg-light text-center">                                                                                                                                                                       
                                        <button type="submit" class="btn btn-primary" style="width: 120px">Salvar</button>
                                        <a href="AutorCtr?metodo=listar">
                                            <button type="button" class="btn btn-danger" style="width: 120px">Cancelar</button>
                                        </a>
                                    </div>
                                </form>                                                        
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript">
            if (<%=request.getAttribute("mensagem") != null%>)
                $('#mensagemAtencao').modal('show');

            function deleteRow(selectedRow) {
                var i = selectedRow.parentNode.parentNode.rowIndex;
                document.getElementById("tabelaGeneros").deleteRow(i);
            }
        </script>         
    </body>
</html>
