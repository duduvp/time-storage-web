<%-- 
    Document   : alertas
    Created on : Aug 30, 2018, 9:49:11 PM
    Author     : mateus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>       
        <div class="modal fade" id="mensagemAtencao" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header bg-warning border-0">
                        <h5 class="modal-title text-white">Atenção</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>                                    

                    <div class="modal-body p-5">
                        ${mensagem}
                    </div>

                    <div class="modal-footer border-0">
                        <button type="button" class="btn btn-warning text-white" data-dismiss="modal">OK</button>                        
                    </div>
                </div>
            </div>
        </div> 

        <div class="modal fade" id="mensagemSucesso" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header bg-success border-0">
                        <h5 class="modal-title text-white">Sucesso</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body p-5">
                        ${mensagemSucesso}
                    </div>

                    <div class="modal-footer border-0">
                        <button type="button" class="btn btn-success" data-dismiss="modal">OK</button>
                    </div>
                </div>
            </div>
        </div>                    
    </body>
</html>
