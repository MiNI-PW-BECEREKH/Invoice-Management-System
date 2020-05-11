package ims;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

public class DeleteInvoice extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(AppCommon.CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        
        try{
            Connection con = AppCommon.createConnection();
            con.setAutoCommit(true);
            PreparedStatement ps =null;
            
            if (AppCommon.CONNECTION_SOURCE == AppCommon.SQL_SERVER_SOURCE) {
                ps = con.prepareStatement("DELETE FROM INVOICES WHERE INVOICE_NR = ?");
            }
            
            ps.setInt(1, AppCommon.parseInteger(request.getParameter("invoicenumber")));
           
            
            try{
                ps.executeUpdate();
            }catch(SQLException e){
                out.println("<p>Invoice couldn't deleted</p>");
                out.println("<p>" + e.getMessage() + "</p>");
                con.commit();
                con.close();
                return;
            }
            out.println("<p> Invoice deleted... <a href=\"/IMS/invoiceviewer\"> View the invoice List </a> !</p>");
            
            
            con.commit();
            con.close();
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        out.println("</body></html>");
        out.close();
    }
}
