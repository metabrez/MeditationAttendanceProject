<%@include file="/WEB-INF/views/template/header.jsp"%>

<div class="container-wrapper">
	<div class="container">
		<div id="login-box">
			
			<form method="POST" action="${contextPath}/login" class="form-signin">
		        <h2 class="form-heading">Log in</h2>
		
		        <div class="form-group ${error != null ? 'has-error' : ''}">
		            <span>${message}</span>
		            <input name="username" type="text" class="form-control" placeholder="Username"
		                   autofocus="true"/>
		            <input name="password" type="password" class="form-control" placeholder="Password"/>
		            <span>${error}</span>
		            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		
		            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
		        </div>		
		    </form>
		    
		</div>
	</div>
</div>

<%@include file="/WEB-INF/views/template/footer.jsp"%>