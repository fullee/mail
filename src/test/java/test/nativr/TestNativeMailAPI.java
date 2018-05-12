package test.nativr;

import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by full on 2018/5/10.
 */
public class TestNativeMailAPI {

    @Test
    public void testSession() {

        Properties properties = System.getProperties();

        Session session = Session.getDefaultInstance(properties);

        try {
            Transport smtp = session.getTransport("smtp");
            smtp.connect();

                smtp.sendMessage(new MimeMessage(session),new Address[]{new InternetAddress("icngor@163.com")});

        } catch (NoSuchProviderException e1) {
            e1.printStackTrace();
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }

        PasswordAuthentication pa = new PasswordAuthentication("icngor@163.com", "liwenliangWY2018");
    }
}
