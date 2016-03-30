import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class q5e extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      String pid = request.getParameter("pid"); 
      
 
	  Connection conn = ConnectionManager.getInstance().getConnection();
      try { 
    	  Statement stmt = conn.createStatement();
    	  ResultSet rset = stmt.executeQuery(
                        "SELECT bid, weight, pid " +
                        "FROM Baggage " +
                        "Where pid="+"'"+pid+"'");
      
    	  out.println("<table border=1>");
	  out.println("<td>"+"BaggageID  "+"</td>");
	  out.println("<td>"+"Weight  "+"</td>");
	  out.println("<td>"+"PassengerID"+"</td>");
          while (rset.next()) {
        	  out.println(
        	  "<tr>" +
               	  "<td>"+rset.getString("bid")+"</td>"+ 
               	  "<td>"+rset.getString("WEIGHT")+"</td>"+ 
               	  "<td>"+rset.getString("PID")+"</td>"+ 
             //  	  "<td>"+rset.getString("year")+"</td>"+
              "</tr>");
          }
          out.println("</table>");
          stmt.close();
      }
      catch(SQLException e) { out.println(e); }
      ConnectionManager.getInstance().returnConnection(conn);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                   						throws ServletException, IOException { 
    	processRequest(request, response);     }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                   						throws ServletException, IOException {
    	processRequest(request, response);     }
    
    public String getServletInfo() {  return "Movie Servlet 1"; }
}
