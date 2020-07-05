package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.logging.Logger;
import java.text.ParseException;
import java.util.logging.Level;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.List;
import GpService.RoomService;
import java.util.Date;
import GpUser.User;
import GpRoom.Room;

public final class vistaaula_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\t <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("         <!--<META HTTP-EQUIV=\"refresh\" CONTENT=\"10;vistaaula.jsp\">-->\n");
      out.write("\t <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\"/>\n");
      out.write("\t <title>Result Page</title>\t\n");
      out.write("       \n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <header>\n");
      out.write("           <p>Gestione Aule Poli</p>\n");
      out.write("       </header>\n");
      out.write("<center>\n");
      out.write("\t <div id=\"container\">\n");
      out.write("\t\t <h1>Vista aula</h1>\n");
      out.write("                 <br/>\n");
      out.write("                <a href=\"./easteregg.html\">*</a>\n");
      out.write("                <br/>\n");
      out.write("\t\t\t ");
      out.print(new Date());
      out.write("</br>\n");
      out.write("\t\t\t ");
 
				 User user = (User) session.getAttribute("user");
                                 String mail = user.getMail();
			 
      out.write("\t\t\n");
      out.write("\t\t\t <b>Welcome ");
      out.print( user.getFirstname() + " " + user.getLastname());
      out.write("</b>\n");
      out.write("                         <b> ");
      out.print( mail + " " + user.getStatus());
      out.write("</b>\n");
      out.write("\t\t\t <br/>\n");
      out.write("\t\t \n");
      out.write("\n");
      out.write("\t\t <table>\n");
      out.write("\t\t\t <thead>\n");
      out.write("\t\t\t\t <tr>   \n");
      out.write("                                        ");
String room = (String) session.getAttribute("room");
                                            if(room == null) System.out.println("La session di room e' null");
                                        
      out.write("\n");
      out.write("                                        \n");
      out.write("                                        <th align=\"center\" width=\"10%\">");
      out.print("Aula " + room);
      out.write("</th>  \n");
      out.write("                                     \n");
      out.write("                                ");
      out.write("\n");
      out.write("                                ");

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
                                pupu.add(Calendar.DATE, k); 
      out.write("\n");
      out.write("                                <th width=\"15%\">");
      out.print( Integer.toString(pupu.get(Calendar.DAY_OF_MONTH)) + " " + aula.getDayofWeek(pupu.get(Calendar.DAY_OF_WEEK)-2) );
      out.write("</th>\n");
      out.write("                                ");

                                    //System.out.println("incremento di k " +k +" " + pupu.get(Calendar.DAY_OF_MONTH) + " " + pupu.get(Calendar.DAY_OF_WEEK));
                                }

                                   
      out.write("        \n");
      out.write("                                 </tr> \n");
      out.write("\t\t\t </thead>\n");
      out.write("\t\t\t <tbody>\n");
      out.write("                 \n");
      out.write("\t\t\t\t ");
                                      
                                        //for (int i = 0; i<11 ; i++) { 
                                        for (int i = orainizio; i<orafine ; i++) { 
                                            int ora=i+8; 
                                            String dayquery = null;
                                            if(((giornoinizio)%(dayOfMonth) == 0))
                                                dayquery = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH)+ "-" + dayOfMonth;
                                            else
                                                dayquery= c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH)+ "-" + (giornoinizio)%(dayOfMonth);
                                           
      out.write("\n");
      out.write("                                            <tr>\n");
      out.write("                                                \n");
      out.write("                                                <td align=\"center\" width=\"10%\">");
      out.print("Ora " + ora);
      out.write("</td>\n");
      out.write("                                                ");
for (int j=0;j<6;j++){ // da 0 a 6 perche' deve contare anche la colonna delle ore
      out.write(" \n");
      out.write("                                                <form id= \"prenota\" width=\"15%\" action=\"UpdateServlet\" method=\"post\">\n");
      out.write("                                                    ");

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
                                                    
      out.write("\n");
      out.write("                                                        <td ><input class=\"inputdisable\" style=\"width: 150px;\" disabled = \"disabled\" type=\"submit\" name=\"Prenota1\"  value=\"");
      out.print(aula.getDay(ora,j));
      out.write("\" /></td> \n");
      out.write("                                                    ");
 } else {
                                                        
                                                        //Calendar tomorrow = Calendar.getInstance();
                                                        
                                                        /*if (weekindex!=1){
                                                            tomorrow.add(Calendar.DATE,1);
                                                            cal.add(Calendar.DATE, 1);
                                                        }*/
                                                        Date dateOfTomorrow = tomorrow.getTime();
                                                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                                        String FormattedDate = df.format(dateOfTomorrow);
                                                        //System.out.println("VISTAAULA.JSP Data nella query " + FormattedDate);
                                                    
      out.write("\n");
      out.write("                                                        <td ><input class=\"inputenable\" onCLick=\"sendMessage()\" id = \"prenota\" style=\"width: 150px;\" type=\"submit\" name=\"Prenota1\"  value=\"");
      out.print(aula.getDay(ora,j));
      out.write("\" />                                                 \n");
      out.write("                                                        <input type=\"hidden\" name=\"prenota\"  value=\"");
      out.print(i+8 + " "+ FormattedDate + " " + room + " " + mail);
      out.write("\"></td>\n");
      out.write("                                                        \n");
      out.write("                                                    ");
 }
      out.write("\n");
      out.write("                                                 </form>\n");
      out.write("                                               ");
 } 
      out.write("\n");
      out.write("                                            </tr>");

                                        }
				 
      out.write("\n");
      out.write("                                 ");
      out.write("\n");
      out.write("                </table>\n");
      out.write("                                    <form action=\"RoomServlet\" method=\"post\">\n");
      out.write("                                        <!--weekindex--;-->\n");
      out.write("                                            \n");
      out.write("                                                    ");
 if (weekindex <= 1) { 
      out.write("\n");
      out.write("                                                        <td><input  class=\"inputdisable\" type=\"submit\" disabled = \"disabled\" name=\"Previous1\" value=\"Previous\" /></td>\n");
      out.write("                                                     ");
 } else { 
      out.write("\n");
      out.write("                                                        <td><input  class=\"inputenable\" type=\"submit\" name=\"Previous1\" value=\"Previous\" /></td>\n");
      out.write("                                                    <td><input type=\"hidden\" name=\"parameters\" value=\"");
      out.print(room + " " + orainizio + " " + orafine + " "+ datainizio + " " + weekindex);
      out.write("\"/></td>\n");
      out.write("                                                     ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("                                      </form>\n");
      out.write("                                                     \n");
      out.write("                                    <form action=\"RoomServlet\" method=\"post\">\n");
      out.write("                                        <!--weekindex++;-->\n");
      out.write("                                            \n");
      out.write("                                                    ");
 if (weekindex >= 4) { 
      out.write("\n");
      out.write("                                                        <td><input  class=\"inputdisable\" type=\"submit\" disabled = \"disabled\" name=\"Next1\" value=\"Next\" /></td>\n");
      out.write("                                                     ");
 } else { 
      out.write("\n");
      out.write("                                                        <td><input  class=\"inputenable\" type=\"submit\" name=\"Next1\" value=\"Next\" /></td>\n");
      out.write("                                                    <td><input type=\"hidden\" name=\"parameters\" value=\"");
      out.print(room + " " + orainizio + " " + orafine + " "+ datainizio + " " + weekindex);
      out.write("\"/></td>\n");
      out.write("                                                     ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("                                      </form>\n");
      out.write("                                   \n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t <tbody>\n");
      out.write("\t\t \t\t\n");
      out.write("\t\t <br/>\n");
      out.write("\t </div>\n");
      out.write("                                 \n");
      out.write("                                 <br/>\n");
      out.write("                                 <a href=\"home.jsp\">Torna alla home</a>\n");
      out.write("         </br>  \n");
      out.write("        <a href=\"logout.jsp\">Logout</a>\n");
      out.write("        </center>\n");
      out.write("        <script type=\"text/javascript\" src=\"websocket.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
