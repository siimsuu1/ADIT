<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adit Service</title>
</head>

<%
	String requestURL = "/adit";
	try {
		requestURL = request.getContextPath();
	} catch (Exception e) {
		;
	}
%>

<body>
	<h1>Adit Service</h1>
	
	
	<ul>
		<li><a href="<%=requestURL%>/service">Web-Service</a></li>
		<li><a href="<%=requestURL%>/service/adit.wsdl">Web-Service WSDL</a></li>
		<li><a href="<%=requestURL%>/monitor">Monitor</a></li>
	</ul>
	
</body>
</html>