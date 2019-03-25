<%-- 
    Document   : menuPrincipal
    Created on : Aug 23, 2018, 9:35:01 PM
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

    </body>
</html>
