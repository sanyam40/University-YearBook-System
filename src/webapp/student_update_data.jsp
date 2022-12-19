<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action=Student_Update_servlet method='post'>
<legend>UPDATE DATA</legend>
    sno : <input type="text" name="sno" /><br /><br />
    id : <input type="text" name="id" /><br /><br />
    Acheived_By : <input type="text" name="Acheived_By" /><br /><br />
    Name : <input type="text" name="Name" /><br /><br />
    Task : <input type="text" name="Task" /><br /><br />
    Achievements : <input type="text" name="Achievements" /><br /><br />
    Year : <input type="text" name="Year" /><br /><br />
  </fieldset>
  <input type="hidden" name="secret" value="888" />
  <input type="submit" value="SEND" />
  <input type="reset" value="CLEAR" />
</form>

</body>
</html>