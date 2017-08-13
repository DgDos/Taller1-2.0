<%-- 
    Document   : tablas
    Created on : Aug 10, 2017, 10:40:31 AM
    Author     : Sary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tablas</title>
    </head>
        <h1> Creador de Tablas </h1>
        
        <form action="TablesSer" method="POST">
            Nombre del esquema: <input type="text" name="nombreEsquemaFK">
            <br> <br>
            Nombre de tabla: <input type="text" name="nombreTabla">
            <br><br>
            <input type="submit" name="Enviar"/>
           
        </form>
        
         <a href="index.jsp"><button>Volver</button></a>
</html>
