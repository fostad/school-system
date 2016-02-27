<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Teacher Management</title>
	<style>
	.error 
	{
		color: #ff0000;
		font-weight: bold;
	}
	#listOfTeachers tr:first-child{
		font-weight: bold;
	}
	</style>
</head>

<body>
	
	<h2><spring:message code="teacher.page.edit" text="Edit Teacher" /></h2>
	<br/>
	<form:form method="post" modelAttribute="teacher" action="updateTeacher">
		<table>
			<tr>
				<td><form:input type="hidden" path="id" /></td>
			</tr>
			<tr>
				<td><spring:message code="teacher.firstName" text="First Name" /></td>
				<td><form:input path="firstName" /></td>
				<td><form:errors path="firstName" cssClass="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="teacher.lastName" text="Last Name" /></td>
				<td><form:input path="lastName" /></td>
				<td><form:errors path="lastName" cssClass="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="teacher.personalNumber" text="Personal Number" /></td>
				<td><form:input path="personalNumber" /></td>
				<td><form:errors path="personalNumber" cssClass="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="teacher.email" text="Email" /></td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Update"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>