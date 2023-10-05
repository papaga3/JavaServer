<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Display Product </title>
</head>
<body>
	<jsp:useBean id="product" class="huy.java.server.Product">
		<jsp:setProperty name="product" property="*"/>
	</jsp:useBean>
	
	Product Details:
	<br /> ID: <jsp:getProperty property="id" name="product" />
	<br /> Name: <jsp:getProperty property="name" name="product" />
	<br /> Description: <jsp:getProperty property="desc" name="product" />
	<br /> Price: <jsp:getProperty property="price" name="product" />
	
	Name: <%=product.getName() %>
</body>
</html>