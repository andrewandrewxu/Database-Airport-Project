import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class insertairline extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response) 
    						throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String a = request.getParameter("acode");
        String n = request.getParameter("name");
        String w = request.getParameter("website");
        //String studioName = request.getParameter("studio");
        
        String statementString = 
		"INSERT INTO AIRLINES(acode, name, website) " +
        "VALUES( '" + a + "'," + "'" + n + "'," + "'" + w + "')";
        
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(statementString);
            stmt.close();
            out.println("Insertion Successful!");
        }
        catch(SQLException e) { out.println(e); }
        ConnectionManager.getInstance().returnConnection(conn);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    public String getServletInfo() {  return "Insert"; }
}
