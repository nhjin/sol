<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! 
String[] strList = {"Hello", "World", "Kosa"};
%>
<c:set var="strList" value='"Hello", "World", "kosa"' scope="request"></c:set>
<c:forEach var="msg" items="${strList}">
<h1>${msg}</h1>
 </c:forEach>

</body>
</html> 