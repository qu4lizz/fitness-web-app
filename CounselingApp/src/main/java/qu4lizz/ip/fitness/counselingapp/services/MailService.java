package qu4lizz.ip.fitness.counselingapp.services;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.servlet.http.Part;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MailService {
    private static final String MAIL_CONFIG_PATH = "config.properties";
    private static String username;
    private static String password;
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileReader(MailService.class.getResource(MAIL_CONFIG_PATH).getFile()));

            String mailConfigPath = properties.getProperty("mail_config_path");
            properties.load(new FileInputStream(MailService.class.getResource(mailConfigPath).getFile()));
            String mailCredentialPath = properties.getProperty("env_path");
            properties.load(new FileInputStream(MailService.class.getResource(mailCredentialPath).getFile()));

            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void sendMail(String to, String title, String body, String filePart) throws IOException {
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, "Fitness Counseling"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(title);

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(body);

            if (filePart != null) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                DataSource source = new FileDataSource(filePart);
                attachmentPart.setDataHandler(new DataHandler(source));
                attachmentPart.setFileName(filePart);
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);
                message.setContent(multipart);
            } else {
                // No attachment, just set the text part
                message.setText(body);
            }

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
