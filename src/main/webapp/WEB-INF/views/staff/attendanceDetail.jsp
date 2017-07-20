<%@include file="/WEB-INF/views/template/header.jsp"%>

<script type="text/javascript">
	function confirmDelete(studentId,barcodeRecordId) {
		document.getElementById("studentId").value = studentId;
		document.getElementById("barcodeRecordId").value = barcodeRecordId;
		
		var check = confirm("Are you sure to delete this record for ["+studentId+"]?");
		if(check == true) {
			return true;
		}
		else {
			return false;
		}
	} 
</script>
<div class="container-wrapper">
	<div class="container">
		<div id="login-box">

			<h2>Attendance Detail</h2>

			<form name="detailForm" action="/staff/deleteAttendance" method="post">
				<c:if test="${not empty error}">
	                <div style="color: red;">${error}</div>
	            </c:if>
				<c:if test="${not empty delResult}">
	                <div class="msg">${delResult}</div>
	            </c:if>
	            
				<input type="hidden" name="studentId" id="studentId" value="">
				<input type="hidden" name="barcodeRecordId" id="barcodeRecordId" value="">
				<input type="hidden" name="offerId" id="offerId" value="${offerId}">
				
				<table  class="table table-striped table-hover" border="1">
					<thead>
						<tr class="bg-success">
							<th>Date</th>
							<th>Building</th>
							<th>Location</th>
							<th>Time Slot</th>
							<th>Time Slot Description</th>
							<th>Delete</th>
						</tr>
					</thead>
					<c:forEach items="${attendanceDetails}" var="barcodeRecord">
						<tr>
							<td>${barcodeRecord.date}</td>
							<td>${barcodeRecord.location.building}</td>
							<td>${barcodeRecord.location.name}</td>
							<td>${barcodeRecord.timeslot.id}</td>
							<td>${barcodeRecord.timeslot.title}</td>
							<td><button type="submit" onClick="return confirmDelete('${studentId}','${barcodeRecord.id}');">Delete</button></td>
						</tr>
					</c:forEach>
				</table>
			</form>
			<br/>
		</div>
	</div>
</div>

<%@include file="/WEB-INF/views/template/footer.jsp"%>