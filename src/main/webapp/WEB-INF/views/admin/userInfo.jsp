<%@include file="/WEB-INF/views/template/header.jsp"%>

<div class="container-wrapper">
	<div class="container">
		<div id="login-box">
			<h2>User Details Information</h2>
			<form action="<c:url value="search"/>" method="get">
				<div>
					Search User: <input type="text" name="userName" id="userName"
						placeholder="Search..">
					<button type="submit" value="">search</button>
				</div>
			</form>

			<table class="table table-striped table-hover" border="1">
				<thead>
					<tr class="bg-success">
						<th>User Name</th>
						<th>Password</th>
						<th>Role</th>
						<th>Update</th>
						<th>Delete</th>
					</tr>
				</thead>

				<c:forEach items="${userList}" var="user">
					<tr>
						<td>${user.userName}</td>
						<td>${user.password}</td>
						<td>${user.role.name}</td>
						<td>
							<a href="userInfo/edit/${user.userName}">
								<span class="glyphicon glyphicon-pencil"/>
							</a>
						</td>
						<td>
							<a href="userInfo/delete/${user.userName}"/>
								<span class="glyphicon glyphicon-remove"/>
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>

<%@include file="/WEB-INF/views/template/footer.jsp"%>