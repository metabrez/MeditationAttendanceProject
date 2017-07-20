<%@include file="/WEB-INF/views/template/header.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	function validateForm() {
		var barcode = document.getElementById("barcode").value;
		var date = document.getElementById("date").value;
		var barcode_format = /^\d{13}$/;
		//var date_format = /^(0?[1-9]|1[0-2])\/(0?[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
		var date_format = /^(19|20)\d{2}\-(0?[1-9]|1[0-2])\-(0?[1-9]|1\d|2\d|3[01])$/;
		
		if(!barcode.match(barcode_format)) {
			alert("Barcode value must be 13 digits!");
			return false;
		}
		
		if(!date.match(date_format)) {
			alert("Date is invalid!");
			return false;
		}
		
		return true;
	} 
</script>
<div class="container-wrapper">
	<div class="container">
		<form:form method="POST" action="/staff/saveAttendance" modelAttribute="attendanceRecord">
			<h2>Create a Meditation Attendance Record</h2>
			<c:if test="${not empty createResult}">
                <div class="msg">${createResult}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div style="color: red;">${error}</div>
            </c:if>
			<table align="center" border="0" cellspacing="500" cellpadding="200">
				<tr>
					<td align="right">Student's barcode:&nbsp;&nbsp;</td>
					<td align="left"><form:input id="barcode" name="barcode" path="barcode" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">Date (YYYY-MM-DD):&nbsp;&nbsp;</td>
					<td align="left"><form:input id="date" name="date" path="date" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">Location:&nbsp;&nbsp;</td>
					<td align="left">
						<form:select path="location">
							<c:forEach var="location" items="${locationList}">
			                    <form:option value="${location.id}"><c:out value="${location.id}-${location.name}"/></form:option>
			                </c:forEach>
				       	</form:select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">Time slot:&nbsp;&nbsp;</td>
					<td align="left">
						<form:select path="timeslot">
							<c:forEach var="timeslot" items="${timeslotList}">
			                    <form:option value="${timeslot.id}"><c:out value="${timeslot.id}-${timeslot.title}"/></form:option>
			                </c:forEach>
				       	</form:select>
				   	</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td></td>
					<td align="left"><form:button id="btnSubmit" type="submit" onclick="return validateForm();">Create</form:button></td>
				</tr>
			</table>
		</form:form>
	</div>
</div>

<%@include file="/WEB-INF/views/template/footer.jsp"%>