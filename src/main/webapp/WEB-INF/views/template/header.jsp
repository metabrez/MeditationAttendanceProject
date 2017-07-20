<html>
<head>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Meditation Attendance System</title>
<%--Angular js--%>
<script src="https://code.angularjs.org/1.4.8/angular.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<%--javasc--%>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet">


<!-- Custom styles for this template -->
<link href="<c:url value="/resources/css/carousel.css"/>"
	rel="stylesheet">

<%--Main ccc--%>

<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">

<link href="<c:url value="/resources/css/common.css"/>" rel="stylesheet">
</head>


<!-- NAVBAR
================================================== -->
<body>
	<div class="navbar-wrapper">
		<div class="container">

			<nav class="navbar navbar-inverse navbar-static-top">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar"
							aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">Meditation Attendance System</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<!-- <ul class="nav navbar-nav">
							<li><a href="<c:url value="/"/>">Home</a></li>
							<li><a href="<c:url value="/courses/coursesList"/>">Course Sign Up</a></li>
							<li><a href="#contact">Contact</a></li>
							<li><a href=<c:url value="/studentDetails/students"/>>Student Details</a></li>
							<li><a href=<c:url value="/studentDetails/courseOfferings"/>>Course Offerings</a></li>
							<li><a href=<c:url value="/courseList"/>>StudentCourse List</a></li>
							<li><a href=<c:url value="/studentAttendace"/>>StudentAttendace List</a></li>
						</ul> -->

						<ul class="nav navbar-nav pull-right">
							<c:if test="${not empty pageContext.request.userPrincipal}">
							    <!-- Student menu -->
							    <sec:authorize access="hasRole('ROLE_STUDENT') and isAuthenticated()">
									<c:if test="${pageContext.request.userPrincipal.name != null}">
										<form id="logoutForm" method="POST" action="${contextPath}/logout">
								            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								        </form>
								
								        <h4 style="color: white;" align="right">Welcome Student [${pageContext.request.userPrincipal.name}] | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h4>
									</c:if>	
								</sec:authorize>
								
								<!-- Staff menu -->
								<sec:authorize access="hasRole('ROLE_STAFF') and isAuthenticated()">
									<c:if test="${pageContext.request.userPrincipal.name != null}">
										<form id="logoutForm" method="POST" action="${contextPath}/logout">
								            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								        </form>
								
								        <h4 style="color: white;" align="right">Welcome Staff [${pageContext.request.userPrincipal.name}]</h4>
								        <h4>
								        	<a href="/staff/createAttendance">Create Attendance Record</a> | 
								        	<a href="/staff/findStudent">Find Students</a> |
								        	<a onclick="document.forms['logoutForm'].submit()">Logout</a>
								        </h4>
									</c:if>	
								</sec:authorize>
								
								<!-- Faculty menu -->
								<sec:authorize access="hasRole('ROLE_FACULTY') and isAuthenticated()">
									<c:if test="${pageContext.request.userPrincipal.name != null}">
										<form id="logoutForm" method="POST" action="${contextPath}/logout">
								            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								        </form>
								
								        <h4 style="color: white;" align="right">Welcome Faculty [${pageContext.request.userPrincipal.name}] | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h4>
									</c:if>	
								</sec:authorize>
								
								<!-- Admin menu -->
								<sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
									<c:if test="${pageContext.request.userPrincipal.name != null}">
										<form id="logoutForm" method="POST" action="${contextPath}/logout">
								            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								        </form>
								
								        <h4 style="color: white;" align="right">Welcome Admin [${pageContext.request.userPrincipal.name}]</h4>
								        <h4>
								        	<a href="/admin/newUser">Create an User</a> |
								        	<a href="/admin/userInfo">View Users</a> |
								        	<a onclick="document.forms['logoutForm'].submit()">Logout</a>
								        </h4>
									</c:if>	
								</sec:authorize>
							</c:if>

							<c:if test="${pageContext.request.userPrincipal.name==null}">
								<h4><a href="<c:url value="/login"/>">Login</a></h4>
							</c:if>

						</ul>
					</div>
				</div>
			</nav>

		</div>
	</div>