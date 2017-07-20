<%@include file="/WEB-INF/views/template/header.jsp"%>

<div class="container-wrapper">
	<div class="container">
		<div id="login-box">
			<h2>Courses Detail</h2>
			<form action="studentCourseMeditation">
				<table class="table table-striped table-hover" border="1">
					<thead>
						<tr class="bg-success">
							<th>Course Id</th>
							<th>Course Name</th>
							<th>View Attendance</th>
						</tr>
					</thead>
					<c:forEach items="${enrollmentList}" var="enrollmentList">
						<tr>
							<td>${enrollmentList.offering.id}</td>
							<td>${enrollmentList.offering.course}</td>
							<!--need to pass enrollmentList.offering object to StudentCoursesMeditation-->
							<td><a
								href="/student/studentCourseMeditation?courseID=${enrollmentList.offering.id}">View
									Attandence</a></td>
							<%-- <td><input type = "submit" value="${enrollmentList.offering}" name="courseID"></td> --%>
						</tr>
					</c:forEach>
				</table>
			</form>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/views/template/footer.jsp"%>