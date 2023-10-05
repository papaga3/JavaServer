<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Demo</title>
</head>
<body>

<c:out value="${9 + 10}" />
	<c:set var="score" value="${80}" scope="session" />
	
	<c:if test="${score>=80}">
		<p> Awesome </p>
	</c:if>
	
	<c:choose>
		<c:when test="${score>=80}">
			<p> Awesome </p>
		</c:when><c:when test="${score<80}">
			<p> Not Awesome </p>
		</c:when>
	</c:choose>
	<c:out value="${score}" />
	<c:remove var="score"/>
	<c:out value="${score}" />
	
	
	<%
		List<String> names = new ArrayList<String>();
		names.add("demo");
		names.add("simple");
		names.add("something");
		request.setAttribute("names", names);
	%>
	
	<c:forEach var="curName" items="${names}">
		<c:out value="${curName}" /> <br/>
	</c:forEach>
	
	<c:set var="number" value="123.8888" />
	<fmt:parseNumber var="i" type="number" value="${number}" />
	<p> Number is: <c:out value="${i}" /> </p>
	
	<p> Formatted number 1: <fmt:formatNumber value="${number}" type="number" /> </p>
	
	<p> Formatted number 2: <fmt:formatNumber value="${number}" type="number" maxFractionDigits="2"/> </p>
	
	<p> Formatted number 3: <fmt:formatNumber value="${number}" type="number" maxIntegerDigits="2"/> </p>
	
	<p> Formatted number 4: <fmt:formatNumber value="${number}" type="number" pattern="####.##"/> </p>
	
</body>
</html>