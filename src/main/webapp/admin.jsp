<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib uri="jakarta.tags.core" prefix="c"%>
<title>Admin Dashboard</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<!-- Top bar -->
	<div class="top-bar">
		<button class="login-button"
			onclick="window.location.href='login.jsp'">Logout</button>
	</div>
	<div class="divider"></div>

	<!-- Main container -->
	<div class="container">
		<h1>Admin Dashboard</h1>
		<c:if test="${not empty updateMessage}">
			<div
				class="message-box ${updateMessage == 'Users updated successfully!' ? 'success' : 'error'}">
				${updateMessage}</div>
		</c:if>
		<form method="post" action="AdminServlet">
			<table
				style="width: 100%; border-collapse: collapse; margin: 20px 0;">
				<thead>
					<tr style="background-color: #f4f4f4;">
						<th style="padding: 10px; border: 1px solid #ddd;">Username</th>
						<th style="padding: 10px; border: 1px solid #ddd;">Password</th>
						<th style="padding: 10px; border: 1px solid #ddd;">Admin</th>
						<th style="padding: 10px; border: 1px solid #ddd;">Active</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty userList}">
						<c:forEach var="user" items="${userList}">
							<tr>
								<input type="hidden" name="id_${user.id}" value="${user.id}">
								<td>${user.username}</td>
								<td>${user.password}</td>
								<td><select name="admin_${user.id}">
										<option value="1" ${user.admin? "selected" : ""}>Yes</option>
										<option value="0" ${user.admin? "" : "selected"}>No</option>
								</select></td>
								<td><select name="active_${user.id}">
										<option value="1" ${user.active? "selected" : ""}>Active</option>
										<option value="0" ${user.active? "" : "selected"}>Inactive</option>
								</select></td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty userList}">
						<p>No users found.</p>
					</c:if>
				</tbody>
			</table>
			<button type="submit" class="submit-button">Save Changes</button>
		</form>
	</div>
</body>
</html>
