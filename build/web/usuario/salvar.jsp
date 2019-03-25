<%-- 
    Document   : cadastro
    Created on : Sep 5, 2018, 9:27:06 PM
    Author     : mateus
--%>

<%@page import="br.com.timestorage.model.Usuario"%>
<%@page import="br.com.timestorage.util.Funcoes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>    
    </head>
    <body>
        <% Usuario oUsuario = (Usuario) request.getAttribute("usuario"); %>

        <% if ((request.getSession().getAttribute("idUsuario") != null) && (request.getSession().getAttribute("tipoUsuario").toString().equals("0"))) { %>
        <%@include file="../usuario/menu.jsp" %>
        <% } else if ((request.getSession().getAttribute("idUsuario") != null) && (request.getSession().getAttribute("tipoUsuario").toString().equals("1"))) { %>
        <%@include file="../administrador/menu.jsp" %>
        <% }%>   

        <form name="salvarUsuario" action="${pageContext.request.contextPath}/UsuarioCtr?metodo=salvar" method="POST" enctype="multipart/form-data" class="form-horizontal form-material">        
            <input type="hidden" name="idUsuario" value="${usuario.idUsuario}"/>
            <input type="hidden" name="idPessoa" value="${usuario.idPessoa}"/>
            <input type="hidden" name="tipoUsuario" value="${usuario.tipoUsuario}"/>                
            <div class="content">            
                <div class="page-wrapper">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-4 col-xlg-3 col-md-5">
                                <div class="card">
                                    <div class="card-body">
                                        <center class="m-t-30">                                        
                                            <div id="imagem-perfil" class="perfil">
                                                <% if ((oUsuario.getFotoPerfilUsuario() != null) && (!oUsuario.getFotoPerfilUsuario().equals(""))) { %>
                                                <img src="${pageContext.request.contextPath}/SerializarImagem?metodo=profilePhoto&fotoPerfil=${usuario.fotoPerfilUsuario}" class="img-fluid rounded-circle" width="150" height="150"/>
                                                <%} else {%>
                                                <img src="./utils/imagens/geral/no-user.png" class="img-fluid rounded-circle" width="150" height="150"/>
                                                <%} %>    
                                            </div>
                                            <input id="selecionar-imagem" type="file" name="fotoUsuario" style="display: none"/>
                                            <label for="selecionar-imagem" class="btn btn-sm"><i class="fa fa-camera"></i>  Alterar Foto</label>
                                            <h4 class="card-title m-t-10">${usuario.nomePessoa} ${usuario.sobrenomePessoa}</h4>
                                            <h6 class="card-subtitle">${usuario.descricaoStatus}</h6>                                        
                                        </center>
                                    </div>
                                    <div>
                                        <hr> 
                                    </div>
                                    <div class="card-body"> 
                                        <small class="text-muted">Tipo de usuário:</small>
                                        <% if (request.getSession().getAttribute("tipoUsuario").toString().equals("0")) { %>
                                        <h6>Comum</h6> 
                                        <% } else if (request.getSession().getAttribute("tipoUsuario").toString().equals("1")) { %>
                                        <h6>Administrador</h6>
                                        <% }%>                                      
                                        <small class="text-muted">Usuário desde:</small>
                                        <h6>${usuario.dataCadastroFormatada}</h6>                                       
                                    </div>
                                </div>
                            </div>                     

                            <div class="col-lg-8 col-xlg-9 col-md-7">
                                <div class="card">
                                    <div class="card-body">                                    
                                        <div class="form-group">
                                            <label class="col-md-12">Nome</label>
                                            <div class="col-md-12">
                                                <input type="text" name="nomePessoa" value="${usuario.nomePessoa}" class="form-control form-control-line"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-12">Sobrenome</label>
                                            <div class="col-md-12">
                                                <input type="text" name="sobrenomePessoa" value="${usuario.sobrenomePessoa}" class="form-control form-control-line"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="example-email" class="col-md-12">Email</label>
                                            <div class="col-md-12">
                                                <input type="email" name="emailPessoa" value="${usuario.emailPessoa}" class="form-control form-control-line"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-12">Telefone</label>
                                            <div class="col-md-12">
                                                <input type="text" name="telefonePessoa" value="${usuario.telefonePessoa}" class="form-control form-control-line"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-12">Data de Nascimento</label>
                                            <div class="col-md-12">
                                                <input class="form-control" type="date" name="dataNascimentoPessoa" value="${usuario.dataNascimentoPessoa}" required/>                                            
                                            </div>
                                        </div>  

                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <button type="submit" class="btn btn-success">Atualizar Perfil</button>
                                            </div>
                                        </div>                                    
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div> 
        </form>                                            

        <script type="text/javascript">
            if (<%=request.getAttribute("mensagemSucesso") != null%>)
                $('#mensagemSucesso').modal('show');

            if (<%=request.getAttribute("mensagem") != null%>)
                $('#mensagemAtencao').modal('show');

            $("#selecionar-imagem").on('change', function () {
                if (typeof (FileReader) != "undefined") {

                    var image_holder = $("#imagem-perfil");
                    image_holder.empty();

                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $("<img />", {
                            "src": e.target.result,
                            "class": "rounded-circle"
                        }).appendTo(image_holder);
                    }
                    image_holder.show();
                    reader.readAsDataURL($(this)[0].files[0]);
                } else {
                    alert("Este navegador nao suporta FileReader.");
                }
            });
        </script>                                               
    </body>
</html>
