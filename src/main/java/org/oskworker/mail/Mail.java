package org.oskworker.mail;

import org.oskworker.mail.configure.Configure;
import org.oskworker.mail.entity.BasicEmail;
import org.oskworker.mail.entity.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    protected Mail() {
    }

    protected Mail(Session session, Configure configure) {
        this.configure = configure;
        this.session = session;
    }

    /**
     * 极简配置
     *
     * @param host
     * @param sender
     * @param password
     * @return
     */
    public static Mail configure(String host, String sender, String password) {
        Configure configure = new Configure(host, sender, password, 25, false);

        return configure(configure);
    }

    /**
     * 自动识别smtp主机
     * 支持：163，126，qq
     *
     * @param sender
     * @param password
     * @return
     */
    public static Mail configure(String sender, String password) {
//        腾讯QQ邮箱：smtp.qq.com  smtp/pop3开启：http://service.mail.qq.com/cgi-bin/help?subtype=1&&id=28&&no=166 （注意腾讯邮箱要单独设置安全码 点击查看说明）
//        网易163邮箱：smtp.163.net smtp/pop3开启：http://help.163.com/10/0312/13/61J0LI3200752CLQ.html（注意网易免费邮箱smtp密码要单独设置 点击查看说明）
//        新浪免费邮箱：smtp.sina.com  smtp/pop3开启：http://mail.sina.com.cn/help2/client01.html
//        腾讯企业邮箱：smtp.exmail.qq.com
//        雅虎免费邮箱：smtp.mail.yahoo.cn
//        网易126邮箱：smtp.126.com
//        搜狐免费邮箱：smtp.sohu.com
//        Gmail邮箱：smtp.gmail.com　（目前gmail在国内一般用不了）
        String host = "smtp.163.com";
        String suffix = sender.substring(sender.lastIndexOf("@"));
        if (suffix.equals("qq.com")) {
            host = "smtp.qq.com";
        } else if (suffix.equals("126.com")) {
            host = "smtp.126.com";
        } else if (suffix.equals("sina.com") || suffix.equals("sina.cn")) {
            host = "smtp.sina.com";
        }

        Configure configure = new Configure(host, sender, password, 25, false);
        return configure(configure);
    }

    /**
     * 从配置文件中读取
     *
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
     *
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
        return new Mail(session, configure);
    }

    /**
     * 极简创建邮件对象
     *
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

        try {
            MimeMessage message = new MimeMessage(session);
            message.setSubject(email.subject());
            message.setFrom(configure.getSender());
            // 纯文本
            if (email instanceof BasicEmail) {
                message.setText(email.content());
            }

            this.message = message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return this;
    }


    public void sendTo(List<String> mails) {

        for (int i = 0; i < mails.size(); i++) {
            try {
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mails.get(i)));
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
