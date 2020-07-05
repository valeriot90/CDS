<%-- 
    Document   : home
    Created on : Nov 21, 2017, 3:45:30 PM
    Author     : valtan
--%>

<%@page import="java.util.logging.Logger"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="GpService.RoomService"%>
<%@page import="java.util.Date"%>
<%@page import="GpUser.User"%>
<%@page import="GpRoom.Room"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <!--<META HTTP-EQUIV="refresh" CONTENT="10;vistaaula.jsp">-->
	 <link rel="stylesheet" type="text/css" href="css/style.css"/>
	 <title>Result Page</title>	
       
</head>
<body>
    <header>
           <p>Gestione Aule Poli</p>
       </header>
<center>
	 <div id="container">
		 <h1>Vista aula</h1>
                 <br/>
                <a href="./easteregg.html">*</a>
                <br/>
			 <%=new Date()%></br>
			 <% 
				 User user = (User) session.getAttribute("user");
                                 String mail = user.getMail();
			 %>		
			 <b>Welcome <%= user.getFirstname() + " " + user.getLastname()%></b>
                         <b> <%= mail + " " + user.getStatus()%></b>
			 <br/>
		 

		 <table>
			 <thead>
				 <tr>   
                                        <%String room = (String) session.getAttribute("room");
                                            if(room == null) System.out.println("La session di room e' null");
                                        %>
                                        
                                        <th align="center" width="10%"><%="Aula " + room%></th>  
                                     
                                <%--     <%= System.out.println("NOME AULA VISTA AULA" + room.getNome())%>  --%>
                                <%
                                Calendar c = Calendar.getInstance();
                                Calendar cal = Calendar.getInstance();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                String datainizio = (String) session.getAttribute("giornoinizio");
                                try {
                                    cal.setTime(sdf.parse(datainizio));// all done
                                } catch (ParseException ex) {
                                    System.out.println("Eccezione nel parsing in vistaaula.jsp");
                                }
                                int weekindex = Integer.valueOf((String) session.getAttribute("weekindex")); 
                                int dayOfMonth = c.getActualMaximum(c.DAY_OF_MONTH);
                                
                                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                                //System.out.println("Giorno corrente della settimana (sistema americano-vistaaula.jsp)" + dayOfWeek);
                                String [] datasplit = datainizio.split("-");
                                int giornoinizio = Integer.valueOf(datasplit[2]); //today
                                
                                Calendar calendar = Calendar.getInstance();
                                int hours = calendar.get(Calendar.HOUR_OF_DAY);//24h format Calenda.HOUR is 12h format
                                //DEPRECATED
                                //Date dateInstance = new Date();
                                //int hours = dateInstance.getHours(); // current hour
                                int orainizio = Integer.valueOf((String) session.getAttribute("orainizio"));
                                
                                /*int [] giornisettimana = new int[6];
                                int tmp = giornoinizio;
                                for(int i= 1; i< 6; i++){
                                    System.out.println("GIORNO " + tmp);
                                    Calendar.getInstance().add(tmp, i);
                                    giornisettimana[i] = tmp;
                                }*/
                                
                                
                                int orafine = Integer.valueOf((String) session.getAttribute("orafine")); 
                                
                                   //indice della settimana
                                RoomService roomService = new RoomService();
                                Room aula = roomService.getViewOfRoom(room, orainizio, orafine, cal, weekindex);
                                
                                // I VALORI -2 SONO LA CORREZIONE DA SISTEMA JAVA AMERICANO AD ITALIANO (lunedi=0 invece che 2)
                                //(giornoinizio+1)%(dayOfMonth+1) + " " + aula.getDayofWeek((dayOfWeek+1-2)%7)
                                
                                
                                for (int k=0;k<6;k++){
                                
                                 Calendar pupu = (Calendar)cal.clone();  
                                pupu.add(Calendar.DATE, k); %>
                                <th width="15%"><%= Integer.toString(pupu.get(Calendar.DAY_OF_MONTH)) + " " + aula.getDayofWeek(pupu.get(Calendar.DAY_OF_WEEK)-2) %></th>
                                <%
                                    //System.out.println("incremento di k " +k +" " + pupu.get(Calendar.DAY_OF_MONTH) + " " + pupu.get(Calendar.DAY_OF_WEEK));
                                }

                                   %>        
                                 </tr> 
			 </thead>
			 <tbody>
                 
				 <%                                      
                                        //for (int i = 0; i<11 ; i++) { 
                                        for (int i = orainizio; i<orafine ; i++) { 
                                            int ora=i+8; 
                                            String dayquery = null;
                                            if(((giornoinizio)%(dayOfMonth) == 0))
                                                dayquery = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH)+ "-" + dayOfMonth;
                                            else
                                                dayquery= c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH)+ "-" + (giornoinizio)%(dayOfMonth);
                                           %>
                                            <tr>
                                                
                                                <td align="center" width="10%"><%="Ora " + ora%></td>
                                                <%for (int j=0;j<6;j++){ // da 0 a 6 perche' deve contare anche la colonna delle ore%> 
                                                <form id= "prenota" width="15%" action="UpdateServlet" method="post">
                                                    <%
                                                        Calendar tomorrow = (Calendar)cal.clone();
                                                        tomorrow.add(Calendar.DATE, (j));
                                                        //System.out.println(tomorrow.get(Calendar.DAY_OF_WEEK) == 1 || (cal.equals(c) && (ora <= hours && j == 0)) );
                                                        //System.out.println("prima cond "+ (tomorrow.get( Calendar.DAY_OF_WEEK) == 1) );
                                                        Date d1 = tomorrow.getTime();
                                                        Date d2 = cal.getTime();
                                                        Date d3 = c.getTime();
                                                        
                                                        //System.out.println("CONDIZIONE (IF OGGI) "+ (sdf.format(d1).equals(sdf.format(d3)) && (ora <= hours && j == 0)));
                                                        
                                                        //System.out.println("oggi" + sdf.format(d3));
                                                       
                                                        //System.out.println("cal" + sdf.format(d2));
                                                        //System.out.println("tomorrow" + sdf.format(d1));
                                                        
                                                        
                                                        if ( tomorrow.get(Calendar.DAY_OF_WEEK) == 1 || (sdf.format(d1).equals(sdf.format(d3)) && (ora <= hours && j == 0)) ) { 
                                                    %>
                                                        <td ><input class="inputdisable" style="width: 150px;" disabled = "disabled" type="submit" name="Prenota1"  value="<%=aula.getDay(ora,j)%>" /></td> 
                                                    <% } else {
                                                        
                                                        //Calendar tomorrow = Calendar.getInstance();
                                                        
                                                        /*if (weekindex!=1){
                                                            tomorrow.add(Calendar.DATE,1);
                                                            cal.add(Calendar.DATE, 1);
                                                        }*/
                                                        Date dateOfTomorrow = tomorrow.getTime();
                                                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                                        String FormattedDate = df.format(dateOfTomorrow);
                                                        //System.out.println("VISTAAULA.JSP Data nella query " + FormattedDate);
                                                    %>
                                                        <td ><input class="inputenable" onCLick="sendMessage()" id = "prenota" style="width: 150px;" type="submit" name="Prenota1"  value="<%=aula.getDay(ora,j)%>" />                                                 
                                                        <input type="hidden" name="prenota"  value="<%=i+8 + " "+ FormattedDate + " " + room + " " + mail%>"></td>
                                                        
                                                    <% }%>
                                                 </form>
                                               <% } %>
                                            </tr><%
                                        }
				 %>
                                 <%-- ERRORE ATTUALE GENERATO DA roomService.. e' null e non va bene! -> RISOLTO--%>
                </table>
                                    <form action="RoomServlet" method="post">
                                        <!--weekindex--;-->
                                            
                                                    <% if (weekindex <= 1) { %>
                                                        <td><input  class="inputdisable" type="submit" disabled = "disabled" name="Previous1" value="Previous" /></td>
                                                     <% } else { %>
                                                        <td><input  class="inputenable" type="submit" name="Previous1" value="Previous" /></td>
                                                    <td><input type="hidden" name="parameters" value="<%=room + " " + orainizio + " " + orafine + " "+ datainizio + " " + weekindex%>"/></td>
                                                     <% } %>

                                      </form>
                                                     
                                    <form action="RoomServlet" method="post">
                                        <!--weekindex++;-->
                                            
                                                    <% if (weekindex >= 4) { %>
                                                        <td><input  class="inputdisable" type="submit" disabled = "disabled" name="Next1" value="Next" /></td>
                                                     <% } else { %>
                                                        <td><input  class="inputenable" type="submit" name="Next1" value="Next" /></td>
                                                    <td><input type="hidden" name="parameters" value="<%=room + " " + orainizio + " " + orafine + " "+ datainizio + " " + weekindex%>"/></td>
                                                     <% } %>

                                      </form>
                                   
				
			 <tbody>
		 		
		 <br/>
	 </div>
                                 
                                 <br/>
                                 <a href="home.jsp">Torna alla home</a>
         </br>  
        <a href="logout.jsp">Logout</a>
        </center>
        <script type="text/javascript" src="websocket.js"></script>
</body>
</html>
