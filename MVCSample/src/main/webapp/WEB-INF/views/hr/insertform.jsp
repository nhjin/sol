<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>사원정보 입력</h1>
<form action="./insert" method="post">
<table border="1">
<tr>
	<th>EMPLOYEE_ID </th> <!-- emp.employeeId 님 환영합니다 -->
	<td><input type="number" name="employeeId" required></td>
</tr>
<tr>
	<th>FIRST_NAME</th>
	<td><input type="text" name="firstName"></td>
</tr>
<tr>
	<th>LAST_NAME</th>
	<td><input type="text" name="lastName" required></td>
</tr>	
<tr>
	<th>EMAIL</th>
	<td><input type="text" name="email" required></td>
</tr>
<tr>
	<th>PHONE_NUMBER</th>
	<td><input type="text" name="phoneNumber"></td>
</tr>
<tr>
	<th>hire_date</th>
	<td><input type="date" name="hireDate" required></td>
</tr>
<tr>
	<th>job_id</th>
	<td>
	<select name="jobId">
	<c:forEach var="job" items="${jobList}">
		<option value="${job.jobId}">${job.title}</option>
	</c:forEach>
	</select>
	</td>
</tr>
<tr>
	<th>SALARY</th>
	<td><input type="number" name="salary"></td>
</tr>
<tr>
	<th>COMMISSION_PCT</th>
	<td><input type="number" name="commissionPct" step="0.1" min="0" max="0.99"></td>
</tr>
<tr>
	<th>manager_id</th>
	<td>
	<select name="managerId">
	<c:forEach var="manager" items="${managerList}">
		<option value="${manager.managerId}">${manager.firstName}</option>
	</c:forEach>
	</select>
	</td>
</tr>
<tr>
	<th>department_id</th>
	<td>
	<select name="departmentId">
	<c:forEach var="department" items="${deptList}">
		<option value="${department.departmentId}">${department.departmentName}</option>
	</c:forEach>
	</select>
	</td>
</tr>
<tr>
<th></th>
<td>
<input type="submit" value="저장">
<input type="reset" value="취소">
</td>
</tr>
</table>
</form>
</body>
</html>