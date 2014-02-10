package org.georg.web.impl.util;

import org.georg.web.impl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Properties;

@Component
public class MailUtil {

    Properties props = new Properties();
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

    @Autowired
    private MessageSource messageSource;

    public void sendActivationMail(User user) {
        props.setProperty("mail.smtp.host", smtpServer);
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUser, smtpPassword);
            }
        });

        // Create the email addresses involved
        try {
            String messageBody = messageSource.getMessage("email.activation-required.body", new Object[]{user.getFirstName() + " " + user.getLastName(), user.getActivationCode()}, Locale.getDefault());
            String messageSubject = messageSource.getMessage("email.activation-required.subject", null, Locale.getDefault());

            MimeMessage message = new MimeMessage(session);
            InternetAddress from = new InternetAddress(activationFrom);
            message.setHeader("Content-Type", "text/html; charset=UTF-8");

            message.setSubject(MimeUtility.encodeText(messageSubject, "UTF-8", "Q"));
            message.setFrom(from);
            Multipart multiP = new MimeMultipart();

            InternetHeaders ihead = new InternetHeaders();
            ihead.addHeader("Content-Type", "text/html; charset=UTF-8");
            ihead.addHeader("Content-Transfer-Encoding", "base64");

            try (ByteArrayOutputStream body = new ByteArrayOutputStream();
                 OutputStream bodyOut = MimeUtility.encode(body, "base64");) {

                bodyOut.write(messageBody.getBytes("UTF-8"));
                bodyOut.flush();

                MimeBodyPart mbp = new MimeBodyPart(ihead, body.toByteArray());
                mbp.setDisposition(Part.INLINE);
                multiP.addBodyPart(mbp);

                message.setContent(multiP, "text/html");
            } catch (IOException e) {
                e.printStackTrace();
            }


            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getLogin()));
            message.writeTo(System.out);
            Transport.send(message);
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
