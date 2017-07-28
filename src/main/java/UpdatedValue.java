

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/UpdatedValue")
public class UpdatedValue extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String phone_no=request.getParameter("phone_no");
		String place=request.getParameter("place");
		
		RegisterValue pojo=new RegisterValue();
		pojo.setName(name);
		pojo.setPassword(password);
		pojo.setEmail(email);
		pojo.setPhoneno(phone_no);
		pojo.setPlace(place);
		
		int status=Query.update(pojo);
		if(status>0){
			response.sendRedirect("RegisterViewServlet");
		}else{
			out.println("Sorry! unable to update");
		}
		
		out.close();
	}

}
