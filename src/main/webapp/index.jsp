<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create a new Language</title>
</head>
<body>
<h1><%= "New Language Form" %></h1>
<form method="post" action="/vgangaswamy-fp/language">
    <div>
        <label for="langId">Language ID Number</label>
        <input type="text" id="langId" name="langId">
    </div>

    <div>
        <label for="name">Language name</label>
        <input type="text" id="name" name="name">
    </div>
</form>

<br/>
<a href="demo-servlet">Demo Servlet</a>
</body>
</html>