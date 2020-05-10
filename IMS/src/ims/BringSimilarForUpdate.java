package ims;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class BringSimilarForUpdate extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(AppCommon.CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
               out.println("<head><title>Update</title></head>");
               out.println("<body>");
               out.println("<p><u>UPDATE INVOICE</u></p>");
               out.println("<form name=\"UpdateSimilarForm\" action=\"updateinvoice\" method=\"get\">");
               out.println("<table cellspacing=\"2\" cellpadding=\"3\" border=\"1\" width=\"50%\">");
               
                out.println("</tr><tr><td width=\"50%\">INVOICE NUMBER</td><td width=\"50%\"><input style=\"width: 100%;\" type=\"text\" name=\"invoicenumber\" value=\"" 
                    + request.getParameter("invoicenumber") + "\"/></td>");
               out.println("</tr><tr><td width=\"50%\">DATE</td><td width=\"50%\"><input style=\"width: 100%;\" type=\"text\" name=\"invoicedate\" value=\"" 
                           + request.getParameter("invoicedate").substring(0, 10) + "\"/></td>");
               out.println("</tr><tr><td width=\"50%\">ACCOUNT NUMBER</td><td width=\"50%\"><input style=\"width: 100%;\" type=\"text\" name=\"accountnumber\" value=\"" 
                           + request.getParameter("accountnumber") + "\"/></td>");
               out.println("</tr><tr><td width=\"50%\">NET AMOUNT</td><td width=\"50%\"><input style=\"width: 100%;\" type=\"text\" name=\"netamount\" value=\"" 
                           + request.getParameter("netamount") + "\"/></td>");
               out.println("</tr><tr><td width=\"50%\">GROSS AMOUNT</td><td width=\"50%\"><input style=\"width: 100%;\" type=\"text\" name=\"grossamount\" value=\"" 
                           + request.getParameter("grossamount") + "\"/></td>");
               out.println("</tr><tr><td width=\"50%\">DISCOUNT</td><td width=\"50%\"><input style=\"width: 100%;\" type=\"text\" name=\"discount\" value=\"" 
                           + request.getParameter("discount")  + "\"/></td>");

               out.println("</tr><tr><td width=50%>&nbsp;</td><td width=\"50%\"><input type=\"submit\" name=\"Submit\"/></td></tr></table></form>");
               
               out.println("</body></html>");
               out.close();
        
    }
}
