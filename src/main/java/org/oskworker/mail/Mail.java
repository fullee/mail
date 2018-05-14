package org.oskworker.mail;

import org.oskworker.mail.configure.Configure;
import org.oskworker.mail.entity.AttachmentEmail;
import org.oskworker.mail.entity.BasicEmail;
import org.oskworker.mail.entity.Email;
import org.oskworker.mail.entity.MiddleWareEmail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by full on 2018/5/6.
 */
public class Mail {

    private Configure configure;
    private Session session;
    private MimeMessage message;
    protected Mail() {}

    protected Mail(Session session, Configure configure) {
        this.configure = configure;
        this.session = session;
    }

    /**
     * 极简配置
     * @param host
     * @param sender
     * @param password
     * @return
     */
    public static Mail configure(String host,String sender,String password){
        Configure configure = new Configure(host, sender, password, 25, false);

        return configure(configure);
    }

    /**
     * 自动识别smtp主机
     * 支持：163，126，qq
     * @param sender
     * @param password
     * @return
     */
    public static Mail configure(String sender, String password) {

        String host = "smtp.163.com";

        Configure configure = new Configure(host, sender, password, 25, false);
        return configure(configure);
    }

    /**
     * 从配置文件中读取
     * @return
     */
    public static Mail configure() {

        try {
            Properties properties = System.getProperties();
            properties.load(new FileInputStream(new File("src/main/resources/mail.properties")));
            String host = properties.getProperty("smtp.host");
            String sender = properties.getProperty("sender");
            String password = properties.getProperty("password");

            Configure configure = new Configure(host, sender, password, 25, false);
            return configure(configure);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 传入配置对象
     * @param configure
     * @return this
     */
    public static Mail configure(final Configure configure) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", configure.getStmp());
        props.put("mail.smtp.port", configure.getPort());

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(configure.getSender(), configure.getPassword());
                    }
                });
        session.setDebug(configure.isDebug());
        return new Mail(session,configure);
    }

    /**
     * 极简创建邮件对象
     * @param subject
     * @param content
     * @return
     */
    public Mail make(String subject, String content) {
        BasicEmail basicEmail = new BasicEmail(subject, content);
        make(basicEmail);
        return this;
    }

    public Mail make(Email email) {
//        MimeMessage message = new MimeMessage(session);
//        try {
//            message.setFrom(configure.getSender());
//            message.setSubject(mail.subject());
//            message.setText(mail.content());
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
        try {
            MimeMessage message = new MimeMessage(session);
            message.setSubject(email.subject());
            message.setFrom(configure.getSender());
            // 纯文本
            if (email instanceof BasicEmail) {
                message.setText(email.content());
            }

            MimeMultipart mPart = new MimeMultipart();
            // HTML 带图片

            // 附件
            if (email instanceof AttachmentEmail) {
                if (!"".equals(email.content()) && email.content() != null) {
                    BodyPart bodyPart = new MimeBodyPart();
                    bodyPart.setText(email.content());
                    mPart.addBodyPart(bodyPart);
                }
                String[] attachment = email.attachment();
                for (int i = 0; i < attachment.length; i++) {
                    String filePath = attachment[i];
                    String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);

                    BodyPart bodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(filePath);
                    bodyPart.setDataHandler(new DataHandler(source));
                    bodyPart.setFileName(MimeUtility.encodeText(fileName));
                    mPart.addBodyPart(bodyPart);
                }
                message.setContent(mPart);
            }
            this.message = message;
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return this;
    }


    public void sendTo(List<String> mails) {

        for (int i = 0; i < mails.size(); i++) {
            try {
                message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mails.get(i)));
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendTo(String... mails) {
        List<String> mailList = Arrays.asList(mails);
        sendTo(mailList);
    }
}
