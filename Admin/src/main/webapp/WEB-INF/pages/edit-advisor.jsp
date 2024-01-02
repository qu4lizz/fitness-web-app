<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="advisorBean" type="qu4lizz.ip.fitness.admin.beans.AdvisorBean" scope="session"/>
<html>
<head>
    <title>Fitness Online Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body class="min-vh-100">
<%@include file="navbar.jsp" %>
<div class="card d-flex align-items-center justify-content-center min-vh-100">
    <form method="post" action="?action=edit-advisor&id=<%=advisorBean.getUser().getUsername()%>" class="w-25 d-flex flex-column align-items-center justify-content-center" enctype="multipart/form-data">
        <div class="form-group mb-3">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name" required value="<%=advisorBean.getUser().getName()%>">
        </div>
        <div class="form-group mb-3">
            <label for="surname">Surname</label>
            <input type="text" class="form-control" id="surname" name="surname" required value="<%=advisorBean.getUser().getSurname()%>">
        </div>
        <div class="form-group mb-3">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" name="username" required value="<%=advisorBean.getUser().getUsername()%>">
        </div>
        <div class="form-group mb-3">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" required value="<%=advisorBean.getUser().getPassword()%>">
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit" name="submit">Edit</button>

        <div class="text-danger fs-3"><%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : ""%></div>
    </form>
</div>
</body>
</html>
