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
  <table cellSpacing="8" cellPadding="8" border="20">
<form action="RegisterServlet" method="post" align="center"> 
 
<tr><td>Name*</td><td><input type="text" name="name" placeholder="Enter your name" required></td></tr>  
<tr><td>Password</td><td><input type="password" name="password" placeholder="Enter your password" required></td></tr>  
<tr><td>Email</td><td><input type="email" name="email" placeholder="Enter your email ID" required></td></tr>  
<tr><td>Phone Number</td><td><input type="text" name="phone_no" placeholder="Enter your Phone Number" required></td></tr>
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