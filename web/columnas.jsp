<%-- 
    Document   : columnas
    Created on : Aug 10, 2017, 10:40:47 AM
    Author     : Sary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Columnas</title>
    </head>
        <h1> Creador de Columnas </h1>
        
        <form action="ColumnsSer" method="POST">
            Nombre de tabla: <input type="text" name="nombreTablaFK">
            <br> <br>
            Nombre de atributo: <input type="text" name="atributo">
            <br><br>
            Tipo de dato: <input type="text" name="tipoAtributo">
        <input type="submit" name="Enviar"/>
           
        </form>
        
         <a href="index.jsp"><button>Volver</button></a>
        
</html>
