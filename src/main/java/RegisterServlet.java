import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name");
		//String newpassword=MD5Process.md5("password");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String phone_no=request.getParameter("phone_no");
		String place=request.getParameter("place");
		RegisterValue registervalue=new RegisterValue();

		registervalue.setName(name);
		registervalue.setPassword(password);
		registervalue.setEmail(email);
		registervalue.setPhoneno(phone_no);
		registervalue.setPlace(place);
		
		int status=Query.save(registervalue);
		if(status>0){
			out.print("<style>");
			out.print(" .marquee{font-size:200%;}");
			out.print("</style>");
			out.print("<marquee class='marquee' style='color:blue'>Register Successfully..!</marquee>");
			request.getRequestDispatcher("mailservlet").include(request, response);
			request.getRequestDispatcher("index.jsp").include(request, response);
		}else{
			out.print("<style>");
			out.print(" .marquee{font-size:200%;}");
			out.print("</style>");
			out.println("<marquee style='color:red'>Register UnSuccessfully..!Plz Try again later</marquee>");
		}
		out.close();
	}

}
