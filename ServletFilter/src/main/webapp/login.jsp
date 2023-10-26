<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login JSP</title>
</head>
<body>
	<form action="authServlet" method="post">
		User name: <input name="username" /> <br />
		Password: <input name="password" type="password"/> <br />
		<input type="submit" value="Submit" /> <br />
	</form>
</body>
</html>