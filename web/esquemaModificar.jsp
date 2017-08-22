<%-- 
    Document   : esquemaModificar
    Created on : Aug 21, 2017, 3:16:13 PM
    Author     : FiJus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Esquema</title>
    </head>
    <h1> Modificar Esquemas </h1>
    <%
        String respuesta = (String) request.getAttribute("palabra");
        if (respuesta != null && respuesta.length() > 0) {
    %>
    <H6><%=respuesta%> </h6>
        <%}%>
    
    <form action="ModificarEsquemaSer" method="POST">
        Nombre de esquema a modificar: <input type="text" name="nombreEsquemaAntiguo">
        Nombre nuevo: <input type="text" name="nombreEsquemaNuevo">
        <input type="submit" name="Modificar"/>

    </form>

    <a href="index.jsp"><button>Volver</button></a>
</html>
