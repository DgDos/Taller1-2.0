<%-- 
    Document   : esquemaEliminar
    Created on : Aug 21, 2017, 3:20:17 PM
    Author     : FiJus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Esquema</title>
    </head>
    <h1> Eliminar Esquemas </h1>
    <%
        String respuesta = (String) request.getAttribute("palabra");
        if (respuesta != null && respuesta.length() > 0) {
    %>
    <H6><%=respuesta%> </h6>
        <%}%>
    
    <form action="EliminarEsquemaSer" method="POST">
        Nombre de esquema a eliminar: <input type="text" name="nombreEsquema">
        <input type="submit" name="Eliminar"/>

    </form>

    <a href="index.jsp"><button>Volver</button></a>
</html>
