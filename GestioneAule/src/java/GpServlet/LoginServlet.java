/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GpServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import GpUser.User;
import GpService.LoginService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author valtan
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            try {
                System.out.println("E' stata chiamata LoginServlet.java");
                String mail = request.getParameter("mail");
                String password = request.getParameter("password");
                LoginService loginService = new LoginService();
                boolean result = loginService.authenticateUser(mail, password);
                User user = loginService.getUserByUserMail(mail);
                //System.out.println("VALORE DI RESULT: " + result);
                if(result == true){
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("home.jsp");
                }
                else{
                    if(result == false)
                        response.sendRedirect("error.jsp");
                    
                }  } catch (SQLException ex) {
                    response.sendRedirect("sqlerror.jsp");
                System.out.println("Mail vuota (LoginServlet)");
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

}
