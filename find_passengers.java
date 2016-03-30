import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class q5d extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      String depid = request.getParameter("depid"); 
      String arrid = request.getParameter("arrid");       
 
	  Connection conn = ConnectionManager.getInstance().getConnection();
      try { 
    	  Statement stmt = conn.createStatement();
          if(arrid==null){
    	  ResultSet rset = stmt.executeQuery(
                        "select PID,NAME,GOV_ISSUED_ID,DOB,POB,DEPID,ARRID from Passengers Where depID='"+depid+"'");
      
    	  out.println("<table border=1>");
	  out.println("<th>"+"PID   "+"</th>");
	  out.println("<th>"+"NAME   "+"</th>");
	  out.println("<th>"+"GOV_ISSUED_ID   "+"</th>");
	  out.println("<th>"+"DOB   "+"</th>");
	  out.println("<th>"+"POB   "+"</th>");
	  out.println("<th>"+"DEPID   "+"</th>");
	  out.println("<th>"+"ARRID   "+"</th>");
          while (rset.next()) {
        	  out.println(
        	  "<tr>" +
               	  "<td>"+rset.getString("PID")+"</td>" + 
               	  "<td>"+rset.getString("NAME")+"</td>"+
	       	"<td>"+rset.getString("GOV_ISSUED_ID")+"</td>"+
                "<td>"+rset.getString("DOB")+"</td>"+
		"<td>"+rset.getString("POB")+"</td>"+
		"<td>"+rset.getString("DEPID")+"</td>"+
		"<td>"+rset.getString("ARRID")+"</td>"+
              "</tr>");
          }
          out.println("</table>");
          }else{
    	  ResultSet rset1 = stmt.executeQuery(
                        "select PID,NAME,GOV_ISSUED_ID,DOB,POB,DEPID,ARRID from Passengers Where arrID='"+arrid+"'");
      
    	  out.println("<table border=1>");
	  out.println("<th>"+"PID   "+"</th>");
	  out.println("<th>"+"NAME   "+"</th>");
	  out.println("<th>"+"GOV_ISSUED_ID   "+"</th>");
	  out.println("<th>"+"DOB   "+"</th>");
	  out.println("<th>"+"POB   "+"</th>");
	  out.println("<th>"+"DEPID   "+"</th>");
	  out.println("<th>"+"ARRID   "+"</th>");
          while (rset1.next()) {
        	  out.println(
        	  "<tr>" +	
               	  "<td>"+rset1.getString("PID")+"</td>" + 
               	  "<td>"+rset1.getString("NAME")+"</td>"+
	       	"<td>"+rset1.getString("GOV_ISSUED_ID")+"</td>"+
                "<td>"+rset1.getString("DOB")+"</td>"+
		"<td>"+rset1.getString("POB")+"</td>"+
		"<td>"+rset1.getString("DEPID")+"</td>"+
		"<td>"+rset1.getString("ARRID")+"</td>"+
              "</tr>");
          }
          out.println("</table>");
          }
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
