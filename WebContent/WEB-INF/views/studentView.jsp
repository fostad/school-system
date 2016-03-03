<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Student Management</title>
	<style>
	.error 
	{
		color: #ff0000;
		font-weight: bold;
	}
	#listOfStudents tr:first-child{
		font-weight: bold;
	}
	</style>
	<script type="text/javascript">
	checked=false;
	function checkedAll (frm1) 
	{
		var aa= document.getElementById('frm1'); 
		if (checked == false){
			checked = true
		}
		else{
			checked = false
		}
		for (var i =0; i < aa.elements.length; i++){
			aa.elements[i].checked = checked;
		}
	}
	</script>
</head>

<body>
	<table>
		<tr>
			
			<td>
				<h2><spring:message code="student.page.edit" text="Edit Student" /></h2>
				<br/>
				<form:form method="post" modelAttribute="student" action="updateStudent">
					<table>
						<tr>
							<td><form:input type="hidden" path="id" /></td>
						</tr>
						<tr>
							<td><spring:message code="person.firstName" text="First Name" /></td>
							<td><form:input path="firstName" /></td>
							<td><form:errors path="firstName" cssClass="error" /></td>
						</tr>
						<tr>
							<td><spring:message code="person.lastName" text="Last Name" /></td>
							<td><form:input path="lastName" /></td>
							<td><form:errors path="lastName" cssClass="error" /></td>
						</tr>
						<tr>
							<td><spring:message code="person.personalNumber" text="Personal Number" /></td>
							<td><form:input path="personalNumber" /></td>
							<td><form:errors path="personalNumber" cssClass="error" /></td>
						</tr>
						<tr>
							<td><spring:message code="person.email" text="Email" /></td>
							<td><form:input path="email" /></td>
							<td><form:errors path="email" cssClass="error" /></td>
						</tr>
						<tr>
							<td colspan="3"><input type="submit" value="Update"/></td>
						</tr>
					</table>
				</form:form>
			</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>			
			<td>
				Student Courses 
				<br>
				<br>
				<table id="listOfCourses" border="1">
				<tr>
				    <td>ID</td>
				    <td>Name</td>
				    <td>Points</td>
			    </tr>
				<c:forEach items="${studentCourses}" var="course">    
				  <tr>
				    <td>${course.id}</td>
				    <td>${course.name}</td>
				    <td>${course.points}</td>
				  </tr>
				</c:forEach>
				</table>
			</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>
				<form:form method="post" modelAttribute="student" action="addCourses">
				<br>
				<br>
				<table id="listOfCourses" border="1">
				<tr>
					<td>Select</td>
				    <td>ID</td>
				    <td>Name</td>
				    <td>Points</td>
			    </tr>
				<c:forEach items="${freeCourses}" var="course">    
				  <tr>
				  	<td><form:checkbox path="freeCourses" value="${course.id}"></form:checkbox></td>
				    <td>${course.id}</td>
				    <td>${course.name}</td>
				    <td>${course.points}</td>
				  </tr>
				</c:forEach>
					
				</table>
				<form:input type="hidden" path="id" />
				<input type="submit" value="Add Course"/>
				</form:form>
			</td>
		</tr>
	</table>
</body>
</html>