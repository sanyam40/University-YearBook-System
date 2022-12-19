<!DOCTYPE html>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<title>YEAR BOOK SYSTEM - LOGIN</title>
<link rel="stylesheet" href="student_login_css.css">
</head>
<body>
<div class="student_login_form">
<h2>Online University Yearbook System Faculty Login Page</h2>
<form method="post" action="Teacher_login_servlet">
<fieldset>
username : <input type="text" name="username" /><br /><br />
password : <input type="password" name="password" /><br /><br />
</fieldset>
<input type="hidden" name="secret" value="888" />
<a href='Teacher_newregister_servlet.jsp'>New Register</a>
<input type="submit" value="SEND" >
<input type="reset" value="CLEAR" >
</form>
</div>
</body>
</html>

