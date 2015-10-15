package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

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
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML>\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<title>ese2015-Team9</title>\r\n");
      out.write("\t\t<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("\t\t<meta name=\"description\" content=\"\" />\r\n");
      out.write("\t\t<meta name=\"keywords\" content=\"\" />\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"resources/style.css\" />\r\n");
      out.write("\t</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"schatten\">\r\n");
      out.write("\r\n");
      out.write("<div class=\"header\">\r\n");
      out.write("\t<img src=\"resources/header.jpg\" alt=\"headerImg\"/>\r\n");
      out.write("       \t<div id=\"nav\">\r\n");
      out.write("           \t<ul>\r\n");
      out.write("               \t<li class=\"main\"><a href=\"index.html\">Home</a></li>\r\n");
      out.write("                <li class=\"main\"><a href=\"info.html\">Infos</a></li>\r\n");
      out.write("                <li class=\"main\"><a href=\"ssearch.html\">Suchen</a>\r\n");
      out.write("                   \t<ul>\r\n");
      out.write("                   \t\t<li class=\"sub\"><a href=\"ssearch.html\">Einfache Suche</a></li>\r\n");
      out.write("                    \t<li class=\"sub\"><a href=\"asearch.html\">Erweiterte Suche</a></li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                   \t</li>\r\n");
      out.write("                <li class=\"main\"><a href=\"register.html\">Registrieren</a></li>\r\n");
      out.write("                <li class=\"main\"><a href=\"faq.html\">FAQs</a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("        </div>       \r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("    \t<h1></h1>\r\n");
      out.write("\r\n");
      out.write("  \t<div class=\"stripe\"></div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"sidebar\"></div>\r\n");
      out.write("    \r\n");
      out.write("    <div class=\"footer\">2015 &copy; All rights reserved | <a href=\"agb.html\">AGB</a></div>\r\n");
      out.write("    \r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
