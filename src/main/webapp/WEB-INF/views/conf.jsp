<%--
  Created by IntelliJ IDEA.
  User: Cupcake
  Date: 9/16/24
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>confirmation page</title>
</head>
<body>
    <h1> Confirmation page</h1>
<ul>
    <li>Language ID: ${requestScope.language.languageId}</li>
    <li>Language name:${requestScope.language.name}</li>
</ul>
</body>
</html>
