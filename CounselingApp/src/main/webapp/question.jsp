<%@ page import="qu4lizz.ip.fitness.counselingapp.models.Advice" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="adviceService" class="qu4lizz.ip.fitness.counselingapp.services.AdviceService" scope="application"/>
<jsp:useBean id="advisor" class="qu4lizz.ip.fitness.counselingapp.models.Advisor" scope="session"/>

<%
    if (!advisor.isLogged())
        response.sendRedirect("index.jsp");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Fitness Counseling</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    Advice advice = adviceService.findById(id);
    adviceService.markAsRead(advice);
    session.setAttribute("email", advice.getUser().getMail());
%>

<body class="min-vh-100">
<%@include file="navbar.jsp" %>
<div class="container mt-4 d-flex flex-column justify-content-center align-items-center">
    <div class="mb-1 fs-2 fw-bold">
        Question
    </div>
    <div class="mb-1">
        Sent by: <strong><%=advice.getUser().getName() + " " + advice.getUser().getSurname()%>
    </strong>
    </div>
    <div class="mb-1">
        Email: <strong><%=advice.getUser().getMail()%>
    </strong>
    </div>
    <div class="mb-1">
        <% SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy. HH:mm"); %>
        At: <strong><%=dateFormat.format(advice.getTimestamp())%>
    </strong>
    </div>
    <div class="card w-25 text-wrap p-2 mb-4">
        <%=advice.getMessage()%>
    </div>

    <div class="mb-1 fs-2 fw-bold">
        Reply
    </div>
    <form method="post" action="Upload" class="w-100 d-flex flex-column align-items-center justify-content-center" enctype="multipart/form-data">
        <div class="form-floating w-50 mb-3">
            <textarea class="form-control" name="message" id="message" style="height: 150px" required></textarea>
            <label for="message">Message</label>
        </div>

        <label>Attach additional file</label>
        <input class="form-control w-50 mb-4" type="file" name="file" id="file" size="10">

        <button class="btn btn-lg btn-primary btn-block" type="submit" name="submit">Send</button>
    </form>
</div>
</body>
</html>
