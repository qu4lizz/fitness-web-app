<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="qu4lizz.ip.fitness.admin.models.LogEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="logBean" type="qu4lizz.ip.fitness.admin.beans.LogBean" scope="session"/>
<html>
<head>
    <title>Fitness Online Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body class="min-vh-100">
<%@include file="navbar.jsp" %>
<div class="container mt-5">
    <h2>Logs</h2>
    <table id="example" class="table table-striped table-bordered w-100">
        <thead>
        <tr>
            <th scope="col" class="col-2">Time</th>
            <th scope="col" class="col-10">Log</th>
        </tr>
        </thead>
        <tbody>

        <% SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
            for(LogEntity log : logBean.findAllAndSort()) {
        %>
        <tr>
            <td>
                <%=dateFormat.format(log.getDatetime())%>
            </td>
            <td>
                <%=log.getText()%>
            </td>
        </tr>
        <% } %>

        </tbody>
    </table>
</div>
</body>
</html>

