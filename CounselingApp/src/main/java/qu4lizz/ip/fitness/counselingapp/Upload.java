package qu4lizz.ip.fitness.counselingapp;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import qu4lizz.ip.fitness.counselingapp.services.MailService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet("/Upload")
@MultipartConfig
public class Upload extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String message = request.getParameter("message");
        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
        }

        MailService.sendMail((String)session.getAttribute("email"), "Response from fitness counselor", message, fileName);


        response.sendRedirect("questions.jsp");
    }
}
