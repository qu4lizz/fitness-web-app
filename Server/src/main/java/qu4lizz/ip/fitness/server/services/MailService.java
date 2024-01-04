package qu4lizz.ip.fitness.server.services;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailService {
    private static final String MAIL_CONFIG_PATH = "config.properties";
    private static String username;
    private static String password;
    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileReader(new ClassPathResource(MAIL_CONFIG_PATH).getFile()));

            String mailConfigPath = properties.getProperty("mail_config_path");
            properties.load(new FileInputStream(new ClassPathResource(mailConfigPath).getFile()));
            String mailCredentialPath = properties.getProperty("env_path");
            properties.load(new FileInputStream(new ClassPathResource(mailCredentialPath).getFile()));

            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void sendMail(String to, String title, String body) {
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username,"Fitness Online"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(title);
            message.setText(body);
            message.setContent(body, "text/html");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}

