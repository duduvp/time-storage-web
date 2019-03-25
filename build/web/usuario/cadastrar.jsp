<%-- 
    Document   : salvar
    Created on : Jul 9, 2018, 7:54:33 PM
    Author     : mateus
--%>

<%@page import="br.com.timestorage.model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../cabecalho.jsp" %>

        <script type="text/javascript">
            function mascaraCampo(pObjeto, pMascara) {
                objeto = pObjeto;
                mascara = pMascara;
                setTimeout("retornaValor()", 1);
            }

            function retornaValor() {
                objeto.value = mascara(objeto.value);
            }

            function mascaraTelefone(tel) {
                tel = tel.replace(/\D/g, "");
                tel = tel.replace(/^(\d)/, "($1");
                tel = tel.replace(/(.{3})(\d)/, "$1)$2");
                if (tel.length === 9) {
                    tel = tel.replace(/(.{1})$/, "-$1");
                } else if (tel.length === 10) {
                    tel = tel.replace(/(.{2})$/, "-$1");
                } else if (tel.length === 11) {
                    tel = tel.replace(/(.{3})$/, "-$1");
                } else if (tel.length === 12) {
                    tel = tel.replace(/(.{4})$/, "-$1");
                } else if (tel.length > 12) {
                    tel = tel.replace(/(.{4})$/, "-$1");
                }
                return tel;
            }
        </script>       
    </head>
    <body>
        <form name="cadastrarUsuario" action="${pageContext.request.contextPath}/UsuarioCtr?metodo=registrar" method="POST"> 
            <div class="page-wrapper flex-row align-items-center">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-md-6">
                            <div class="card p-4">
                                <div class="card-header text-center h4 font-weight-light">
                                    TimeStorage
                                </div>
                                <div class="card-body py-8">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="form-control-label">Nome</label>
                                                <input name="nomePessoa" class="form-control" type="text" value="${usuario.nomePessoa}" maxlength="200" autofocus required/>
                                            </div>                                            
                                        </div>   

                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="form-control-label">Sobrenome</label>
                                                <input class="form-control" type="text" name="sobrenomePessoa" value="${usuario.sobrenomePessoa}" maxlength="200" required/>
                                            </div>                                            
                                        </div>    
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">                
                                            <div class="form-group">
                                                <label class="form-control-label">Telefone</label>
                                                <input class="form-control" type="text" name="telefonePessoa" maxlength="14" onkeydown="javascript: mascaraCampo(this, mascaraTelefone);" value="${usuario.telefonePessoa}" maxlength="20" required/>
                                            </div> 
                                        </div> 

                                        <div class="col-md-6"> 
                                            <div class="form-group">
                                                <label class="form-control-label">Data de Nascimento</label>
                                                <input class="form-control" type="date" name="dataNascimentoPessoa" value="${usuario.dataNascimentoPessoa}" required/>
                                            </div>
                                        </div> 
                                    </div> 

                                    <div class="row">
                                        <div class="col-md-12">                                              
                                            <div class="form-group">
                                                <label class="form-control-label">Email</label>
                                                <input class="form-control" type="email" name="emailPessoa" value="${usuario.emailPessoa}" maxlength="200" required/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">                                     
                                            <div class="form-group">
                                                <label class="form-control-label">Senha</label>
                                                <input class="form-control" type="password" name="senhaUsuario" value="${usuario.senhaUsuario}" required/>
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="form-control-label">Confirmar senha</label>
                                                <input class="form-control" type="password" name="confirmarSenhaUsuario" value="${confirmarSenhaUsuario}" required/>
                                            </div>
                                        </div>
                                    </div>                                
                                </div>
                                <div class="card-footer">                                        
                                    <button type="submit" class="btn btn-success btn-block">Salvar</button>
                                    <br/>                                                                    
                                    <div class="text-center font-weight-light">
                                        <a href="UsuarioCtr?metodo=telaLogin">Voltar</a>
                                    </div>    
                                </div>                                               
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>  

        <script type="text/javascript">
            if (<%=request.getAttribute("mensagem") != null%>)
                $('#mensagemAtencao').modal('show');
        </script>                                               
    </body>         
</html>
