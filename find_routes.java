import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class q5a extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      String a_code = request.getParameter("acode"); 
      
 
	  Connection conn = ConnectionManager.getInstance().getConnection();
      try { 
    	  Statement stmt = conn.createStatement();
    	  ResultSet rset = stmt.executeQuery(
                        "SELECT rnum " +
                        "FROM Routes " +
                        "Where acode="+"'"+a_code+"'");
      
    	  out.println("<table>");
	  out.println("<td>"+"Routes Number"+"</td>");
          while (rset.next()) {
        	  out.println(
        	  "<tr>" +
               	  "<td>"+rset.getString("rnum")+"</td>" + 
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
