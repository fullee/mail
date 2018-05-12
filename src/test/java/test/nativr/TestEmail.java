package test.nativr;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by full on 2018/5/10.
 */
public class TestEmail {

    @Test
    public void sendEmail() throws Exception {
        String userName = "icngor@163.com"; // 发件人邮箱
        String password = "aF88Km"; // 发件人密码
        String smtpHost = "smtp.163.com"; // 邮件服务器

        String to = "1192577322@qq.com"; // 收件人，多个收件人以半角逗号分隔
        String cc = "icngor@126.com"; // 抄送，多个抄送以半角逗号分隔
        String subject = "这是邮件的主题"; // 主题
        String body = "这是邮件的正文"; // 正文，可以用html格式的哟
        List<String> attachments = Arrays.asList("I:\\Temp\\TxGameDownload\\MobileGamePCShared\\apk.conf"); // 附件的路径，多个附件也不怕

        Email email = Email.entity(smtpHost, userName, password, to, cc, subject, body, null);

        email.send(); // 发送！
    }
}
