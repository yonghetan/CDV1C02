<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="top-bar">
		<button class="back-button" onclick="window.location.href='main.jsp'">Back</button>
	</div>
	<div class="divider"></div>
    <div class="container">
        <h1>Login Page Updated 4</h1>
        <c:if test="${not empty errorMessage}">
            <div class="error-message" style="color: red;">
                ${errorMessage}
            </div>
        </c:if>
        <c:if test="${not empty successMessage}">
            <div class="success-message" style="color: green;">
                ${successMessage}
            </div>
        </c:if>
        <form action="LoginServlet" method="POST">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="submit-button">Login</button>
        </form>
    </div>
</body>
</html>