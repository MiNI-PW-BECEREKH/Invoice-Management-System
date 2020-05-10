package ims;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;

public class InvoiceViewer extends HttpServlet {
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
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT INVOICE_NR,INVOICE_DATE,ACCOUNT_NUMBER,NET_AMOUNT,GROSS_AMOUNT,DISCOUNT FROM INVOICES");
            
            out.println("<p><u>INVOICES</u>  <a href=\"/IMS/FilterForm.html\">FILTER INVOICES</a>  </p>");
            out.println("<table border=\"1\" style=\"width:100%;\">\n");          
            out.println("<tr><th>INVOICE NUMBER</th><th>DATE</th><th>ACCOUNT NUMBER</th><th>NET AMOUNT</th><th>GROSS AMOUNT</th>"+
                        "<th>DISCOUNT</th><th>ACTIONS</th></tr>\n");
            
            while(rs.next()){
                out.println("<tr><td style=\"text-align:right\">" 
                            + rs.getString("INVOICE_NR") + "</td><td style=\"text-align:right\">"
                            + rs.getString("INVOICE_DATE") + "</td><td style=\"text-align:right\">"
                            + rs.getString("ACCOUNT_NUMBER") + "</td><td style=\"text-align:right\">"
                            + rs.getString("NET_AMOUNT") + "</td><td style=\"text-align:right\">"
                            + rs.getString("GROSS_AMOUNT") + "</td><td style=\"text-align:right\">"
                            + rs.getString("DISCOUNT") + "%" 
                            + "</td><td> <a href=\"/IMS/bringsimilarforinsert" 
                            + "?invoicenumber=" + rs.getString("INVOICE_NR")
                            + "&invoicedate=" + rs.getString("INVOICE_DATE")
                            + "&accountnumber=" + rs.getString("ACCOUNT_NUMBER")
                            + "&netamount=" + rs.getString("NET_AMOUNT")
                            + "&grossamount=" + rs.getString("GROSS_AMOUNT")
                            + "&discount=" + rs.getString("DISCOUNT")
                            + "\">INSERT SIMILAR</a>"
                            + " | "
                            + "<a href=\"/IMS/bringsimilarforupdate" 
                            + "?invoicenumber=" + rs.getString("INVOICE_NR")
                            + "&invoicedate=" + rs.getString("INVOICE_DATE")
                            + "&accountnumber=" + rs.getString("ACCOUNT_NUMBER")
                            + "&netamount=" + rs.getString("NET_AMOUNT")
                            + "&grossamount=" + rs.getString("GROSS_AMOUNT")
                            + "&discount=" + rs.getString("DISCOUNT")
                            + "\">UPDATE</a>"
                            + " | "
                            + "<a href=\"/IMS/deleteinvoice" 
                            + "?invoicenumber=" + rs.getString("INVOICE_NR")
                            + "&invoicedate=" + rs.getString("INVOICE_DATE")
                            + "&accountnumber=" + rs.getString("ACCOUNT_NUMBER")
                            + "&netamount=" + rs.getString("NET_AMOUNT")
                            + "&grossamount=" + rs.getString("GROSS_AMOUNT")
                            + "&discount=" + rs.getString("DISCOUNT")
                            + "\">DELETE</a>"
                            + "</td></tr>"
                            );
            }
            out.println("</table>");
            statement.close();
            rs.close();
            con.close();
                                  
        }catch(Exception e){
            e.printStackTrace();
        }
        
        out.println("</body></html>");
        out.close();
    }
}
