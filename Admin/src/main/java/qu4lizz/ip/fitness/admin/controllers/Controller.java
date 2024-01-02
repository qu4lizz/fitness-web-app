package qu4lizz.ip.fitness.admin.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import qu4lizz.ip.fitness.admin.beans.*;
import qu4lizz.ip.fitness.admin.models.*;

import java.io.*;

@WebServlet(name = "controller", value = "/controller")
@MultipartConfig
public class Controller extends HttpServlet {
    private static final String SIGN_IN = "/WEB-INF/pages/index.jsp";
    private static final String HOME = "/WEB-INF/pages/home.jsp";
    private static final String USERS = "/WEB-INF/pages/users.jsp";
    private static final String NEW_USER = "/WEB-INF/pages/new-user.jsp";
    private static final String EDIT_USER = "/WEB-INF/pages/edit-user.jsp";
    private static final String ADVISORS = "/WEB-INF/pages/advisors.jsp";
    private static final String NEW_ADVISOR = "/WEB-INF/pages/new-advisor.jsp";
    private static final String EDIT_ADVISOR = "/WEB-INF/pages/edit-advisor.jsp";
    private static final String CATEGORIES = "/WEB-INF/pages/categories.jsp";
    private static final String NEW_CATEGORY = "/WEB-INF/pages/new-category.jsp";
    private static final String EDIT_CATEGORY = "/WEB-INF/pages/edit-category.jsp";
    private static final String DETAILS_CATEGORY = "/WEB-INF/pages/details-category.jsp";
    private static final String NEW_ATTRIBUTE = "/WEB-INF/pages/new-attribute.jsp";
    private static final String EDIT_ATTRIBUTE = "/WEB-INF/pages/edit-attribute.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String address = SIGN_IN;
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        session.setAttribute("notification", "");

        if (action == null || action.isEmpty()) {
            address = SIGN_IN;
        } else if (action.equals("logout")) {
            session.invalidate();
        } else if (action.equals("index")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            AdminBean adminBean = new AdminBean();
            session.setAttribute("adminBean", adminBean);
            AdminEntity admin = adminBean.checkLogin(username, password);
            if (admin != null) {
                session.setAttribute("adminBean", adminBean);
                session.setAttribute("logBean", new LogBean());
                session.setAttribute("userBean", new UserBean());
                session.setAttribute("advisorBean", new AdvisorBean());
                session.setAttribute("categoryBean", new CategoryBean());
                address = HOME;
            } else {
                session.setAttribute("notification", "Invalid credentials.");
            }
        } else {
            AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
            UserBean userBean = (UserBean) session.getAttribute("userBean");
            AdvisorBean advisorBean = (AdvisorBean) session.getAttribute("advisorBean");
            CategoryBean categoryBean = (CategoryBean) session.getAttribute("categoryBean");

            if (adminBean == null || !adminBean.isLogged()) {
                address = SIGN_IN;
            } else {
                switch (action) {
                    case "home" -> address = HOME;
                    case "users" -> address = USERS;
                    case "new-user" -> {
                        address = NEW_USER;
                        if (request.getParameter("submit") != null) {
                            String name = request.getParameter("name");
                            String surname = request.getParameter("surname");
                            String username = request.getParameter("username");
                            String password = request.getParameter("password");
                            String mail = request.getParameter("mail");
                            String city = request.getParameter("city");

                            if (name.isBlank() || surname.isBlank() || username.isBlank() || password.isBlank() ||
                                mail.isBlank() || city.isBlank()) {
                                session.setAttribute("notification", "All fields except image are required");
                            }
                            else {
                                Part filePart = request.getPart("image");
                                byte[] imageBytes = null;

                                if (filePart != null && filePart.getSize() > 0) {
                                    InputStream is = filePart.getInputStream();
                                    imageBytes = is.readAllBytes();
                                }

                                UserEntity newUser = new UserEntity(0, name, surname, username, password, mail, city, imageBytes, false);

                                userBean.create(newUser);

                                address = USERS;
                            }
                        }
                    }
                    case "edit-user" -> {
                        address = EDIT_USER;
                        int id = Integer.parseInt(request.getParameter("id"));
                        UserEntity user = userBean.findById(id);
                        userBean.setUser(user);

                        if (request.getParameter("submit") != null) {
                            String name = request.getParameter("name");
                            String surname = request.getParameter("surname");
                            String username = request.getParameter("username");
                            String password = request.getParameter("password");
                            String mail = request.getParameter("mail");
                            String city = request.getParameter("city");

                            if (name.isBlank() || surname.isBlank() || username.isBlank() || password.isBlank() ||
                                    mail.isBlank() || city.isBlank()) {
                                session.setAttribute("notification", "All fields except image are required");
                            }
                            else {
                                Part filePart = request.getPart("newImage");
                                byte[] imageBytes;

                                if (filePart != null && filePart.getSize() > 0) {
                                    InputStream is = filePart.getInputStream();
                                    imageBytes = is.readAllBytes();
                                } else {
                                    imageBytes = user.getImage();
                                }

                                UserEntity newUser = new UserEntity(user.getId(), name, surname, username, password, mail, city, imageBytes, user.getVerified());

                                userBean.update(newUser);

                                address = USERS;
                            }
                        }
                    }
                    case "delete-user" -> {
                        address = USERS;
                        int id = Integer.parseInt(request.getParameter("id"));
                        userBean.delete(id);
                    }
                    case "advisors" -> address = ADVISORS;
                    case "new-advisor" -> {
                        address = NEW_ADVISOR;
                        if (request.getParameter("submit") != null) {
                            String name = request.getParameter("name");
                            String surname = request.getParameter("surname");
                            String username = request.getParameter("username");
                            String password = request.getParameter("password");

                            if (name.isBlank() || surname.isBlank() || username.isBlank() || password.isBlank()) {
                                session.setAttribute("notification", "All fields are required");
                            } else {
                                AdvisorEntity advisorEntity = new AdvisorEntity(name, surname, username, password);

                                advisorBean.create(advisorEntity);

                                address = ADVISORS;
                            }
                        }
                    }
                    case "edit-advisor" -> {
                        address = EDIT_ADVISOR;
                        String usernameId = request.getParameter("id");
                        AdvisorEntity advisor = advisorBean.findById(usernameId);
                        advisorBean.setUser(advisor);

                        if (request.getParameter("submit") != null) {
                            String name = request.getParameter("name");
                            String surname = request.getParameter("surname");
                            String username = request.getParameter("username");
                            String password = request.getParameter("password");

                            if (name.isBlank() || surname.isBlank() || username.isBlank() || password.isBlank()) {
                                session.setAttribute("notification", "All fields are required");
                            } else {
                                AdvisorEntity advisorEntity = new AdvisorEntity(name, surname, username, password);

                                advisorBean.update(advisorEntity);

                                address = ADVISORS;
                            }
                        }
                    }
                    case "delete-advisor" -> {
                        address = ADVISORS;
                        String username = request.getParameter("id");
                        advisorBean.delete(username);
                    }
                    case "categories" -> address = CATEGORIES;
                    case "new-category" -> {
                        address = NEW_CATEGORY;

                        if (request.getParameter("submit") != null) {
                            String name = request.getParameter("name");

                            if (name.isBlank()) {
                                session.setAttribute("notification", "All fields are required");
                            } else {
                                CategoryEntity entity = new CategoryEntity(0, name);

                                categoryBean.create(entity);

                                address = CATEGORIES;
                            }
                        }
                    }
                    case "edit-category" -> {
                        address = EDIT_CATEGORY;
                        int id = Integer.parseInt(request.getParameter("id"));
                        CategoryEntity entity = categoryBean.findById(id);
                        categoryBean.setCategory(entity);

                        if (request.getParameter("submit") != null) {
                            String name = request.getParameter("name");

                            if (name.isBlank()) {
                                session.setAttribute("notification", "All fields are required");
                            } else {
                                CategoryEntity categoryEntity = new CategoryEntity(entity.getId(), name);

                                categoryBean.update(categoryEntity);

                                address = CATEGORIES;
                            }
                        }
                    }
                    case "delete-category" -> {
                        address = CATEGORIES;
                        int id = Integer.parseInt(request.getParameter("id"));
                        categoryBean.delete(id);
                    }
                    case "details-category" -> {
                        address = DETAILS_CATEGORY;
                        int id = Integer.parseInt(request.getParameter("id"));
                        var entity = categoryBean.findById(id);

                        categoryBean.setCategory(entity);
                    }
                    case "new-attribute" -> {
                        address = NEW_ATTRIBUTE;

                        if (request.getParameter("submit") != null) {
                            String name = request.getParameter("name");

                            if (name.isBlank()) {
                                session.setAttribute("notification", "All fields are required");
                            } else {
                                AttributeEntity entity = new AttributeEntity(0, name, categoryBean.getCategory().getId());

                                categoryBean.createAttribute(entity);

                                address = DETAILS_CATEGORY;
                            }
                        }

                        var cat = categoryBean.findById(categoryBean.getCategory().getId());

                        categoryBean.setCategory(cat);
                    }
                    case "edit-attribute" -> {
                        address = EDIT_ATTRIBUTE;
                        int id = Integer.parseInt(request.getParameter("id"));
                        var entity = categoryBean.getAttributeById(id);
                        categoryBean.setAttribute(entity);

                        if (request.getParameter("submit") != null) {
                            String name = request.getParameter("name");

                            if (name.isBlank()) {
                                session.setAttribute("notification", "All fields are required");
                            } else {
                                AttributeEntity attribute = new AttributeEntity(entity.getId(), name, entity.getIdCategory());

                                categoryBean.updateAttribute(attribute);

                                address = DETAILS_CATEGORY;
                            }
                        }

                        var cat = categoryBean.findById(categoryBean.getCategory().getId());

                        categoryBean.setCategory(cat);
                    }
                    case "delete-attribute" -> {
                        address = DETAILS_CATEGORY;
                        int id = Integer.parseInt(request.getParameter("id"));
                        categoryBean.deleteAttribute(id);

                        var cat = categoryBean.findById(categoryBean.getCategory().getId());

                        categoryBean.setCategory(cat);
                    }
                    default -> address = HOME;
                }
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
