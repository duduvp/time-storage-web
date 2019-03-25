<%-- 
    Document   : index
    Created on : Jun 1, 2018, 10:06:39 PM
    Author     : mateus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
    </head>
    <body> 
        <%
            if (request.getSession().getAttribute("idUsuario") == null) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        %>

        <%@include file="menu.jsp" %>  

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-3">
                        <div class="card p-4">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <span class="h4 d-block font-weight-normal mb-2">${dashboardAdm.qtdDocumentos}</span>
                                    <span class="font-weight-light">Documentos</span>
                                </div>

                                <div class="h2 text-muted">
                                    <i class="fa fa-archive"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                                    
                    <div class="col-md-3">
                        <div class="card p-4">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <span class="h4 d-block font-weight-normal mb-2">${dashboardAdm.qtdAutores}</span>
                                    <span class="font-weight-light">Autores</span>
                                </div>

                                <div class="h2 text-muted">
                                    <i class="fa fa-user"></i>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="card p-4">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <span class="h4 d-block font-weight-normal mb-2">${dashboardAdm.qtdEditoras}</span>
                                    <span class="font-weight-light">Editoras</span>
                                </div>

                                <div class="h2 text-muted">
                                    <i class="fa fa-book"></i>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="card p-4">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <span class="h4 d-block font-weight-normal mb-2">${dashboardAdm.qtdeGeneros}</span>
                                    <span class="font-weight-light">Gêneros</span>
                                </div>

                                <div class="h2 text-muted">
                                    <i class="fa fa-cubes"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> 
                                    
                <div class="row ">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                Dados de uso do sistema
                            </div>

                            <div class="card-body p-0">
                                <div class="p-4">
                                    <canvas id="line-chart" width="100%" height="20"></canvas>
                                </div>

                                <div class="justify-content-around mt-4 p-4 bg-light d-flex border-top d-md-down-none">
                                    <div class="text-center">
                                        <div class="text-muted small">Tráfico nesta semana</div>
                                        <div>800 Usuários (40%)</div>
                                    </div>

                                    <div class="text-center">
                                        <div class="text-muted small">Usuários bloqueados</div>
                                        <div>20 bloqueios (2%)</div>
                                    </div>

                                    <div class="text-center">
                                        <div class="text-muted small">Documentos visualizados</div>
                                        <div>357 documentos (50%)</div>
                                    </div>

                                    <div class="text-center">
                                        <div class="text-muted small">Total de downloads</div>
                                        <div>39 arquivos (650 MB)</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>                                    
            </div>
        </div>        
    </body>
</html>
