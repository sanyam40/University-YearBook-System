<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
		<title>
            YEAR BOOK SYSTEM - FACULTY LOGIN
        </title>
		<link rel="stylesheet" href="FacultyLoginCSS.css">
	</head>
			
        <body>
			<div class="Faculty-Login">
				<h1>ONLINE UNIVERSITY YEAR BOOK SYSTEM</h1>
                <h2>FACULTY LOGIN</h2>
				<form method="post" action="Teacher_login_servlet">
				
				    <p>Username</p>
                    <input type="text" placeholder="Enter Username" name="username">
				    <p>Password</p>
                    <input type="password" placeholder="Enter Password" name="password" >
                    <input type="submit" value="Sign In">
                    <p>Don't have an account? <a href='Teacher_newregister_servlet.jsp'>Create a new account</a></p>

				</form>
			</div>
		</body>
</html>