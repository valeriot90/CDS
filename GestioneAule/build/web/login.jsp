<%-- 
    Document   : login
    Created on : Nov 17, 2017, 4:45:53 PM
    Author     : valtan
--%>

<%@page import="DBConnect.DBConnector"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head id="rcorners">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css" media = "screen" >	
        <title>Login</title>
    </head>
    
   <body>
       <header>
           <p>Gestione Aule Poli</p>
       </header>
       <%  
            
           //Ogni volta che si accede al server parte una query per ripulire le entry vecchi
           //ovvero tutte quelle piu' vecchie del giorno odierno
           //powderblue
            try{
                DBConnector connessione = new DBConnector();
                Calendar c = Calendar.getInstance();
                
                Date data = c.getTime();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String FormattedDate = df.format(data);
                connessione.connect();
                connessione.delete(FormattedDate);
                /*Connection con = null;
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gap","root","root");
                */
                
                /*PreparedStatement psta= con.prepareStatement("SET SQL_SAFE_UPDATES=0");
                psta.executeUpdate();
                psta= con.prepareStatement("DELETE FROM gap.prenotazioni WHERE giorno < DATE('"+ FormattedDate +"')"); 
                psta.executeUpdate();*/
                
            }catch(Exception e){
                System.out.println("Eccezione login.jsp "+ e);
            }
       %>
        <h3>Login Page</h3>
        <center>
        <br/>
        <a href="./easteregg.html">*</a>
        <br/>
        <th >Please provide your credential to use this website</th>  
        <center>
        
        <form method="post" action="LoginServlet">
            
        <table class="logintable" align="center" cellpadding = "10">
            
                <tr>
                    <td><div id="login-box-name" >User Mail:</div></td>
                    <td><div id="login-box-field"></td>
                    <td>
                        <input name="mail" type="text" placeholder="Username" title="Username" value="" size="30" maxlength="50" />
                    </td>
                </tr>

                <tr>
                    <td><div id="login-box-name">Password:</div></td>
                    <td>
                        <div id="login-box-field">
                    </td>        
                    <td>
                        <input name="password" type="password" placeholder="Password" title="Password" value="" size="30" maxlength="48"/>
                        </div>
                    </td>
                </tr>


                <tr>
                        <td>New User? </td>
                        <td>
                            <a class="register" href="signup.jsp" >Register Here</a>
                        </td>
                        <td>
                            <input  type="submit" value="Login" />
                        </td>    
                </tr>
        </table>
        </form>

    </body>
</html>


</html>
