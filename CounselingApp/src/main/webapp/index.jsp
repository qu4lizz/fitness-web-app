<%@ page import="qu4lizz.ip.fitness.counselingapp.services.AdvisorService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="authService" class="qu4lizz.ip.fitness.counselingapp.services.AdvisorService" scope="application"/>
<jsp:useBean id="advisor" class="qu4lizz.ip.fitness.counselingapp.models.Advisor" scope="session"/>
<jsp:setProperty property="username" name="advisor" param="username"/>
<jsp:setProperty property="password" name="advisor" param="password"/>
<!DOCTYPE html>
<%
    if (request.getParameter("submit") != null) {
        var res = authService.checkLogin(request.getParameter("username"), request.getParameter("password"));

        if (res != null) {
            advisor.setName(res.getName());
            advisor.setSurname(res.getSurname());
            advisor.setUsername(res.getUsername());
            advisor.setPassword(res.getPassword());
            advisor.setLogged(true);
            session.setAttribute("notification", "");
            response.sendRedirect("questions.jsp");
        }
        else {
            session.setAttribute("notification", "Login failed! Try again.");
        }
    } else {
        session.setAttribute("notification", "");
    }
%>
<html>
<head>
    <title>Fitness Counseling</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body class="min-vh-100">
<div class="card d-flex align-items-center justify-content-center min-vh-100">
    <form method="post" action="index.jsp" class="w-25 d-flex flex-column align-items-center justify-content-center">
        <div class="form-group mb-3">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" name="username">
        </div>
        <div class="form-group mb-3">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit" name="submit">Sign in</button>

        <div class="text-danger fs-3"><%=session.getAttribute("notification").toString()%></div>
    </form>
</div>
</body>
</html>