<%@include file="/WEB-INF/views/template/header.jsp"%>
<div class="container-wrapper">
	<div class="container">
		<div id="login-box">
			<h2>Attandance Detail</h2>
			<c:set var="count" value="0" />
			<table class="table table-striped table-hover" border="1">
				<thead>
					<tr class="bg-success">
						<th>Date</th>
						<th>Building</th>
						<th>Location</th>
						<th>Time Slot</th>
						<th>Time Slot Description</th>
					</tr>
				</thead>
				<c:forEach items="${attendanceDetails}" var="barcodeRecord">
					<tr>
						<td>${barcodeRecord.date}</td>
						<td>${barcodeRecord.location.building}</td>
						<td>${barcodeRecord.location.name}</td>
						<td>${barcodeRecord.timeslot.id}</td>
						<td>${barcodeRecord.timeslot.title}</td>
					</tr>
				</c:forEach>
			</table>
			<%-- <div>Meditation count:${count}</div>	 --%>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/views/template/footer.jsp"%>
