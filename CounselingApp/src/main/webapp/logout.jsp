<%@ page contentType="text/html;charset=UTF-8" %>
<%
    session.invalidate();
    response.sendRedirect("index.jsp");
%>
<!doctype html>
<html lang="en">
<head>
    <title>Log out</title>
</head>
<body>
</body>
</html>