<%-- 
    Document   : error
    Created on : Nov 17, 2017, 5:07:21 PM
    Author     : valtan
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" href="css/style.css" type="text/css" media = "screen" >
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Error Page</title>
    </head>
    <body>
        <header>
           <p>Gestione Aule Poli</p>
       </header>
        <center>
                <br/>
                <a href="./easteregg.html">*</a>
                <br/>
                 <h1>Your Login Was Unsuccessful - Please Try Again</h1>
                 To login again <a href="login.jsp">click here</a>.
                 <%System.out.println("Sono nella pagina di errore error.jsp");%>
                 <br>
                 <img  align="center" src="img/error.png" alt="dino">
        </center>
    </body>
</html>
