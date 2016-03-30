import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class q5c extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      String time = request.getParameter("time"); 
      
 
	  Connection conn = ConnectionManager.getInstance().getConnection();
      try { 
    	  Statement stmt = conn.createStatement();
    	  ResultSet rset = stmt.executeQuery(
                        "SELECT DEPID,GATE,DEPT,RNUM From DEPARTURES Where (24*((To_Date((DEPARTURES.DEPT),'HH24:MI - MM-DD-YYYY'))- (To_Date('"+
                        time+"','HH24:MI - MM-DD-YYYY')))<=1) AND (24*((To_Date((DEPARTURES.DEPT),'HH24:MI - MM-DD-YYYY'))- (To_Date('"+time+"','HH24:MI - MM-DD-YYYY')))>=-1)");
      
    	  out.println("<table border=1>");
	  out.println("<th>"+"DEPID   "+"</th>");
	  out.println("<th>"+"GATE   "+"</th>");
	  out.println("<th>"+"DEPT   "+"</th>");
	  out.println("<th>"+"RNUM   "+"</th>");
          while (rset.next()) {
        	  out.println(
        	  "<tr>" +
               	  "<td>"+rset.getString("DEPID")+"</td>" + 
               	  "<td>"+rset.getString("GATE")+"</td>"+
	       	"<td>"+rset.getString("DEPT")+"</td>"+
		"<td>"+rset.getString("RNUM")+"</td>"+
              "</tr>");
          }
          out.println("</table>");
    	  ResultSet rset1 = stmt.executeQuery(
                        "SELECT ARRID,GATE,ARRT,RNUM From ARRIVALS Where (24*((To_Date((ARRIVALS.ARRT),'HH24:MI - MM-DD-YYYY'))- (To_Date('"+
                        time+"','HH24:MI - MM-DD-YYYY')))<=1) AND (24*((To_Date((ARRIVALS.ARRT),'HH24:MI - MM-DD-YYYY'))- (To_Date('"+time+"','HH24:MI - MM-DD-YYYY')))>=-1)");
      
    	  out.println("<table border=1>");
	  out.println("<th>"+"ARRID   "+"</th>");
	  out.println("<th>"+"GATE   "+"</th>");
	  out.println("<th>"+"ARRT   "+"</th>");
	  out.println("<th>"+"RNUM   "+"</th>");
          while (rset1.next()) {
        	  out.println(
        	  "<tr>" +	
               	  "<td>"+rset1.getString("ARRID")+"</td>" + 
               	  "<td>"+rset1.getString("GATE")+"</td>"+
	       	"<td>"+rset1.getString("ARRT")+"</td>"+
		"<td>"+rset1.getString("RNUM")+"</td>"+
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
