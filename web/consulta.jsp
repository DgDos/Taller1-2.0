<%-- 
    Document   : consulta
    Created on : Aug 13, 2017, 10:21:25 PM
    Author     : FiJus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta</title>
    </head>
    <h1> Consulta </h1>
    <%
        String respuesta = (String) request.getAttribute("palabra");
        if (respuesta != null && respuesta.length() > 0) {
    %>
    <H6><%=respuesta%> </h6>
        <%}%>
    
    <form action="ConsultaSer" method="POST">
        Nombre de esquema a consultar: <input type="text" name="nombreEsquema">
        <input type="submit" name="Consulta"/>

    </form>

    <a href="index.jsp"><button>Volver</button></a>

</html>
