import org.junit.Test;
import org.oskworker.mail.EM;
import org.oskworker.mail.Mail;
import org.oskworker.mail.configure.Configure;
import org.oskworker.mail.entity.BasicEmail;

/**
 * Created by full on 2018/5/6.
 */
public class TestMail {

    @Test
    public void testMailConfigure() {

        Mail.configure().sendTo("1@qq.com","1@qq.com");

//        EM.configure(new Configure()).make(new BasicEmail()).sendTo(List<String> mails);
    }
}
