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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
   
/**
 *
 * @author valtan
 */
@WebServlet(name = "RoomServlet", urlPatterns = {"/RoomServlet"})
public class RoomServlet extends HttpServlet {

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
        
             //   try {
                    String parameters = request.getParameter("parameters");
                    String[] splits = parameters.split("\\ ");
                    String nomeaula = splits[0];
                    String orainizio = splits[1];
                    String orafine = splits[2];
                    String datainizio = splits[3];
                    String weekindex = splits[4];
                    String giornoinizio ;
                    //split della data per prendere il giorno
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
                    try {
                        cal.setTime(sdf.parse(datainizio));// all done
                    } catch (ParseException ex) {
                        Logger.getLogger(RoomServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int weekindexint =1;
                    
                    //String nome = (String)request.getSession().getAttribute("nomeaula");
                    System.out.println("QUALCOSA DI DIVERSO DA PRIMA_roomservlet  "+nomeaula);
                    //System.out.println(java.util.Arrays.asList(request.getParameterNames()));
                    //RoomService roomService = new RoomService();
                    //Room room = roomService.getRoomByName(nome);
                    if (request.getParameter("Previous1")!= null){
                         weekindexint = Integer.parseInt(weekindex);
                         weekindexint --;
                         cal.add(Calendar.DATE, -1);
                    }
                     if (request.getParameter("Next1")!= null){
                         weekindexint = Integer.parseInt(weekindex);
                         cal.add(Calendar.DATE, 1);
                         weekindexint ++;
                    }
                    
                    weekindex=String.valueOf(weekindexint);
                    Date data = cal.getTime();
                    giornoinizio = sdf.format(data);
                    request.getSession().setAttribute("orainizio", orainizio);
                    request.getSession().setAttribute("orafine", orafine);
                    request.getSession().setAttribute("giornoinizio", giornoinizio);
                    request.getSession().setAttribute("weekindex", weekindex);
                    
                    request.getSession().setAttribute("room", nomeaula);
                    
                    response.sendRedirect("vistaaula.jsp");
               // } catch (SQLException ex) {
           //     System.out.println("Mail vuota (LoginServlet)");
           //     Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
          //  }
        }

}
