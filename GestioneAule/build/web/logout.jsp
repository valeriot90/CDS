<%-- 
    Document   : logout
    Created on : Nov 17, 2017, 4:50:21 PM
    Author     : valtan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
	 <link rel="stylesheet" type="text/css" href="css/style.css">
	 <title>logout Page</title>
        
</head>
<body>
    <header>
           <p>Gestione Aule Poli</p>
       </header>
	 <%		
		 session.removeAttribute("mail");
		 session.removeAttribute("password");
		 session.invalidate();
	 %>
<center>
	 <h1>You have successfully logged out</h1>
         <br/>
                <a href="./easteregg.html">*</a>
                <br/>
                <img  align="center" src="img/dino.gif" alt="dino">
                </br>
	 To login again <a href="login.jsp">click here</a>.
</center>
</body>
</html>