package ims;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.sql.DataSource;

public class UpdateInvoice extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(AppCommon.CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        try {
            Connection con = AppCommon.createConnection();
            con.setAutoCommit(true);
            PreparedStatement ps = con.prepareStatement("UPDATE INVOICES SET  INVOICE_DATE = ?, ACCOUNT_NUMBER = ?, " +
                "NET_AMOUNT = ?, GROSS_AMOUNT = ?, DISCOUNT = ? WHERE INVOICE_NR = ?");
            ps.setInt(6, AppCommon.parseInteger(request.getParameter("invoicenumber")));
            ps.setDate(1,AppCommon.parseDate(request.getParameter("invoicedate")));
            ps.setString(2, request.getParameter("accountnumber"));
            ps.setDouble(3,AppCommon.parseDouble(request.getParameter("netamount")));
            ps.setDouble(4,AppCommon.parseDouble(request.getParameter("grossamount")));
            ps.setDouble(5, AppCommon.parseDouble(request.getParameter("discount")));

            try {
            ps.executeUpdate();
                } catch (SQLException e) {
                out.println("<p> Invoice not updated!  </p>");
                out.println("<p> " + e.getMessage() + " </p>");
                con.commit();
                con.close();
                return;
            }
            out.println("<p> Invoice updated... <a href=\"/IMS/invoiceviewer\"> View the invoice List </a> !</p>");
            
            con.commit();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println("</body></html>");
        out.close();
        
    }
}
