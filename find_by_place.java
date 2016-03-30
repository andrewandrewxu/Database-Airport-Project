import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class q5b extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      String place = request.getParameter("place"); 
      
 
	  Connection conn = ConnectionManager.getInstance().getConnection();
      try { 
    	  Statement stmt = conn.createStatement();
    	  ResultSet rset = stmt.executeQuery(
                        "(SELECT rnum from OutgoingRoutes where destination='" +
                        place+"'"+") union (SELECT rnum from OutgoingRoutes where destination='"+place+"')");
      
    	  out.println("<table border=1>");
	  out.println("<th>"+"Routes with "+place+"</th>");
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
