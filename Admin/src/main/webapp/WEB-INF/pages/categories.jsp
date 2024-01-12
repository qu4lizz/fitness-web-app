<%@ page import="qu4lizz.ip.fitness.admin.models.CategoryEntity" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="categoryBean" type="qu4lizz.ip.fitness.admin.beans.CategoryBean" scope="session"/>
<html>
<head>
    <title>Fitness Online Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body class="min-vh-100">

<%@include file="navbar.jsp" %>
<div class="container mt-5">
    <p style="color: red"><%=session.getAttribute("notification") != null ? session.getAttribute("notification") : ""%></p>
    <div class="d-flex flex-row justify-content-between">
        <h2>Categories</h2>
        <form method="post" action="?action=new-category">
            <input type="submit" value="Add new category" class="btn btn-lg btn-primary btn-block"/>
        </form>
    </div>
    <table id="example" class="table table-striped table-bordered w-100">
        <thead>
        <tr>
            <th scope="col" class="col-8">Name</th>
            <th scope="col" class="col-4">Actions</th>
        </tr>
        </thead>
        <tbody>

        <%
            for (CategoryEntity category : categoryBean.findAll()) {
        %>
        <tr>
            <td class="fs-3">
                <%=category.getName()%>
            </td>
            <td>
                <div class="d-flex flex-row mb-3  justify-content-evenly align-items-center">
                    <div>
                        <button type="button" class="btn"
                                onclick="location.href='?action=details-category&id=<%=category.getId()%>'">
                            <i class="bi bi-layout-text-sidebar-reverse" style="font-size: 24px"></i>
                        </button>
                    </div>
                    <div>
                        <button type="button" class="btn"
                                onclick="location.href='?action=edit-category&id=<%=category.getId()%>'">
                            <i class="bi bi-pen" style="font-size: 24px"></i>
                        </button>
                    </div>
                    <div>
                        <button type="button" class="btn"
                                onclick="location.href='?action=delete-category&id=<%=category.getId()%>'">
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