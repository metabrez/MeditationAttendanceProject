<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/views/template/header.jsp"%>


<div class="container-wrapper">
	<div class="container">
		<div id="login-box">
			<h2>Fill the given information</h2>

			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>

			<form name="createForm" action="<c:url value="/createUser"/> "
				method="post" commandName="user">
				<c:if test="${not empty error}">
					<div class="error" style="color: #ff0000;">${error}</div>
				</c:if>
				<div class="form-group">
					<label for="username">User Name</label> <input type="text"
						id="username" name="userName" class="form-control" />
				</div>
				<div class="form-group">
					<label for="password">Password</label> <input type="password"
						id="password" name="password" class="form-control" />
				</div>

				<div>
					Select Role <select class="selectpicker" name="roleId">
						<option value="1">Admin</option>
						<option value="2">Faculty</option>
						<option value="3">Staff</option>
						<option value="4">Student</option>
					</select>
				</div>

				<!--   //..............Drop Down............. -->

				<div class="form-group">
					<label for="studentId">Student Id</label> <input type="text"
						id="studentId" name="studentId" class="form-control" />
				</div>

				<div class="form-group">
					<label for="facultyId">Faculty Id</label> <input type="text"
						id="facultyId" name="facultyId" class="form-control" />
				</div>

				<input type="submit" value="Submit" class="btn btn-default" />

				<hr />
				
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
	</div>
</div>
<%@include file="/WEB-INF/views/template/footer.jsp"%>