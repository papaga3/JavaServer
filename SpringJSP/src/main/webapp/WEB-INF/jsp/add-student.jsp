<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Student</title>
</head>
<body>
	<body>
		<c:if test="${addStudentSuccess}">
			<div> Add student successful: ${savedStudent.id} </div>
		</c:if>
		
		<c:url var="add_student_url" value="/student/addStudent"/>
        <form:form action="${add_book_url}" method="post" modelAttribute="student">
            <form:label path="id">ID: </form:label> <form:input type="text" path="id"/>
            <form:label path="lastName">Last Name: </form:label> <form:input type="text" path="lastName"/>
            <form:label path="firstName">First Name: </form:label> <form:input path="firstName"/>
            <input type="submit" value="submit"/>
        </form:form>
	</body>
</body>
</html>