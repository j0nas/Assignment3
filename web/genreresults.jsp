<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Genre Selection</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body>
<br>

<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <h1>MusicHelper</h1>

        <jsp:useBean id="albums" scope="request" type="java.util.ArrayList"/>
        <c:forEach items="${albums}" var="album">
            ${album.toString()}<br>
        </c:forEach>
        <br>

        <p>
            <button type="button" class="btn btn-default" onclick="window.location = '/';">Back</button>
        </p>
    </div>
</div>
</body>
</html>
