package org.georg.web.impl.util;

import org.georg.web.impl.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;

@Component
public class MailUtil {

    @Value("${mail.smtp.server}")
    private String smtpServer;
    @Value("${mail.smtp.user}")
    private String smtpUser;
    @Value("${mail.smtp.password}")
    private String smtpPassword;
    @Value("${mail.smtp.port}")
    private String smtpPort;
    @Value("${mail.activation.from}")
    private String activationFrom;
    @Value("${mail.activation.subject}")
    private String activationSubject;

    java.util.Properties props = new java.util.Properties();

    public void sendActivationMail(User user, String code) {
        props.setProperty("mail.smtp.host", smtpServer);
        props.setProperty("mail.smtp.protocol", "smtps");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.ssl.enable", "true");
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.user", smtpUser);
        props.setProperty("mail.smtp.password", smtpPassword);

        Session session = Session.getInstance(props, null);
        MimeMessage message = new MimeMessage(session);

        // Create the email addresses involved
        try {
            InternetAddress from = new InternetAddress(activationFrom);
            message.setSubject(activationSubject);
            message.setFrom(from);
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getLogin()));

            // Create a multi-part to combine the parts
            Multipart multipart = new MimeMultipart("alternative");

            // Create your text message part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("some text to send");

            // Add the text part to the multipart
            multipart.addBodyPart(messageBodyPart);

            // Create the html part
            messageBodyPart = new MimeBodyPart();
            String htmlMessage = "Our html text";
            messageBodyPart.setContent(htmlMessage, "text/html");


            // Add html part to multi part
            multipart.addBodyPart(messageBodyPart);

            // Associate multi-part with message
            message.setContent(multipart);

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "username", "password");
            System.out.println("Transport: " + transport.toString());
            transport.sendMessage(message, message.getAllRecipients());


        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
