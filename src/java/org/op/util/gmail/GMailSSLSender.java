package org.op.util.gmail;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GMailSSLSender implements Serializable
{

    private final String gmailUsr,
            gmailPwd;

    public GMailSSLSender(String gmailUsr, String gmailPwd)
    {
        this.gmailUsr = gmailUsr;
        this.gmailPwd = gmailPwd;
    }

    public void send(String toAddress, String subject, String body) throws MessagingException
    {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new javax.mail.Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(gmailUsr, gmailPwd);
            }
        });

        try
        {
            Message message;
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(gmailUsr));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException e)
        {
            throw new RuntimeException(e);
        }
    }
}
