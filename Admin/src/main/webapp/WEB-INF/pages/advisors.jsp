<%@ page import="qu4lizz.ip.fitness.admin.models.AdvisorEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="advisorBean" type="qu4lizz.ip.fitness.admin.beans.AdvisorBean" scope="session"/>
<html>
<head>
    <title>Fitness Online Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body class="min-vh-100">

<%@include file="navbar.jsp" %>
<div class="container mt-5">
    <div class="d-flex flex-row justify-content-between">
        <h2>Advisors</h2>
        <form method="post" action="?action=new-advisor">
            <input type="submit" value="Add new advisor" class="btn btn-lg btn-primary btn-block"/>
        </form>
    </div>
    <table id="example" class="table table-striped table-bordered w-100">
        <thead>
        <tr>
            <th scope="col" class="col-3">Name</th>
            <th scope="col" class="col-3">Surname</th>
            <th scope="col" class="col-2">Username</th>
            <th scope="col" class="col-2">Actions</th>
        </tr>
        </thead>
        <tbody>

        <%
            for (AdvisorEntity user : advisorBean.findAll()) {
        %>
        <tr>
            <td>
                <%=user.getName()%>
            </td>
            <td>
                <%=user.getSurname()%>
            </td>
            <td>
                <%=user.getUsername()%>
            </td>
            <td>
                <div class="d-flex flex-row mb-3  justify-content-evenly align-items-center">
                    <div>
                        <button type="button" class="btn"
                                onclick="location.href='?action=edit-advisor&id=<%=user.getUsername()%>'">
                            <i class="bi bi-pen" style="font-size: 24px"></i>
                        </button>
                    </div>
                    <div>
                        <button type="button" class="btn"
                                onclick="location.href='?action=delete-advisor&id=<%=user.getUsername()%>'">
                            <i class="bi bi-trash" style="font-size: 24px"></i>
                        </button>
                    </div>
                </div>
            </td>
        </tr>
        <% } %>

        </tbody>
    </table>
</div>
</body>
</html>