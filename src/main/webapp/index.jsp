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
        <select id="langId" name="langId">
            <option value = "1"> Language #1 </option>
            <option value = "2"> Language #2 </option>

        </select>
    </div>

    <div>
        <label for="name">Language name</label>
        <input type="text" id="name" name="name">
    </div>
    <button type = "submit"> Create Customer</button>
</form>

<br/>
<a href="demo-servlet">Demo Servlet</a>
</body>
</html>