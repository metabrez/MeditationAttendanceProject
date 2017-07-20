<%@include file="/WEB-INF/views/template/header.jsp"%>

<div class="container-wrapper">
	<div class="container">
		<div id="login-box">
			<h2>Students Meditation Attendance Report</h2>
			<c:if test="${not empty error}">
                <div style="color: red;">${error}</div>
            </c:if>
			<br>
			<h5>Student ID: ${student.studentId}</h5>
			<h5>Name: ${student.firstName} ${student.lastName}</h5>
			<br>
			<table class="table table-striped table-hover" border="1">
				<thead>
					<tr class="bg-success">
						<th>Course ID</th>
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
						<td>${studentAttendance.courseOffering.course.number}</td>
						<td><a href="/staff/studentCourseAttendanceDetail?offerId=${studentAttendance.courseOffering.id}&studentId=${studentAttendance.student.id}">${studentAttendance.courseOffering.course.name}</a></td>
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