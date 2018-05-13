package org.oskworker.mail;

import org.oskworker.mail.configure.Configure;
import org.oskworker.mail.entity.BasicEmail;
import org.oskworker.mail.entity.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
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
     * 从配置文件中读取
     * @return
     */
    public static Mail configure() {

        return new Mail();
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

    public Mail make(Email mail) {
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(configure.getSender());
            message.setSubject(mail.subject());
            message.setText(mail.content());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        this.message = message;
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
