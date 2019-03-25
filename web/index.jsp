<%-- 
    Document   : index
    Created on : Feb 27, 2019, 10:48:00 AM
    Author     : mateus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="cabecalho.jsp" %>        
    </head>
    <body>              
        <div class="page-header">
            <nav class="navbar page-header">    
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown">
                        <div class="header-wrap">
                            <div style="float: right;">
                                <div class="input-group">
                                    <input type="text" id="tituloDocumento" name="tituloDocumento" class="form-control" placeholder="Buscar por documentos...">
                                    <span class="input-group-btn">
                                        <button type="submit" class="btn btn-primary" onClick="javascript:window.location = '${pageContext.request.contextPath}/DocumentoCtr?metodo=consulta&origem=login&tituloDocumento=' + document.getElementById('tituloDocumento').value"><i class="fa fa-search"></i> </button>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </nav>
        </div>


        <div class="card">                             
            <div class="card-body">
                <form name="loginUsuario" action="${pageContext.request.contextPath}/UsuarioCtr?metodo=login" method="POST">       
                    <div class="page-wrapper flex-row align-items-center">
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-md-5">
                                    <div class="card p-4">                                                                                                     
                                        <div class="card-header text-center h4 font-weight-light">
                                            Acessar o sistema
                                        </div>

                                        <div class="card-body">
                                            <div class="form-group">
                                                <label class="form-control-label">Email</label>
                                                <div class="input-group mb-3">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">
                                                            <i class="fa fa-user"></i>
                                                        </span>
                                                    </div>                                                
                                                    <input type="email" name="emailPessoa" value="${emailPessoa}" maxlength="200" required autofocus class="form-control">
                                                </div>    
                                            </div>

                                            <div class="form-group">
                                                <label class="form-control-label">Senha</label>
                                                <div class="input-group mb-3">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">
                                                            <i class="fa fa-lock"></i>
                                                        </span>
                                                    </div>                                                         
                                                    <input type="password" name="senhaUsuario" value="${senhaUsuario}" required class="form-control">
                                                </div>    
                                            </div>
                                        </div>

                                        <div class="card-footer text-center">
                                            <div class="row">
                                                <div class="col-12">
                                                    <button type="submit" class="btn btn-primary btn-block px-5">Login</button>
                                                </div>
                                            </div>
                                            </br>                                    
                                            <div class="row">
                                                <div class="col-12">
                                                    <a href="UsuarioCtr?metodo=cadastrar" class="btn btn-link">Registrar-se</a>
                                                </div>  
                                            </div>    
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>                                        
                    </div>    
                </form>    
            </div>
        </div>


        <script type="text/javascript">
            if (<%=request.getAttribute("mensagem") != null%>)
                $('#mensagemAtencao').modal('show');
            }
        </script>            
    </body>
</html>
