import org.junit.Test;
import org.oskworker.mail.Mail;
import org.oskworker.mail.configure.Configure;
import org.oskworker.mail.entity.BasicEmail;

import javax.mail.internet.AddressException;

/**
 * Created by full on 2018/5/6.
 */
public class TestMailApi {

    @Test
    public void testMailConfigure() throws AddressException {

//        // 构建配置类
//        Configure configure = Configure.builder()
//                .password("aF88Km")
//                .sender("icngor@163.com")
//                .stmp("smtp.163.com")
//                .build();
//
//        // 构建邮件
//        BasicEmail basicEmail = BasicEmail.builder()
//                .subject("5月13号")
//                .content("今天是母亲节")
//                .build();
//
//        String[] mails = {"1192577322@qq.com", "icngor@126.com"};
//        Mail.configure(configure).make(basicEmail).sendTo(mails);

        // 配置文件
//        Mail.make("标题", "正文内容").sendTo("icngor@126.com");

        // 推测smtp服务器
//        Mail.configure("icngor@163.com","xxx").make("标题", "正文内容").sendTo("icngor@126.com");

        // 填写准确的smtp
        Mail.configure("smtp.qq.com","icngor@163.com","aF88Km").make("标题", "正文内容").sendTo("icngor@126.com");

    }


    /**
     * 极简的方式发送邮件
     */
    @Test
    public void minimalismSendMail() {
        Mail.configure("smtp.163.com","icngor@163.com","aF88Km").make("标题", "正文内容").sendTo("icngor@126.com","1192577322@qq.com");
    }


}
