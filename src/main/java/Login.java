import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Login")
public class Login extends HttpServlet { 
   public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
            try{
    response.setContentType("text/html");
        PrintWriter out = response.getWriter();  
    
        String email = request.getParameter("email");
        String pass = request.getParameter("password");        
        String name=Query.login(email,pass);
        if(name!=null)
        {
            HttpSession session = request.getSession();
            String msg="Login Successfully";
            Log4jFile.msg(msg,name);
            session.setAttribute("name",name);
            response.sendRedirect("dashboard");
            out.close();
        }
    else
    {  
        response.setContentType("text/html");
        out.print("<style>");
		out.print(" .marquee{font-size:200%;}");
		out.print("</style>");
        out.println("<marquee style='color:red'>Username and Password Wrong....!!!</marquee>");  
       request.getRequestDispatcher("login.jsp").include(request, response);
        }  
    } 
   catch(Exception e){System.out.println(e);}   
}  
}