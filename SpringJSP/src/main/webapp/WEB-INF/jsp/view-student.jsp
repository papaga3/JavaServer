<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View students</title>
</head>
<body>
	<h1>Hello World</h1>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${students}" var="student">
				<tr>
					<td>${student.id}</td>
					<td>${student.firstName}</td>
					<td>${student.lastName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>