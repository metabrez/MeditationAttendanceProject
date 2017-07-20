<%@include file="/WEB-INF/views/template/header.jsp"%>

<div class="container-wrapper">
	<div class="container">
		<h2>Student List</h2>

		<table class="table table-striped table-hover" border="1">
			<thead>
				<tr class="bg-success">
					<th>Student ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Visa Status</th>
					<th>Status</th>
					<th>Entry Date</th>
				</tr>
			</thead>
			<c:forEach items="${students}" var="student">
				<tr>
					<td><a href="/staff/studentCoursesAttendances?id=${student.studentId}">${student.studentId}</a></td>
					<td>${student.firstName}</td>
					<td>${student.lastName}</td>
					<td>${student.emailaddress}</td>
					<td>${student.visaStatus}</td>
					<td>${student.status}</td>
					<td>${student.entryDate}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/views/template/footer.jsp"%>