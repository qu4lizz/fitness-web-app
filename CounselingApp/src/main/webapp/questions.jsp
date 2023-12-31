<%@ page import="qu4lizz.ip.fitness.counselingapp.models.Advice" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Comparator" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="adviceService" class="qu4lizz.ip.fitness.counselingapp.services.AdviceService" scope="application"/>
<jsp:useBean id="advisor" class="qu4lizz.ip.fitness.counselingapp.models.Advisor" scope="session"/>

<%
    if (!advisor.isLogged())
        response.sendRedirect("index.jsp");
%>

<html>
<head>
    <title>Fitness Counseling</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body class="min-vh-100">
<%@include file="navbar.jsp" %>
<div class="container mt-5">
    <div class="d-flex flex-row justify-content-between">
        <h2>Questions</h2>
        <form method="get">
            Filter:&nbsp;<input type="text" name="filter">
            <input type="submit" value="Search"/>
        </form>
    </div>
    <table id="example" class="table table-striped table-bordered w-100">
        <thead>
        <tr>
            <th scope="col">Time</th>
            <th scope="col">Question</th>
            <th scope="col">User name and surname</th>
            <th scope="col">User e-mail</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>

        <% SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
            for (Advice adviceQuestion : adviceService.findAllUnread().stream().sorted(Comparator.comparing(Advice::getTimestamp)).toList()) {
            String filter = request.getParameter("filter");
            if (filter == null || adviceQuestion.getMessage().toLowerCase().contains(filter)) {
        %>
        <tr>
            <td>
                <%=dateFormat.format(adviceQuestion.getTimestamp())%>
            </td>
            <td>
                <%=adviceQuestion.getMessage()%>
            </td>
            <td>
                <%=adviceQuestion.getUser().getName() + " " + adviceQuestion.getUser().getSurname()%>
            </td>
            <td>
                <%=adviceQuestion.getUser().getMail()%>
            </td>
            <td>
                <button type="button" class="btn btn-primary view-button"
                        onclick="location.href='question.jsp?id=<%=adviceQuestion.getId()%>'">View
                </button>
            </td>
        </tr>
        <% }
            } %>

        </tbody>
    </table>
</div>
</body>
</html>
