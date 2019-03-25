<%-- 
    Document   : salvar
    Created on : Jun 5, 2018, 7:48:17 PM
    Author     : mateus
--%>

<%@page import="br.com.timestorage.model.Categoria"%>
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
                                    Manutenção de Categorias
                                </div>                                  

                                <%
                                    Categoria categoria;
                                    String inativo = "";                                    
                                    if (!(request.getAttribute("categoria") == null)) {
                                        categoria = (Categoria) request.getAttribute("categoria");
                                        if (categoria.getStatusCategoria() == 1) {
                                            inativo = "selected";
                                        }
                                    }
                                %>

                                <form name="cadastrarCategoria" action="${pageContext.request.contextPath}/CategoriaCtr?metodo=salvar" method="POST">
                                    <input type="hidden" name="idCategoria" value="${categoria.idCategoria}"/>
                                    <div class="row pb-5 p-5">                                        
                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="form-control-label">Descrição</label>
                                                        <input class="form-control" type="text" name="descricaoCategoria" value="${categoria.descricaoCategoria}" maxlength="200" autofocus/>                                                        
                                                    </div>                                            
                                                </div>            

                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="form-control-label">Status</label>
                                                        <select id="single-select" class="form-control" name="statusCategoria">
                                                            <option value="0">Ativo</option>
                                                            <option value="1" <%=inativo%>>Inativo</option>
                                                        </select>                                                         
                                                    </div>                                            
                                                </div>                                                                                                            
                                            </div>                                                                  
                                        </div>
                                    </div>

                                    <div class="card-footer bg-light text-center">                                    
                                        <button type="submit" class="btn btn-primary" style="width: 120px">Salvar</button>  
                                        <a href="CategoriaCtr?metodo=listar">
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
        </script>             
    </body>
</html>
