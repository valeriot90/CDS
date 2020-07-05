/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import GpUser.User;
import GpService.RegisterService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author valtan
 */

/*
0 = utente registrato
1 = utente già presente
2 = mail in formato non corretto
3 = controlla se unipi.it e' professore
4 = errore immissione utente
*/

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	 response.setContentType("text/html;charset=UTF-8");
	 PrintWriter out = response.getWriter();
	 String firstName = request.getParameter("firstName");
	 String lastName = request.getParameter("lastName");
         String status = request.getParameter("status");
	 String email = request.getParameter("email");
	 String password = request.getParameter("password");
	 User user = new User(firstName, lastName, status, email, password);
			
	 try {	
		 RegisterService registerService = new RegisterService();
		 int result = registerService.register(user);	
                 System.out.println("result e' " + result);
		 out.println("<html>");
		 out.println("<head>");		
		 out.println("<title>Registration Successful</title>");		
		 out.println("</head>");
                 out.println("<style>\n" +
                        "        body {\n" +
                        "          background-color: powderblue;\n" +
                        "        }\n" +
                        "        h1  {\n" +
                        "          color: blue;\n" +
                        "        }\n" +
                        "        p  {\n" +
                        "          color: red;\n" +
                        "        }\n" +
                        "       </style>");
		 out.println("<body>");
		 out.println("<center>");
		 switch(result){
                    
                    case 0:
			 out.println("<h1>Thanks for Registering with us :</h1>");
			 out.println("To login with new UserId and Password<a href=login.jsp>Click here</a>");
                         break;
                    case 1:
			 out.println("<h1>Registration Failed: user already present</h1>");
                         out.println("To try again<a href=signup.jsp>Click here</a>");
                         break;
                    case 2:
			 out.println("<h1>Registration Failed: The specified mail is not valid, please use a valid domain</h1>");
                         out.println("To try again<a href=signup.jsp>Click here</a>");
                         break;
                    case 3:
			 out.println("<h1>Registration Failed: if you are prof, use @unipi.it domain</h1>");
                         out.println("To try again<a href=signup.jsp>Click here</a>");
                         break;
                    case 4:
			 out.println("<h1>Registration Failed: mail already used</h1>");
                         out.println("To try again<a href=signup.jsp>Click here</a>");
                         break;
                    case 5:
                         response.sendRedirect("sqlerror.jsp");
                         break;
                    
		 }
                 
                 out.println("<br/>");
                 out.println(" <img  align=\"center\" src=\"img/error.png\" alt=\"dino\"> ");
		 out.println("</center>");
		 out.println("</body>");
		 out.println("</html>");
	 }  catch (SQLException ex) {
                System.out.println("ECCEZIONE CONNESSIONE DB "+ex);
                response.sendRedirect("sqlerror.jsp");
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
         }  catch (ClassNotFoundException ex) {		
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {		
		 out.close();
	 }
        }

}
