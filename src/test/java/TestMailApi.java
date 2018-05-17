import org.junit.Before;
import org.junit.Test;
import org.oskworker.mail.EM;
import org.oskworker.mail.Mail;

/**
 * Created by full on 2018/5/6.
 */
public class TestMailApi {

    private String smtp;
    private String sender;
    private String password;

    private String subject = "标题：母亲节！";
    private String content = "内容：5.13节日快乐";

    @Before
    public void init() {
        smtp = "smtp.163.com";
        sender = "icngor@163.com";
        password = "****";
    }

    /**
     * 极简的方式发送邮件
     */
    @Test
    public void minimalismSendMail() {
        Mail.configure(smtp,sender,password).make(subject, content).sendTo("icngor@126.com","1192577322@qq.com");
    }

    /**
     * 自动识别smtp服务器
     */
    @Test
    public void smtpSendMail() {
        Mail.configure(sender, password).make("母亲节", "五月13号，今天是母亲节，妈，节日快乐").sendTo("1192577322@qq.com");
    }

    /**
     * 测试读取配置文件
     */
    @Test
    public void readConfigFile() {
        EM.configure().make("母亲节", "五月13号，今天是母亲节，妈，节日快乐").sendTo("1192577322@qq.com");
    }

}
