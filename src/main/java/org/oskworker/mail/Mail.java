package org.oskworker.mail;

import org.oskworker.mail.configure.Configure;
import org.oskworker.mail.entity.Email;

import javax.mail.internet.MimeUtility;
import java.util.List;

/**
 * Created by full on 2018/5/6.
 */
public class Mail {

    public static Mail configure() {
        return new Mail();
    }

    public static Mail configure(Configure configure) {

        return new Mail();
    }

    public Mail sendTo(String... emils) {
        return this;
    }

    public Mail sendTo(List<String> emils) {
        return this;
    }


    public Mail make(Email email) {
        return this;
    }
}
