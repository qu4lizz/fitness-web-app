<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userBean" type="qu4lizz.ip.fitness.admin.beans.UserBean" scope="session"/>
<html>
<head>
    <title>Fitness Online Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body class="min-vh-100">
<%@include file="navbar.jsp" %>
<div class="card d-flex align-items-center justify-content-center min-vh-100">
    <form method="post" action="?action=edit-user&id=<%=userBean.getUser().getId()%>" class="w-25 d-flex flex-column align-items-center justify-content-center" enctype="multipart/form-data">
        <div class="form-group mb-3">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name" required value="<%=userBean.getUser().getName()%>">
        </div>
        <div class="form-group mb-3">
            <label for="surname">Surname</label>
            <input type="text" class="form-control" id="surname" name="surname" required value="<%=userBean.getUser().getSurname()%>">
        </div>
        <div class="form-group mb-3">
            <label for="city">City</label>
            <input type="text" class="form-control" id="city" name="city" required value="<%=userBean.getUser().getCity()%>">
        </div>
        <div class="form-group mb-3">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" name="username" required value="<%=userBean.getUser().getUsername()%>">
        </div>
        <div class="form-group mb-3">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" required value="<%=userBean.getUser().getPassword()%>">
        </div>
        <div class="form-group mb-3">
            <label for="mail">Email</label>
            <input type="email" class="form-control" id="mail" name="mail" required value="<%=userBean.getUser().getMail()%>">
        </div>
        <div class="form-group mb-3">
            <label for="currentImage">Current Image</label>
            <% byte[] imageData = userBean.getUser().getImage();
                if (imageData != null && imageData.length > 0) { %>
                    <img id="currentImage" src="data:image/<%=userBean.getImageFormat(imageData)%>;base64, <%=Base64.getEncoder().encodeToString(imageData)%>" alt="Current Image" width="100" height="100">
                <% } else { %>
                    <span>No image available</span>
                <% } %>
        </div>
        <div class="form-group mb-3">
            <label for="newImage">New Image</label>
            <input type="file" class="form-control" id="newImage" name="newImage">
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit" name="submit">Edit</button>

        <div class="text-danger fs-3"><%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : ""%></div>
    </form>
</div>
</body>
</html>
