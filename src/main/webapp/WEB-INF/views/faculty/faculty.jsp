<!DOCTYPE html>
<html lang="en">
<head>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Faculty page</title>

<!-- Bootstrap core CSS -->
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="/resources/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/resources/css/dashboard.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="/resources/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Meditation Attendance System</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<c:if test="${pageContext.request.userPrincipal.name != null}">
						<form id="logoutForm" method="POST" action="${contextPath}/logout">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
						<h4 style="color: white;" align="right">Welcome Faculty [${pageContext.request.userPrincipal.name}]</h4>
						<h4 align="right">
							<a onclick="document.forms['logoutForm'].submit()">Logout</a>
						</h4>
					</c:if>
					<c:if test="${pageContext.request.userPrincipal.name==null}">
						<h4>
							<a href="<c:url value="/login"/>">Login</a>
						</h4>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<c:forEach items="${courseOfferingList}" var="courseOffering">
						<li><a href="?name=${courseOffering.getId()}">${courseOffering.course.name}${' ('}${courseOffering.getBlock().getId()}${')'}</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<!-- 				<h1 class="page-header">Dashboard</h1> -->
				<c:if test="${couserOfferingList.size < 1}">
					There is no course which taught by faculty within last 6 months.
				</c:if>
				<h4 class="sub-header">Attendance report for:
					${courseOfferingList.get(0).getCourse().getName()}${', '}${courseOfferingList.get(0).getBlock().getId()}</h4>
				<table class="table table-striped table-hover" border="1"
					style="font-size: 12px">
					<thead>
						<tr class="bg-success">
							<!-- <th>Photo Thumb</th> -->
							<th style="width: 40px">No</th>
							<th style="width: 190px">Name</th>
							<th style="width: 100px">ID</th>
							<th>Attendance</th>
							<th>Percent</th>
							<th>Extra grade</th>
						</tr>
					</thead>
					<c:set var="no" value="0" />
					<c:forEach items="${facultyCourseAttendance}"
						var="studentAttendance">
						<tr>
							<td>${no = no + 1}</td>
							<td style="text-align: left">${studentAttendance.getStudent().getFirstName()}${' '}${studentAttendance.getStudent().getLastName()}</td>
							<td>${studentAttendance.getStudent().getStudentId()}</td>
							<td>${studentAttendance.getMeditationCount()}</td>
							<td><fmt:formatNumber
									value="${studentAttendance.getMeditaionPercentage()}"
									maxFractionDigits="2" /></td>
							<td>${studentAttendance.getMeditationExtraGrade()}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="/resources/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="/resources/js/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="/resources/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
