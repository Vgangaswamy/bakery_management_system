<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create a new Language</title>
</head>
<body>
<h1><%= "New Language Form" %></h1>

<c:if test = "${not empty requestScope.violations}">
    <h2>
        Please fix the following errors with your input
    </h2>
    <table border="1">
        <tr>
            <th>Field</th>
            <th>error</th>
        </tr>
        <c:forEach var="violations" items="${requestScope.violations}">
            <tr>
                <td><c:out value="${violations.propertyPath}"/></td>
                <td><c:out value="${violations.message}"/></td>
            </tr>
        </c:forEach>
    </table>

</c:if>
<form method="post" action="language">
    <div>
        <label for="langId">Language ID Number</label>
        <input type="text" id="langId" name="langId" value="${requestScope.language.languageId}">
    </div>

    <div>
        <label for="name">Language name</label>
        <input type="text" id="name" name="name" value="${requestScope.language.name}">
    </div>
    <button type = "submit"> Create Customer</button>
</form>

<br/>
<a href="demo-servlet">Demo Servlet</a>
</body>
</html>