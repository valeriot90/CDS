<%-- 
    Document   : home
    Created on : Nov 21, 2017, 3:45:30 PM
    Author     : valtan
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="GpService.LoginService"%>
<%@page import="java.util.Date"%>
<%@page import="GpUser.User"%>
<%@page import="GpRoom.Room"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <link rel="stylesheet" type="text/css" href="css/style.css"/>
	 <title>Result Page</title>
         
</head>
<body>
    <header>
           <p>Gestione Aule Poli</p>
       </header>
<center>
	 <div id="container">
		 <h1>Lista aule</h1>
                 <br/>
                 <a href="./easteregg.html">*</a>
                 <br/>
			 <%=new Date()%></br>
			 <%
				 User user = (User) session.getAttribute("user");
			 %>		
			 <b>Welcome <%= user.getFirstname() + " " + user.getLastname()%></b>		
			 <br/>
                         
                       
                         
                   <table class="hometable">
			 <thead align="center">
                            <tr>
                                <th>Aula</th>
                                <th>Premi il pulsante per visualizzare la vista</th>
                            </tr>
			 </thead>
			 <tbody>
                               
				 <%
                                        int orainizio = 0;
                                        int orafine = 11;
                                        //int giornoinizio = GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH);
                                        Calendar c = Calendar.getInstance();
                                       /* Calendar tomorrow = Calendar.getInstance();
                                        //Calendar.getInstance().add(tomorrow.DATE, 5);
                                        tomorrow.add(Calendar.DATE, 5);*/
                                        Date data = c.getTime();
                                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                        String giornoinizio = df.format(data);
                                        
                                        System.out.println("Giornoinizio da home.jsp " +  giornoinizio);
                                        
                                        int weekindex=1;
                                        LoginService loginService = new LoginService();
                                        List<Room> list = loginService.getListOfRoom();
                                        for (Room r : list) {
                                            String aula = r.getNome();
				 %>
				 <tr>
                                    <%--    <form action="${pageContext.request.contextPath}/myservlet" method="post"> --%>
                                    <form action="RoomServlet" method="POST">
                                        <tr><input type="hidden" name="parameters" value="<%=aula + " " + orainizio + " " + orafine + " "+ giornoinizio + " " + weekindex%>"/></tr>
                                        <td><div name="nomeaula"><%=aula%></div></td>
                                        <%--out.println("GET NOME E' " + aula);--%>
                                        <td><input class="visualizza" type="submit" name="<%=aula%>" value="Visualizza"/></td>
                                    </form>
				 </tr>
				 <%}%>
			 <tbody>
		 </table>		
		 <br/>
	 </div>     
        </br>  
        <a href="logout.jsp">Logout</a>                 
	</center>	
                         
        
</body>
</html>

