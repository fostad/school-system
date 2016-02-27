<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Course Management</title>
	<style>
	.error 
	{
		color: #ff0000;
		font-weight: bold;
	}
	#listOfCourses tr:first-child{
		font-weight: bold;
	}
	</style>
</head>

<body>
	
	<h2><spring:message code="lbl.page.edit" text="Edit Course" /></h2>
	<br/>
	<form:form method="post" modelAttribute="course" action="updateCourse">
		<table>
			<tr>
				<td><form:input readonly path="id" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.name" text="Name" /></td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.points" text="Points" /></td>
				<td><form:input path="points" /></td>
				<td><form:errors path="points" cssClass="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.teacher" text="Teacher" /></td>
				<td><form:select path="teacher" items="${allTeachers}" itemValue="id" itemLabel="fullName" /></td>
				<td><form:errors path="teacher" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Update"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>