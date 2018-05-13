package test.nativr;

import org.junit.Test;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.NamingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * TODO 各测试用例，测试中文发送是否乱码。
 * Created by full on 2018/5/10.
 */
public class TestEmail {

    @Test
    public void sendMail() throws MessagingException, UnsupportedEncodingException {
        final String userName = "icngor@163.com"; // 发件人邮箱
        final String password = "aF88Km"; // 发件人密码
        String smtpHost = "smtp.163.com"; // 邮件服务器

        String to = "1192577322@qq.com"; // 收件人，多个收件人以半角逗号分隔
        String cc = "icngor@126.com"; // 抄送，多个抄送以半角逗号分隔
        String subject = "这是邮件的主题"; // 主题
        String body = "这是邮件的正文"; // 正文，可以用html格式的哟

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", smtpHost);
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(userName,"徐虹"));
        Address[] addresses = {new InternetAddress(to)};
        message.setRecipients(Message.RecipientType.TO, addresses);
        message.setRecipients(Message.RecipientType.BCC,cc);
        message.setText("好久不见了哈");
        message.setSubject("主题：好久不见");


        // 发送邮件
        message.saveChanges();
        Transport transport = session.getTransport();
        transport.connect();
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();


//        Transport.send(message);
    }

    String to = "1192577322@qq.com";

    String from = "icngor@163.com";
    final String username = "icngor@163.com";//change accordingly
    final String password = "aF88Km";//change accordingly

    String host = "smtp.163.com";

    @Test
    public void testSendSimpleMail() {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });


        session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Testing Subject");

            // Now set the actual message
            message.setText("Hello, this is sample for to check send email using JavaMailAPI ");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSendAttachmentMail() throws UnsupportedEncodingException {
        // Recipient's email ID needs to be mentioned.
//        String to = "1192577322@qq.com";
//
//        // Sender's email ID needs to be mentioned
//        String from = "icngor@163.com";
//
//        final String username = "icngor@163.com";//change accordingly
//        final String password = "";//change accordingly
//
//        // Assuming you are sending email through relay.jangosmtp.net
//        String host = "relay.jangosmtp.net";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Testing Subject");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("你好啊");

            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            String filename = "C:\\Users\\full\\Desktop\\协群组织结构.png";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(MimeUtility.encodeText("西区全.png"));
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    // 发送HTML
    @Test
    public void testSendHTMLMail() throws NamingException, UnsupportedEncodingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("中文标题");

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>HTML中文内容</h1>","text/html; charset=utf-8");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    public void sendHtmlWithImage() {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Testing Subject");

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<H1>你好</H1><img src=\"cid:image\">";
            messageBodyPart.setContent(htmlText, "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);

            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            String filename = "C:\\Users\\full\\Desktop\\协群组织结构.png";
            DataSource fds = new FileDataSource(filename);

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);

            // put everything together
            message.setContent(multipart);
            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testSystem() throws IOException {
        Properties properties = System.getProperties();
        properties.load(new FileInputStream(new File("I:\\Gitprepository\\java\\oskworker\\mail\\src\\test\\java\\test\\system.properties")));
        String property = properties.getProperty("db.name");
        System.out.println(property);
    }


    // 添加附件
}
