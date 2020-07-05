package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import DBConnect.DBConnector;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.Calendar;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <head id=\"rcorners\">\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media = \"screen\" >\t\n");
      out.write("        <title>Login</title>\n");
      out.write("    </head>\n");
      out.write("    \n");
      out.write("   <body>\n");
      out.write("       <header>\n");
      out.write("           <p>Gestione Aule Poli</p>\n");
      out.write("       </header>\n");
      out.write("       ");
  
            
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
       
      out.write("\n");
      out.write("        <h3>Login Page</h3>\n");
      out.write("        <center>\n");
      out.write("        <br/>\n");
      out.write("        <a href=\"./easteregg.html\">*</a>\n");
      out.write("        <br/>\n");
      out.write("        <th >Please provide your credential to use this website</th>  \n");
      out.write("        <center>\n");
      out.write("        \n");
      out.write("        <form method=\"post\" action=\"LoginServlet\">\n");
      out.write("            \n");
      out.write("        <table class=\"logintable\" align=\"center\" cellpadding = \"10\">\n");
      out.write("            \n");
      out.write("                <tr>\n");
      out.write("                    <td><div id=\"login-box-name\" >User Mail:</div></td>\n");
      out.write("                    <td><div id=\"login-box-field\"></td>\n");
      out.write("                    <td>\n");
      out.write("                        <input name=\"mail\" type=\"text\" placeholder=\"Username\" title=\"Username\" value=\"\" size=\"30\" maxlength=\"50\" />\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td><div id=\"login-box-name\">Password:</div></td>\n");
      out.write("                    <td>\n");
      out.write("                        <div id=\"login-box-field\">\n");
      out.write("                    </td>        \n");
      out.write("                    <td>\n");
      out.write("                        <input name=\"password\" type=\"password\" placeholder=\"Password\" title=\"Password\" value=\"\" size=\"30\" maxlength=\"48\"/>\n");
      out.write("                        </div>\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("\n");
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                        <td>New User? </td>\n");
      out.write("                        <td>\n");
      out.write("                            <a class=\"register\" href=\"signup.jsp\" >Register Here</a>\n");
      out.write("                        </td>\n");
      out.write("                        <td>\n");
      out.write("                            <input  type=\"submit\" value=\"Login\" />\n");
      out.write("                        </td>    \n");
      out.write("                </tr>\n");
      out.write("        </table>\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
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
