<%-- 
    Document   : esquemas
    Created on : Aug 10, 2017, 10:36:24 AM
    Author     : Sary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Esquemas</title>
    </head>
        <h1> Creador de Esquemas </h1>
        <%
        String respuesta = (String) request.getAttribute("palabra");
        if (respuesta != null && respuesta.length() > 0) {
        %>
        <H6><%=respuesta%> </h6>
        <%}%>
    
        <form action="SchemaSer" method="POST">
            Nombre del esquema: <input type="text" name="nombreEsquema">
            <br> <br>
            
        <input type="submit" name="Enviar"/>
           
        </form>
        
         <a href="index.jsp"><button>Volver</button></a>
</html>
