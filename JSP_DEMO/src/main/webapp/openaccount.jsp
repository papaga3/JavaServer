<%@ page
    import="java.sql.*" 
    language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    errorPage="errorHandler.jsp"
%>
<%!
   Connection conn;
   PreparedStatement ps;
   
   public void jspInit() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mydb", "root", "root");
			ps = conn.prepareStatement("INSERT INTO account (firstname, lastname, point) VALUES (?, ?, ?)");
		} catch(Exception e) {
			e.printStackTrace();
		} 
   }
   
   public void jspDestroy() {
	   try {
		   ps.close();
		   conn.close();
	   } catch(Exception e) {
		   e.printStackTrace();
	   }
   }
%>

<%
   String firstname = request.getParameter("firstname");
   String lastname = request.getParameter("lastname");
   int point = Integer.parseInt(request.getParameter("point"));
   ps.setString(1, firstname);
   ps.setString(2, lastname);
   ps.setInt(3, point);
   ps.executeUpdate();
%>

<%@ include file="openaccount.html" %>
<html>
	<body>
		<h1> Something </h1>
	</body>
</html>