<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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

        <p>Select a music genre you like.</p>

        <form action="${pageContext.request.contextPath}/genreresults" method="get">
            <label>
                Genre
                <select name="genre" onchange="this.form.submit();">
                    <option disabled="disabled" selected="selected">Select a genre</option>
                    <jsp:useBean id="genres" scope="request" type="java.util.ArrayList"/>
                    <c:forEach items="${genres}" var="genre">
                        <option value="${genre}">${genre}</option>
                    </c:forEach>
                </select>
            </label>
        </form>
    </div>
</div>
</body>
</html>
