import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/UpdateDetail")
public class UpdateDetail extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<h1>Update Registration Details</h1>");
		String name=request.getParameter("name");
		
		RegisterValue pojo=Query.getStudentName(name);
		
		out.print("<form action='UpdatedValue' method='post'>");
		out.print("<table>");
		out.print("<tr><td>Name:</td><td><input type='text' name='name' value='"+pojo.getName()+"'/></td></tr>");
		out.print("<tr><td>Password:</td><td><input type='password' name='password' value='"+pojo.getPassword()+"'/></td></tr>");
		out.print("<tr><td>Email:</td><td><input type='email' name='email' value='"+pojo.getEmail()+"'/></td></tr>");
		out.print("<tr><td>Phone No:</td><td><input type='number' name='phone_no' value='"+pojo.getPhoneno()+"'/></td></tr>");
		out.print("<tr><td>Place:</td><td>");
		out.print("<select name='place' style='width:150px'>");
		out.print("<option>Coimbatore</option>");
		out.print("<option>Erode</option>");
		out.print("<option>Salem</option>");
		out.print("<option>Namakkal</option>");
		out.print("</select>");
		out.print("</td></tr>");
		out.print("<tr><td><input type='submit' value='Edit &amp; Save '/></td></tr>");
		out.print("</table>");
		out.print("</form>");
		
		out.close();
}
}