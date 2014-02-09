package org.georg.web.impl.util;

import org.georg.web.impl.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
        //props.setProperty("mail.smtp.protocol", "smtps");
        props.setProperty("mail.smtp.starttls.enable", "true");
        //props.setProperty("mail.smtp.ssl.enable", "true");
        props.setProperty("mail.smtp.port", smtpPort);
        //props.setProperty("mail.smtp.user", smtpUser);
        //props.setProperty("mail.smtp.password", smtpPassword);
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUser, smtpPassword);
            }
        });

        // Create the email addresses involved
        try {

            MimeMessage message = new MimeMessage(session);
            InternetAddress from = new InternetAddress(activationFrom);
            message.setHeader("Content-Type", "text/html; charset=UTF-8");
/*
            try (ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                 OutputStream out = MimeUtility.encode(baos2, "base64");) {

                out.write(activationSubject.getBytes("UTF-8"));
                out.flush();
                message.setSubject(baos2.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            message.setSubject(MimeUtility.encodeText(activationSubject, "UTF-8", "Q"));
            message.setFrom(from);
            Multipart multiP = new MimeMultipart();

            InternetHeaders ihead = new InternetHeaders();
            ihead.addHeader("Content-Type", "text/html; charset=UTF-8");
            ihead.addHeader("Content-Transfer-Encoding", "base64");

            String messageBody = "Пожалуйста нажмите на ссылку для активации Вашего аккаунта :" +
                    " <a href=\"http://www.georg.org.ua:8080/activate_user/" + code + "\"> ссылка </a>";

            try (ByteArrayOutputStream body = new ByteArrayOutputStream();
                 OutputStream bodyOut = MimeUtility.encode(body, "base64");) {

                bodyOut.write(messageBody.getBytes("UTF-8"));//str - тип String
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
