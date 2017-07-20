<%@include file="/WEB-INF/views/template/header.jsp"%>

<script type="text/javascript">
	function validate() {
		var studentID = document.getElementById("studentId").value;
		if(studentID == "") {
			alert("Please input Student ID to search!");
			return false;
		}
		document.getElementById("searchStudentId").value = studentID;
		return true;
	} 
</script>
<div class="container-wrapper">
	<div class="container">
		<h2>Find Students</h2>
		<form action="/staff/findStudentResult" method="POST">
			Enter Student ID (*):<input type="text"	name="studentId" id="studentId" required placeholder="Student ID">
			<button type="submit" onClick="return validate();">Search</button>
			<input type="hidden" name="searchStudentId" id="searchStudentId" value="">
		</form>
	</div>
</div>

<%@include file="/WEB-INF/views/template/footer.jsp"%>
