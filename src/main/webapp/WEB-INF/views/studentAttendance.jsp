<%@include file="/WEB-INF/views/template/header.jsp"%>

<div class="container-wrapper">
	<div class="container">
		<div id="login-box">

			<div>
				<a href="courseOfferingList">Course Offerings</a>
			</div>

			<h2>Students Meditation Attendance Report</h2>

			<table class="table table-striped table-hover" border="1">
				<thead>
					<tr class="bg-success">
						<th>Course Id</th>
						<th>Course Name</th>
						<th>Start Date</th>
						<th>Meditation Count</th>
						<th>Meditation Required</th>
						<th>Meditation Percentage</th>
						<th>Meditation Extra Grade</th>
					</tr>
				</thead>
				<c:forEach items="${attendances}" var="studentAttendance">
					<tr>
						<td>${studentAttendance.courseOffering.id}</td>
						<td>${studentAttendance.courseOffering.course.name}</td>
						<td>${studentAttendance.courseOffering.startDate}</td>
						<td>${studentAttendance.getMeditationCount()}</td>
						<td>
							<c:out value="${empty studentAttendance.courseOffering.block ? '0' : studentAttendance.courseOffering.block.requiredSessions}" />
							<%--${studentAttendance.courseOffering.block.requiredSessions}--%>
						</td>
						<td>${studentAttendance.getMeditaionPercentage()}</td>
						<td>${studentAttendance.getMeditationExtraGrade()}</td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>
</div>

<%@include file="/WEB-INF/views/template/footer.jsp"%>