import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class q5g extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

   //   String place = request.getParameter("place"); 
      
 
	  Connection conn = ConnectionManager.getInstance().getConnection();
      try { 
    	  Statement stmt = conn.createStatement();
    	  ResultSet rset = stmt.executeQuery(
                        "SELECT RNUM,GATE,'done' as STATUS FROM DEPARTURES WHERE (24*(sysdate - (To_Date((DEPARTURES.DEPT),'HH24:MI - MM-DD-YYYY')))>0) union SELECT rnum,gate,'done' as status FROM ARRIVALS WHERE (24*(sysdate - (To_Date((ARRIVALS.ARRT),'HH24:MI - MM-DD-YYYY')))>0)");
      
    	  out.println("<table border=1>");
	  out.println("<th>"+"RNUM   "+"</th>");
	  out.println("<th>"+"GATE   "+"</th>");
	  out.println("<th>"+"STATUS   "+"</th>");
          while (rset.next()) {
        	  out.println(
        	  "<tr>" +
               	  "<td>"+rset.getString("RNUM")+"</td>" + 
              	  "<td>"+rset.getString("GATE")+"</td>"+
              	  "<td>"+rset.getString("STATUS")+"</td>"+
              "</tr>");
          }
          out.println("</table>");
          ResultSet rset1 = stmt.executeQuery(
                        "UPDATE gates SET gates.isfree=1 WHERE gate in (SELECT gate FROM ARRIVALS WHERE (24*(sysdate - (To_Date((ARRIVALS.ARRT),'HH24:MI - MM-DD-YYYY')))>0) AND gates.gate=ARRIVALS.gate) OR gate in (SELECT gate FROM DEPARTURES WHERE (24*(sysdate - (To_Date((DEPARTURES.DEPT),'HH24:MI - MM-DD-YYYY')))>0) AND gates.gate=DEPARTURES.gate)");

          ResultSet rset2 = stmt.executeQuery(
                        "SELECT GATE,ISFREE FROM GATES ");

          out.println("<table border=1>");
	  out.println("<th>"+"GATE   "+"</th>");
	  out.println("<th>"+"ISFREE   "+"</th>");
          while (rset2.next()) {
        	  out.println(
        	  "<tr>" +
               	  "<td>"+rset2.getString("GATE")+"</td>" + 
              	  "<td>"+rset2.getString("ISFREE")+"</td>"+
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
