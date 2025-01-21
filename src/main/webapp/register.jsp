<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Register</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="top-bar">
		<button class="back-button" onclick="window.location.href='main.jsp'">Back</button>
	</div>
	<div class="divider"></div>
	<div class="container">
		<h1>Register</h1>
        <c:if test="${not empty errorMessage}">
            <div class="error-message" style="color: red;">
                ${errorMessage}
            </div>
        </c:if>
		<form action="RegisterServlet" method="post">
			<div class="form-group">
				<label for="name">Name:</label> <input type="text" id="name"
					name="name" required>
			</div>
			<div class="form-group">
				<label for="address">Address</label>
				<textarea id="address" name="address" rows="3" required></textarea>
			</div>
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					id="username" name="username" required>
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					id="password" name="password" required>
			</div>
			<div class="form-group">
				<label for="confirm_password">Confirm Password:</label> <input
					type="password" id="confirm_password" name="confirm_password"
					required>
			</div>
			<button type="submit" class="submit-button">Register</button>
		</form>
	</div>
</body>
</html>
