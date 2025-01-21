<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib uri="jakarta.tags.core" prefix="c"%>
    <title>Item Listing</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="top-bar">
		<button class="login-button"
			onclick="window.location.href='login.jsp'">Logout</button>
	</div>
	<div class="divider"></div>
    <div class="container">
        <h1>Item Listing</h1>

        <c:if test="${not empty itemList}">
            <table class="item-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Value</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${itemList}">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.name}</td>
                            <td>${item.quantity}</td>
                            <td>${item.value}</td>
                            <td><button class="buy-button" onclick="alert('Buy functionality is not implemented yet')">Loan</button></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty itemList}">
            <p>No items available.</p>
        </c:if>
    </div>
</body>
</html>
