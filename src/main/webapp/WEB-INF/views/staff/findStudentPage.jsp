<%@include file="/WEB-INF/views/template/header.jsp"%>

<div class="container-wrapper">
	<div class="container">
		<form action="/findStudentById" method="GET">
			<div class="divFindStudent">
				Enter StudentId:<Span id="spanId"> * </Span><input type="text"
					name="studentId" id="studentId" required placeholder="student Id">
				<input type="submit" value="Find" id="requestButton">
			</div>
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/layout/footer.jsp"%>
