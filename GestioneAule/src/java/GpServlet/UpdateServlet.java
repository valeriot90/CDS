/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GpServlet;

import DBConnect.DBConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.websocket.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author valtan
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
 
    @EJB
    DBConnector connessione = new DBConnector();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PrintWriter writer = response.getWriter();
        //Call your Cluster class and methods...
        //wait for it...

        //return something to your AJAX call.
        //writer.print("refresh page");
        System.out.println("Chiamata doGet UpdateServlet");
        response.getWriter().println("refresh");
    }
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

            try {
                String coordinate = request.getParameter("prenota");
                //System.out.println("QUALCOSA DI DIVERSO DA PRIMA_updateServlet  "+coordinate);

                String [] splits = coordinate.split("\\ ");
                String ora = splits[0];
                String giorno = splits[1];
                //System.out.println("Giorno: "+ giorno);
                String room = splits[2];
                String mail = splits[3]; // mail dell'utente loggato


                //System.out.println("chiamo la CONTROLLA PRENOTAZIONE");
                connessione.connect();
                connessione.update(ora, giorno, room, mail);
                request.getSession().setAttribute("room", room);
                response.sendRedirect("vistaaula.jsp");
                
                System.out.println("Chiamata doPost UpdateServlet");
                doGet(request, response); ////////////////
            }
             catch(SQLException ex){
                    System.out.println("UpdateServlet: ECCEZIONALE! CONTROLLA PRENOTAZIONE");
                    System.out.println(ex);
            } catch (ClassNotFoundException ex) {
                 Logger.getLogger(UpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

}
