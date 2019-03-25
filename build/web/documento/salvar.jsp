<%-- 
    Document   : salvar
    Created on : Oct 22, 2018, 1:57:27 PM
    Author     : mateus
--%>

<%@page import="br.com.timestorage.model.Imagem"%>
<%@page import="br.com.timestorage.model.DocumentoAutor"%>
<%@page import="br.com.timestorage.model.Documento"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>          
    </head>
    <body>
        <%@include file="../administrador/menu.jsp" %>
        <%@include file="../autor/pesquisar.jsp" %>

        <%
            Documento oDocumento = null;
            List<DocumentoAutor> oDocumentoAutor = null;
            List<Imagem> oListaImagem = null;
            String inativo = "";
            if (request.getAttribute("documento") != null) {
                oDocumento = (Documento) request.getAttribute("documento");
                if (oDocumento.getStatusDocumento() == 1) {
                    inativo = "selected";
                }
            }

            if (request.getAttribute("DocumentoAutor") != null) {
                oDocumentoAutor = (List<DocumentoAutor>) request.getAttribute("DocumentoAutor");
            }

            if (request.getAttribute("ImagensDocumento") != null) {
                oListaImagem = (List<Imagem>) request.getAttribute("ImagensDocumento");
            }
        %>        

        <div class="content">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body p-0">  
                                <div class="card-header bg-light">
                                    Manutenção de Documentos
                                </div>  
                                <form name="cadastrarDocumento" enctype="multipart/form-data" action="${pageContext.request.contextPath}/DocumentoCtr?metodo=salvar" method="POST">
                                    <input type="hidden" name="idDocumento" value="${documento.idDocumento}"/>
                                    <div class="row pb-5 p-5">                                        
                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label class="form-control-label">Título</label>
                                                        <input class="form-control" type="text" name="tituloDocumento" value="${documento.tituloDocumento}" maxlength="200" autofocus/>                                                        
                                                    </div>                                            
                                                </div>            

                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label class="form-control-label">Sínopse</label>
                                                        <textarea id="textarea" name="sinopseDocumento" class="form-control" rows="6" maxlength="4000">${documento.sinopseDocumento}</textarea>
                                                    </div>                                            
                                                </div>                                                                                                            
                                            </div>                                                                  
                                        </div>

                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="form-control-label">Data Publicação</label>
                                                        <input class="form-control" type="date" name="dataPublicacaoDocumento" value="${documento.dataPublicacaoDocumento}"/>                                                        
                                                    </div>                                            
                                                </div>            

                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="form-control-label">Status</label>
                                                        <select id="single-select" class="form-control" name="statusDocumento">
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
                                                        <label class="form-control-label">Categoria</label>
                                                        <select id="single-select" class="form-control" name="categoriaDocumento">
                                                            <c:forEach var="categoria" items="${categorias}"> 
                                                                <option value="${categoria.idCategoria}" ${categoria.idCategoria == documento.categoria.idCategoria ? "selected" : ""}>${categoria.descricaoCategoria}</option>
                                                            </c:forEach>
                                                        </select>                                                         
                                                    </div>                                            
                                                </div>               

                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="form-control-label">Editora</label>
                                                        <select id="single-select" class="form-control" name="editoraDocumento">
                                                            <c:forEach var="editora" items="${editoras}"> 
                                                                <option value="${editora.idEditora}" ${editora.idEditora == documento.editora.idEditora ? "selected" : ""}>${editora.descricaoEditora}</option>
                                                            </c:forEach>
                                                        </select>                                                         
                                                    </div>                                            
                                                </div>                                                                                                            
                                            </div>                                                                  
                                        </div>  

                                        <div class="col-md-6">
                                            <div class="row">                                                    
                                                <div class="col-md-12">
                                                    <label class="form-control-label">Autores</label>
                                                    <div class="form-group">                                                        
                                                        <table class="table" id="tabelaAutores">
                                                            <thead>
                                                                <tr>
                                                                    <th class="border-0 text-uppercase small font-weight-bold" hidden></th>   
                                                                    <th class="border-0 text-uppercase small font-weight-bold">ID</th>
                                                                    <th class="border-0 text-uppercase small font-weight-bold">Nome</th>                                                                    
                                                                    <th class="border-0 text-uppercase small font-weight-bold">#</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody id="itensAutores">
                                                                <% if ((oDocumentoAutor != null) && (oDocumentoAutor.size() > 0)) {%>    
                                                                <c:forEach var="documentoAutor" items="${DocumentoAutor}">
                                                                    <tr>
                                                                        <td hidden><input type="hidden" name="autores" value="${documentoAutor.autor.idAutor}" /></td>
                                                                        <td>${documentoAutor.autor.idAutor}</td>
                                                                        <td>${documentoAutor.autor.nomeAutor}</td>                                                                         
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
                                                        <button type="button" data-toggle="modal" data-target="#pesquisarAutor" class="btn btn-secondary btn-block">
                                                            Adicionar
                                                        </button>
                                                    </span>                                            
                                                </div>                                                            
                                            </div>
                                        </div>   

                                        <div class="col-md-12">                                            
                                            <div class="row">
                                                <div class="col-md-12"> 
                                                    <br></br>
                                                    <label class="form-control-label">Fotos</label>
                                                    <div class="row" id="divFotos">
                                                        <% if ((oListaImagem != null) && (oListaImagem.size() > 0)) {%>
                                                        <c:forEach var="imagem" items="${ImagensDocumento}">
                                                            <div class="col-md-2" id="doc-1">
                                                                <div class="form-group text-center">                                                        
                                                                    <div id="imagem-1" class="perfil">
                                                                        <img src="${pageContext.request.contextPath}/SerializarImagem?metodo=imagemDocumento&caminho=${imagem.caminhoImagem}" class="img-fluid" width="150" height="150"/> 
                                                                    </div>
                                                                    <input id="selecionar-imagem-1" type="file" name="foto-doc-1" style="display: none" onchange="alterarImagem(this, '#imagem-1')"/>
                                                                    <label for="selecionar-imagem-1" class="btn btn-sm"><i class="fa fa-camera"></i>  Alterar Imagem</label>
                                                                    <label class="btn btn-sm" onclick="deleteDiv('#doc-1');"><i class="fa fa-trash"></i> Remover Imagem</label>
                                                                </div>
                                                            </div>                                                        
                                                        </c:forEach>    
                                                        <%} else {%>
                                                        <div class="col-md-2" id="doc-1">
                                                            <div class="form-group text-center">                                                        
                                                                <div id="imagem-1" class="perfil">
                                                                    <img src="./utils/imagens/geral/no-image.jpg" class="img-fluid" width="150" height="150"/> 
                                                                </div>
                                                                <input id="selecionar-imagem-1" type="file" name="foto-doc-1" style="display: none" onchange="alterarImagem(this, '#imagem-1')"/>
                                                                <label for="selecionar-imagem-1" class="btn btn-sm"><i class="fa fa-camera"></i>  Alterar Imagem</label>
                                                                <label class="btn btn-sm" onclick="deleteDiv('#doc-1');"><i class="fa fa-trash"></i> Remover Imagem</label>
                                                            </div>
                                                        </div>
                                                        <%}%>
                                                    </div>
                                                </div>

                                                <div class="col-md-2">                                                    
                                                    <span class="input-group-btn">                                                          
                                                        <button type="button" class="btn btn-secondary btn-block" id="btnAdicionarFotos">
                                                            Adicionar
                                                        </button>
                                                    </span>                                            
                                                </div>                                                 
                                            </div>                                              
                                        </div>                                                              
                                    </div>

                                    <div class="card-footer bg-light text-center">                                    
                                        <button type="submit" class="btn btn-primary" style="width: 120px">Salvar</button>
                                        <a href="DocumentoCtr?metodo=listar&consultar=false">
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
                document.getElementById("tabelaAutores").deleteRow(i);
            }

            function deleteDiv(selectedDiv) {
                $(selectedDiv).remove();
            }

            $(document).ready(function () {
                $('#btnAdicionarFotos').click(function () {
                    var qtdeFotos = $('input[type="file"]').length + 1;
                    var foto = '#imagem-' + qtdeFotos;
                    $('#divFotos').append('<div class="col-md-2" id="doc-' + qtdeFotos + '"> ' +
                            '  <div class="form-group text-center"> ' +
                            '    <div id="imagem-' + qtdeFotos + '" class="perfil"> ' +
                            '      <img src="./utils/imagens/geral/no-image.jpg" class="img-fluid" width="150" height="150"/> ' +
                            '    </div> ' +
                            '    <input id="selecionar-imagem-' + qtdeFotos + '" type="file" name="foto-doc-' + qtdeFotos + '" style="display: none" onchange="alterarImagem(this, \'' + foto + '\')"/> ' +
                            '    <label for="selecionar-imagem-' + qtdeFotos + '" class="btn btn-sm"><i class="fa fa-camera"></i>  Alterar Imagem</label> ' +
                            '    <label class="btn btn-sm" onclick="deleteDiv(\'#doc-' + qtdeFotos + '\');"><i class="fa fa-trash"></i> Remover Imagem</label> ' +
                            '  </div> ' +
                            '</div>');
                });
            });

            function alterarImagem(pObjeto, pImagem) {
                if (typeof (FileReader) != "undefined") {
                    var image_holder = $(pImagem);
                    image_holder.empty();

                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $("<img />", {
                            "src": e.target.result,
                            "class": "img-fluid"
                        }).appendTo(image_holder);
                    }
                    image_holder.show();
                    reader.readAsDataURL($(pObjeto)[0].files[0]);
                } else {
                    alert("Este navegador nao suporta FileReader.");
                }
            }
            ;
        </script>        
    </body>
</html>