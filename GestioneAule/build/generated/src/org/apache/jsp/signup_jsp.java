package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class signup_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Registration Form</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media = \"screen\" >\n");
      out.write("       \n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <center>\n");
      out.write("        <header>\n");
      out.write("           <p>Gestione Aule Poli</p>\n");
      out.write("       </header>\n");
      out.write("        <h3>Registration Form</h3>\n");
      out.write("        <br/>\n");
      out.write("                <a  href=\"./easteregg.html\">*</a>\n");
      out.write("                <br/>\n");
      out.write("        <form action=\"RegisterServlet\" method=\"POST\">\n");
      out.write("\n");
      out.write("        <table align=\"center\" cellpadding = \"10\">\n");
      out.write("            <tr>\n");
      out.write("            <td>First Name</td>\n");
      out.write("            <td><input type=\"text\" name=\"firstName\" maxlength=\"30\"/>\n");
      out.write("            (max 30 characters a-z and A-Z)\n");
      out.write("            </td>\n");
      out.write("            </tr>\n");
      out.write("\n");
      out.write("            <tr>\n");
      out.write("            <td>Last Name</td>\n");
      out.write("            <td><input type=\"text\" name=\"lastName\" maxlength=\"30\"/>\n");
      out.write("            (max 30 characters a-z and A-Z)\n");
      out.write("            </td>\n");
      out.write("            </tr>\n");
      out.write("            \n");
      out.write("            <tr>\n");
      out.write("            <td>Stato</td>\n");
      out.write("            <td><input type=\"radio\" name=\"status\" value=\"professore\">\n");
      out.write("                   <label for=\"contactChoice1\">Professore</label>\n");
      out.write("\n");
      out.write("                   <input type=\"radio\" name=\"status\" value=\"studente\" checked=\"checked\">\n");
      out.write("                   <label for=\"contactChoice2\">Studente</label>\n");
      out.write("            </td>\n");
      out.write("            </tr>\n");
      out.write("\n");
      out.write("            <tr>\n");
      out.write("            <td>Email</td>\n");
      out.write("            <td><input type=\"text\" name=\"email\" maxlength=\"100\" /></td>\n");
      out.write("            </tr>\n");
      out.write("\n");
      out.write("            <tr>\n");
      out.write("            <td>Password</td>\n");
      out.write("            <td><input type=\"text\" name=\"password\" maxlength=\"100\" /></td>\n");
      out.write("            </tr>\n");
      out.write("\n");
      out.write("            <tr>\n");
      out.write("            <td colspan=\"2\" align=\"center\">\n");
      out.write("            <input type=\"submit\" value=\"Submit\">\n");
      out.write("            <input type=\"reset\" value=\"Reset\">\n");
      out.write("            </td>\n");
      out.write("            </tr>\n");
      out.write("        </table>\n");
      out.write("\n");
      out.write("        </form>\n");
      out.write("        <div align=\"center\">\n");
      out.write("        <a  href=\"./login.jsp\" >Click here to access Gestione Aule Poli</a>\n");
      out.write("        </div>\n");
      out.write("        </center>\n");
      out.write("    </body>\n");
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
