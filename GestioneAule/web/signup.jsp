<%-- 
    Document   : signup
    Created on : Nov 17, 2017, 5:01:32 PM
    Author     : valtan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Registration Form</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media = "screen" >
       
    </head>

    <body>
        <center>
        <header>
           <p>Gestione Aule Poli</p>
       </header>
        <h3>Registration Form</h3>
        <br/>
                <a  href="./easteregg.html">*</a>
                <br/>
        <form action="RegisterServlet" method="POST">

        <table align="center" cellpadding = "10">
            <tr>
            <td>First Name</td>
            <td><input type="text" name="firstName" maxlength="30"/>
            (max 30 characters a-z and A-Z)
            </td>
            </tr>

            <tr>
            <td>Last Name</td>
            <td><input type="text" name="lastName" maxlength="30"/>
            (max 30 characters a-z and A-Z)
            </td>
            </tr>
            
            <tr>
            <td>Stato</td>
            <td><input type="radio" name="status" value="professore">
                   <label for="contactChoice1">Professore</label>

                   <input type="radio" name="status" value="studente" checked="checked">
                   <label for="contactChoice2">Studente</label>
            </td>
            </tr>

            <tr>
            <td>Email</td>
            <td><input type="text" name="email" maxlength="100" /></td>
            </tr>

            <tr>
            <td>Password</td>
            <td><input type="text" name="password" maxlength="100" /></td>
            </tr>

            <tr>
            <td colspan="2" align="center">
            <input type="submit" value="Submit">
            <input type="reset" value="Reset">
            </td>
            </tr>
        </table>

        </form>
        <div align="center">
        <a  href="./login.jsp" >Click here to access Gestione Aule Poli</a>
        </div>
        </center>
    </body>
</html>
