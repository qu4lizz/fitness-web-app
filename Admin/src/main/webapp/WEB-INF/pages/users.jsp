<%@ page import="qu4lizz.ip.fitness.admin.models.UserEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userBean" type="qu4lizz.ip.fitness.admin.beans.UserBean" scope="session"/>
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
        <h2>Users</h2>
        <form method="post" action="?action=new-user">
            <input type="submit" value="Add new user" class="btn btn-lg btn-primary btn-block"/>
        </form>
    </div>
    <table id="example" class="table table-striped table-bordered w-100">
        <thead>
        <tr>
            <th scope="col" class="col-1">Image</th>
            <th scope="col" class="col-2">Name</th>
            <th scope="col" class="col-2">Surname</th>
            <th scope="col" class="col-3">Email</th>
            <th scope="col" class="col-2">Username</th>
            <th scope="col" class="col-1">City</th>
            <th scope="col" class="col-1">Actions</th>
        </tr>
        </thead>
        <tbody>

        <%
            for (UserEntity user : userBean.findAll()) {
                byte[] imageData = user.getImage();
                String base64Image;

                if (imageData != null && imageData.length > 0) {
                    String format = userBean.getImageFormat(imageData);
                    base64Image = "data:image/" + format + ";base64," + java.util.Base64.getEncoder().encodeToString(imageData);
                } else {
                    base64Image = "https://w7.pngwing.com/pngs/141/425/png-transparent-user-profile-computer-icons-avatar-profile-s-free-angle-rectangle-profile-cliparts-free-thumbnail.png";
                }
        %>
        <tr>
            <td>
                <img src="<%=base64Image%>" alt="User Image" style="max-height: 64px">
            </td>
            <td>
                <%=user.getName()%>
            </td>
            <td>
                <%=user.getSurname()%>
            </td>
            <td>
                <%=user.getMail()%>
            </td>
            <td>
                <%=user.getUsername()%>
            </td>
            <td>
                <%=user.getCity()%>
            </td>
            <td>
                <div class="d-flex flex-row mb-3 justify-content-evenly align-items-center">
                    <div>
                        <button type="button" class="btn"
                                onclick="location.href='?action=edit-user&id=<%=user.getId()%>'">
                            <i class="bi bi-pen" style="font-size: 24px"></i>
                        </button>
                    </div>
                    <div>
                        <button type="button" class="btn"
                                onclick="location.href='?action=delete-user&id=<%=user.getId()%>'">
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