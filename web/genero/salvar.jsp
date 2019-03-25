<%-- 
    Document   : salvar
    Created on : Jun 1, 2018, 10:22:56 PM
    Author     : mateus
--%>

<%@page import="br.com.timestorage.model.Genero"%>
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
                                    Manutenção de Gêneros
                                </div>                                  

                                <%
                                    Genero genero;
                                    String inativo = "";                                    
                                    if (!(request.getAttribute("genero") == null)) {
                                        genero = (Genero) request.getAttribute("genero");
                                        if (genero.getStatusGenero() == 1) {
                                            inativo = "selected";
                                        }
                                    }
                                %>

                                <form name="cadastrarGenero" action="${pageContext.request.contextPath}/GeneroCtr?metodo=salvar" method="POST"> 
                                    <input type="hidden" name="idGenero" value="${genero.idGenero}"/>
                                    <div class="row pb-5 p-5">                                        
                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="form-control-label">Descrição</label>
                                                        <input class="form-control" type="text" name="descricaoGenero" value="${genero.descricaoGenero}" maxlength="200" autofocus/>                                                        
                                                    </div>                                            
                                                </div>            

                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="form-control-label">Status</label>
                                                        <select id="single-select" class="form-control" name="statusGenero">
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
                                        <a href="GeneroCtr?metodo=listar">
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
