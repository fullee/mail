import org.junit.Test;
import org.oskworker.mail.EM;
import org.oskworker.mail.Mail;

/**
 * Created by full on 2018/5/6.
 */
public class TestMailApi {

    /**
     * 极简的方式发送邮件
     */
    @Test
    public void minimalismSendMail() {
        Mail.configure("smtp.163.com","icngor@163.com","aF88Km").make("标题", "正文内容").sendTo("icngor@126.com","1192577322@qq.com");
    }

    /**
     * 自动识别smtp服务器
     */
    @Test
    public void smtpSendMail() {
        Mail.configure("icngor@163.com", "aF88Km").make("母亲节", "五月13号，今天是母亲节，妈，节日快乐").sendTo("1192577322@qq.com");
    }

    /**
     * 测试读取配置文件
     */
    @Test
    public void readConfigFile() {
        EM.configure().make("母亲节", "五月13号，今天是母亲节，妈，节日快乐").sendTo("1192577322@qq.com");
    }

}
