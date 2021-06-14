package ua.kharkiv.rsyrtsov.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

public class Mailer {
    public static void send(String to,Long userId, String recordId) {
        final String username = "romansyrtsov1@gmail.com";
        final String password = "bgrbgr123";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("romansyrtsov1@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject("Beauty service");
            message.setContent("<h2>Leave a review</h2>" +
                            "<h3>Please click to leave a review\n </h3> "+
                            " <a href=http://localhost:8080/review?userId="+ userId +"&recordId="+recordId+">Click</a>",
                    "text/html");

            Calendar c = Calendar.getInstance();
            Date now = new Date();
            c.set(Calendar.HOUR_OF_DAY,12);
            c.set(Calendar.MINUTE,00);
            c.set(Calendar.SECOND,00);
            c.set(Calendar.DAY_OF_MONTH,now.getDay());
            c.add(Calendar.DAY_OF_MONTH,1);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        Transport.send(message);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            },c.getTime());


            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
