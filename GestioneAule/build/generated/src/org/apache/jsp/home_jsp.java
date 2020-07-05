package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.List;
import GpService.LoginService;
import java.util.Date;
import GpUser.User;
import GpRoom.Room;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\t <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("\t <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\"/>\n");
      out.write("\t <title>Result Page</title>\n");
      out.write("         \n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <header>\n");
      out.write("           <p>Gestione Aule Poli</p>\n");
      out.write("       </header>\n");
      out.write("<center>\n");
      out.write("\t <div id=\"container\">\n");
      out.write("\t\t <h1>Lista aule</h1>\n");
      out.write("                 <br/>\n");
      out.write("                 <a href=\"./easteregg.html\">*</a>\n");
      out.write("                 <br/>\n");
      out.write("\t\t\t ");
      out.print(new Date());
      out.write("</br>\n");
      out.write("\t\t\t ");

				 User user = (User) session.getAttribute("user");
			 
      out.write("\t\t\n");
      out.write("\t\t\t <b>Welcome ");
      out.print( user.getFirstname() + " " + user.getLastname());
      out.write("</b>\t\t\n");
      out.write("\t\t\t <br/>\n");
      out.write("                         \n");
      out.write("                       \n");
      out.write("                         \n");
      out.write("                   <table class=\"hometable\">\n");
      out.write("\t\t\t <thead align=\"center\">\n");
      out.write("                            <tr>\n");
      out.write("                                <th>Aula</th>\n");
      out.write("                                <th>Premi il pulsante per visualizzare la vista</th>\n");
      out.write("                            </tr>\n");
      out.write("\t\t\t </thead>\n");
      out.write("\t\t\t <tbody>\n");
      out.write("                               \n");
      out.write("\t\t\t\t ");

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
				 
      out.write("\n");
      out.write("\t\t\t\t <tr>\n");
      out.write("                                    ");
      out.write("\n");
      out.write("                                    <form action=\"RoomServlet\" method=\"POST\">\n");
      out.write("                                        <tr><input type=\"hidden\" name=\"parameters\" value=\"");
      out.print(aula + " " + orainizio + " " + orafine + " "+ giornoinizio + " " + weekindex);
      out.write("\"/></tr>\n");
      out.write("                                        <td><div name=\"nomeaula\">");
      out.print(aula);
      out.write("</div></td>\n");
      out.write("                                        ");
      out.write("\n");
      out.write("                                        <td><input class=\"visualizza\" type=\"submit\" name=\"");
      out.print(aula);
      out.write("\" value=\"Visualizza\"/></td>\n");
      out.write("                                    </form>\n");
      out.write("\t\t\t\t </tr>\n");
      out.write("\t\t\t\t ");
}
      out.write("\n");
      out.write("\t\t\t <tbody>\n");
      out.write("\t\t </table>\t\t\n");
      out.write("\t\t <br/>\n");
      out.write("\t </div>     \n");
      out.write("        </br>  \n");
      out.write("        <a href=\"logout.jsp\">Logout</a>                 \n");
      out.write("\t</center>\t\n");
      out.write("                         \n");
      out.write("        \n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
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
