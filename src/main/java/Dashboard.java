import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServletResponse;
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {  
  
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
            try{
       response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
      HttpSession session=request.getSession(false);    
   String name=(String)session.getAttribute("name"); 
    out.print("Hello "+name);
    
    if(name.equals("admin"))
    {
        out.println("<html>");
        out.println("<link rel='stylesheet' type='text/css' href='bootstrap.min.css'>");
        out.println("<body>");
        out.println("<form action='RegisterViewServlet'>");
        out.println("<input type='submit' value='View Registration' class='btn btn-primary'>");
        out.println("</form></body></html>");
    }
     request.getRequestDispatcher("logout.jsp").include(request, response);
    } 
    catch(Exception e){System.out.println(e);}  
 }
  
}  