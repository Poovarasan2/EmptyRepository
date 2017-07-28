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
<table cellSpacing="8" cellPadding="8" border="20">
<tr><td>Email ID</td><td><input type="email" name="email" placeholder="Enter your email ID" required/></td></tr> 
<tr><td>Password</td><td><input type="password" name="password" placeholder="Enter your Password" required/></td></tr> 
</table>
<input type="submit" value="Login" class="btn btn-success"/></br>
</center>
</form>
</body>
</html>
