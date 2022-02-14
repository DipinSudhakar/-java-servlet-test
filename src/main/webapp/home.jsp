<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
	<form action = "TotalMarkServlet">
		<input type = "submit" value = "Candidates Total Marks">
	</form>
</div>
<div>
	<form action = "ResultServlet">
		<input type = "submit" value = "Candidates with Promotion Status">
	</form>
</div>
<div>
	<form action = "GraceMarkResultServlet">
		<input type = "submit" value = "Final Result with GraceMarks added or not">
	</form>
</div>
</body>
</html>