<%-- 
    Document   : pesquisar
    Created on : Sep 19, 2018, 1:06:40 PM
    Author     : mateus
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>   
        <div class="modal fade" id="pesquisarAutor" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Pesquisar Autores</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body"> 
                        <div class="col-md-12">
                            <div class="row">
                                <div class="col-md-9">
                                    <div class="form-group">                                                        
                                        <input id="filtroDescricaoAutore" placeholder="Descrição" class="form-control" type="text" value="${filtroDescricaoAutor}" maxlength="200" autofocus/>
                                    </div>                                            
                                </div>    

                                <div class="col-md-3">                                                    
                                    <span class="input-group-btn">                                                          
                                        <button type="button" class="btn btn-primary btn-block" id="btnPesquisar"><i class="fa fa-search"></i> Pesquisar</button>
                                    </span>                                            
                                </div>                                                                                                              
                            </div>                                                                  
                        </div> 


                        <div class="col-md-12">
                            <table class="table" >
                                <thead>
                                    <tr>
                                        <th class="border-0 text-uppercase small font-weight-bold">#</th>
                                        <th class="border-0 text-uppercase small font-weight-bold">ID</th>
                                        <th class="border-0 text-uppercase small font-weight-bold">Nome</th>
                                        <th class="border-0 text-uppercase small font-weight-bold">Status</th>
                                    </tr>
                                </thead>
                                <tbody id="consultaAutor">
                                </tbody>    
                            </table>
                        </div>                                                                        
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Fechar</button>                        
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript">
            $(document).ready(function () {
                $("#btnPesquisar").click(function () {
                    $.getJSON({
                        type: 'POST',
                        data: {
                            metodo: "listar",
                            filtroDescricaoAutor: $("#filtroDescricaoAutor").val(),
                            filtroStatusAutor: "0",
                            modal: "true"
                        },
                        url: "AutorCtr",
                        success: function (result) {
                            if (result) {
                                $("#consultaAutor").html("");
                                $(result).each(function () {
                                    $("#consultaAutor").append(
                                            "<tr>" +                                            
                                            "<td> <button class=\"btn btn-light\" onclick='adicionaAutor(" + this.idAutor + ");'> <i class=\"fa fa-plus\"></i></button></td>" +
                                            "<td>" + this.idAutor + "</td>" +
                                            "<td>" + this.nomeAutor + "</td>" +
                                            "<td>" + this.descricaoStatus + "</td>" +
                                            "</tr>"
                                            );
                                });
                            }
                        }
                    });
                });
            });


            function adicionaAutor(id) {
                $.getJSON({
                    type: 'POST',
                    data: {
                        metodo: "carregar",
                        idAutor: id,
                        modal: "true"
                    },
                    url: "AutorCtr",
                    success: function (result) {
                        if (result) {
                            $(result).each(function () {
                                var adiciona = true;
                                var oTable = document.getElementById('itensAutores');                                 
                                for (i = 0; i < oTable.rows.length; i++) {                                    
                                    var oCells = oTable.rows.item(i).cells;                                    
                                    if (oCells.item(1).innerHTML === this.idAutor.toString()){
                                        adiciona = false;
                                    }
                                }

                                if (adiciona) {
                                    $("#itensAutores").append(
                                            "<tr>" +
                                            "<td hidden><input type=\"hidden\" name=\"autores\" value='" + this.idAutor + "'/></td>"+
                                            "<td>" + this.idAutor + "</td>" +
                                            "<td>" + this.nomeAutor + "</td>" +
                                            "<td> <button class=\"btn btn-light\" onclick=\"deleteRow(this);\"> <i class=\"fa fa-trash\"></i> </button> </td>" +
                                            "</tr>"
                                            );
                                }
                            });
                            $('#pesquisarAutor').modal('hide');
                        }
                    }
                });
            };
        </script>    
    </body>
</html>
