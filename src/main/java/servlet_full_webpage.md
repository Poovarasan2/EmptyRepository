# Creating Web Based Project using Servlet
----------

# JSP Page

- index.jsp
- login.jsp
- logout.jsp

# CSS File

- bootstrap.min.css


# Servlet Page

- Dashboard.java
- DeleteServlet.java
- Login.java
- Logout.java
- MailServlet.java
- Query.java
- RegisterServlet.java
- RegisterValue.java
- RegisterViewServlet.java
- UpdateDetail.java
- UpdatedValue.java

# index.jsp

This index file is visible to user, the user can fill the corresponding detail through this jsp page and this page will automatically send the details in servlet page(RegisterServlet.java) using form action concept.

```````
<html>
<head> 
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="bootstrap.min.css"> 
     <style>
      .container {
    max-width: 750px;
  }
  h1{
    color: red;
  }
      </style>
</head>
 <body>
<div class="container">
      <div class="header">
        <h1 align="center"><b><i>Registration</i></b></h1>
        <ul class="nav nav-pills pull-right">
              <li class="active"><a href="index.jsp">Register</a></li>
            <li class=" " id="register"><a href="login.jsp">Login</a></li>
        </ul>
      </div>
</div>
<center>
  <table cellSpacing="8" cellPadding="8" border="30">
<form action="RegisterServlet" method="post" align="center"> 
 
<tr><td>Name*</td><td><input type="text" name="name" placeholder="Enter your name"></td></tr>  
<tr><td>Password</td><td><input type="password" name="password" placeholder="Enter your password"></td></tr>  
<tr><td>Email</td><td><input type="email" name="email" placeholder="Enter your email ID"></td></tr>  
<tr><td>Phone Number</td><td><input type="text" name="phone_no" placeholder="Enter your Phone Number"></td></tr>
<tr><td>Place</td><td><select name="place">  
<option>Coimbatore</option>  
<option>Erode</option>  
<option>Salem</option>  
<option>Namakkal</option>  
</select>
</td></tr>
</table>
<input type="submit" value="Register" class="btn btn-primary"/></br>
</form>
</center>
 </body>
</html>
```````

# RegisterServlet.java
This servlet page get the request from the jsp page and store the user details in seperated String and send it to the POJO class.

```````
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
			out.print("<p>Register Successfully!</p>");
			request.getRequestDispatcher("mailservlet").include(request, response);
			request.getRequestDispatcher("index.jsp").include(request, response);
		}else{
			out.println("Unsuccessfully");
		}
		out.close();
	}

}

```````
#MailServlet.java
This class is your to send the mail automatically,while the register can registed.

````````
import java.io.IOException; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.*;
import java.net.Authenticator;
import java.util.*; 
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
@WebServlet("/mailservlet")

public class MailServlet extends HttpServlet {
   MailServlet javaEmail=null;
 public void init() throws ServletException {}
     public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
       {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            String toMail = request.getParameter("email");
            final String username = "poovarasan.g@kggroup.com";
            final String password = "PoovKgfsl1";
Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable", "false");
props.put("mail.smtp.host", "webmail.kggroup.com");
props.put("mail.smtp.port", "25");
Session session = Session.getInstance(props,
new javax.mail.Authenticator() {
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
try {
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("poovarasan.g@kggroup.com"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse(toMail));
message.setSubject("Event Registration !!!");
message.setText("Your Event has been Successfully registered!");
Transport.send(message);
}
catch(Exception e)
{
    e.printStackTrace();
}
        }        
    }

````````
# RegisterValue.java
It is a pojo class, there is only set and get function, It can only store and return the value.

```````
public class RegisterValue {  
private String name,password,email,phone_no,place;  
public String getName() {  
    return name;  
}  
public void setName(String name) {  
    this.name = name;  
}  
public String getPassword() {  
    return password;  
}  
public void setPassword(String password) {  
    this.password = password;  
}  
public String getEmail() {  
    return email;  
}  
public void setEmail(String email) {  
    this.email = email;  
}  
public String getPhoneno() {  
    return phone_no;  
}  
public void setPhoneno(String phone_no) {  
    this.phone_no = phone_no;  
}  
  public String getPlace() {  
    return place;  
}  
public void setPlace(String place) {  
    this.place = place;  
} 
}  
```````
# Query.java
This class having only the Database Connection and Query for Create,Retrieve,Update,Delete[CRUD].

```````
import java.util.*;  
import java.sql.*;  
  
public class Query {  
  
    public static Connection getConnection(){  
        Connection con=null;  
        try{  
           Class.forName("com.mysql.jdbc.Driver");
            con =DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "");
 
        }catch(Exception e){System.out.println(e);}  
        return con;  
    }  
    public static int save(RegisterValue registervalue){  
        int status=0;  
        try{  
            Connection con=Query.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
                         "insert into crud(name,password,email,phone_no,place) values (?,?,?,?,?)");  
            ps.setString(1,registervalue.getName());  
            ps.setString(2,registervalue.getPassword());  
            ps.setString(3,registervalue.getEmail());
            ps.setString(4,registervalue.getPhoneno());  
            ps.setString(5,registervalue.getPlace());  
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ System.out.println("Your Email ID is already used....!!!");}
        return status;  
    }  
     public static int login(String name,String password){  
        int status=0,userresult=0; 
        String dbname="",dbpass=""; 
        try{  
            Connection con=Query.getConnection();
            PreparedStatement ps=con.prepareStatement("select name,password from crud where name=?"); 
            ps.setString(1,name);
            ResultSet rs=ps.executeQuery();  
            int i=0;
            if (rs.next()) {
              dbname=rs.getString(1);  
                dbpass=rs.getString(2);  
            }   
        }

        catch(Exception e)
        {
           e.printStackTrace();
          
        }
        if((password.equals(dbpass))&&(name.equals(dbname)))
        {
                userresult=1;
                return userresult;
            }
            else
            {
                return userresult;
            }
    }
    public static int update(RegisterValue registervalue){  
        int status=0;  
        try{  
            Connection con=Query.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
                         "update crud set password=?,email=?,phone_no=?,place=? where name=?");  
            
            ps.setString(1,registervalue.getPassword());  
            ps.setString(2,registervalue.getEmail());  
             ps.setString(3,registervalue.getPhoneno());  
            ps.setString(4,registervalue.getPlace());  
            ps.setString(5,registervalue.getName());  
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }  
    public static int delete(String name){  
        int status=0;  
        try{  
            Connection con=Query.getConnection();  
            PreparedStatement ps=con.prepareStatement("delete from crud where name=?");  
            ps.setString(1,name);  
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return status;  
    }  
    public static RegisterValue getStudentName(String name){  
        RegisterValue registervalue=new RegisterValue();  
          
        try{  
            Connection con=Query.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from crud where name=?");  
            ps.setString(1,name);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){    
                registervalue.setName(rs.getString(1));  
                registervalue.setPassword(rs.getString(2));  
                registervalue.setEmail(rs.getString(3)); 
                registervalue.setPhoneno(rs.getString(4));  
                registervalue.setPlace(rs.getString(5));  
            }  
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return registervalue;  
    }  
    public static List<RegisterValue> getAllStudent(){  
        List<RegisterValue> list=new ArrayList<RegisterValue>();  
          
        try{  
            Connection con=Query.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from crud");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                RegisterValue registervalue=new RegisterValue();  
                registervalue.setName(rs.getString(1));    
                registervalue.setPassword(rs.getString(2));  
                registervalue.setEmail(rs.getString(3));
                registervalue.setPhoneno(rs.getString(4));  
                registervalue.setPlace(rs.getString(5));  
                list.add(registervalue);  
            }  
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return list;  
    }  
}  
```````
# RegisterViewServlet.java
This servlet page is used to display all value in the database by using Query.java page. Query.java having Query for select all value from the table.

```````
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/RegisterViewServlet")
public class RegisterViewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<a href='dashboard'><--Back</a>");
		out.println("<h1>Registration List</h1>");
		
		List<RegisterValue> list=Query.getAllStudent();
		
		out.print("<table border='1' width='100%'");
		out.print("<tr><th>Name</th><th>Password</th><th>Email</th><th>PhoneNo</th><th>Place</th><th>Edit</th><th>Delete</th></tr>");
		for(RegisterValue pojo:list){
			out.print("</td><td>"+pojo.getName()+"</td><td>"+pojo.getPassword()+"</td><td>"+pojo.getEmail()+"</td><td>"+pojo.getPhoneno()+"</td><td>"+pojo.getPlace()+"</td><td><a href='UpdateDetail?name="+pojo.getName()+"'>edit</a></td><td><a href='DeleteServlet?name="+pojo.getName()+"'>delete</a></td></tr>");
		}
		out.print("</table>");
		
		out.close();
	}
}

```````
# UpdateDetail.java
This servlet page is used to edit the user information.This page will display the predefined value from the user.

```````
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
```````
# UpdatedValue.java
This servlet page is used to change/modify the value from the database by using Query.java page. Query.java having Query for edit the value from the table.

```````


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
```````

# DeleteServlet.java
This servlet page is used to delete the particular records form the table by using Query.java page. Query.java having Query for delete the value from the table.

```````
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		Query.delete(name);
		response.sendRedirect("RegisterViewServlet");
	}
}
```````
# login.jsp
This login file is used to get information from the user to start the session.Collection the information from the user and send to the servlet page through form action concept.
```````
<html>
<head>
  <title>Login</title>
  <link rel="stylesheet" type="text/css" href="bootstrap.min.css"> 
     <style>
      .container {
    max-width: 750px;
  }
  h1{
    color: red;
  }
  
      </style>
</head>
<body>
      <div class="container">
      <div class="header">
        <h1 align="center"><b><i>Login</i></b></h1>
        <ul class="nav nav-pills pull-right">
              <li class=""><a href="index.jsp">Register</a></li>
            <li class="active" id="register"><a href="login.jsp">Login</a></li>
        </ul>
      </div>
</div>
<form action="Login" method="post">
<center>
<table cellSpacing="8" cellPadding="8" border="30">
<tr><td>Name</td><td><input type="text" name="name" placeholder="Enter your username"/></td></tr> 
<tr><td>Password</td><td><input type="password" name="password" placeholder="Enter your Password"/></td></tr> 
</table>
<input type="submit" value="Login" class="btn btn-primary"/></br>
</center>
</form>
</body>
</html>

```````
# Login.java
This servlet page get the information from the user by using login.jsp page and check with the Database whether the user details is correct or not. If the user will give correct details, this page will start the session.
```````
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
        String name = request.getParameter("name");
        String pass = request.getParameter("password");       
        int result=Query.login(name,pass);
        if(result==1)
        {
            HttpSession session = request.getSession();
            session.setAttribute("hello",name);
            response.sendRedirect("dashboard");
            out.close();
        }
    else
    {  
        response.setContentType("text/html");
        out.println("Username and Password Wrong....!!!");  
       request.getRequestDispatcher("login.jsp").include(request, response);
        }  
    } 
   catch(Exception e){System.out.println(e);}   
}  
}
```````
# Dashboard.java
This servlet page is used start the session and display the dashboard page,(i.e) Check the session page and directly call the action.And also give the privilege to the particular user.

```````
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
   String name=(String)session.getAttribute("hello"); 
    out.print("hello "+name);
    if(name.equals("admin"))
    {
        out.println("Admin Rights");
        out.println("<a href='RegisterViewServlet'>view Registration</a>");
    }
     request.getRequestDispatcher("logout.jsp").include(request, response);
    } 
    catch(Exception e){System.out.println(e);}  
 }
  
}  
```````

# logout.jsp
This page is simple call the Logout servlet page.

```````
<html>
        <body>
            <form action="logout" align="right">
       <input type="submit" value="Logout"> 
        </body>
        </html>
```````

# Logout.java
This servlet page is simple close the session by using  session.invalidate() function.

```````
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
@WebServlet("/logout")
public class Logout extends HttpServlet {  

public void doGet(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
            try{
    response.setContentType("text/html");
        PrintWriter out = response.getWriter();  
         HttpSession session=request.getSession(false); 
        session.invalidate();
        out.println("Logout Successfully");
        request.getRequestDispatcher("index.jsp").include(request, response);
    } 
   catch(Exception e){System.out.println(e);}  
  
}  
}
```````