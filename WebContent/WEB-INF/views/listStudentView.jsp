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
	
	<h2><spring:message code="student.page.list" text="student.page.list" /></h2>
	<br/>
	
<%-- 	<form:form id ="frm1" action="deleteStudent" method="post" > --%>
	<table id="listOfStudents" border="1">
	<tr>
	    <td>Name</td>
	    <td>Personal Number</td>
	    <td>Email</td>
	    <td>Delete</td>
    </tr>
   
	<c:forEach items="${allStudents}" var="student">    
	  <tr>
	    <td><a href="editStudent?id=${student.id}">${student.fullName}</a></td>
	    <td>${student.personalNumber}</td>
	    <td>${student.email}</td>
	    <td><a href="deleteStudent?id=${student.id}">Delete</a></td>
      </tr>
	</c:forEach>
	</table>
	<table>
	</table>
<%-- 	</form:form> --%>
	
	<h2><spring:message code="student.page" text="Add New Student" /></h2>
	<br/>
	<form:form method="post" modelAttribute="student" action="addStudent">
		<table>
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
				<td colspan="3"><input type="submit" value="Add Student"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>