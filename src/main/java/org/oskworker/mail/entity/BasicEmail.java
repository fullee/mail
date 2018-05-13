package org.oskworker.mail.entity;

import lombok.Builder;

import javax.mail.*;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;

/**
 * Created by full on 2018/5/6.
 */
@Builder
public class BasicEmail implements Email {

    private String subject;
    private String content;
//    private String[] recipients;


    public BasicEmail(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public String subject() {
        return subject;
    }

    public String content() {
        return content;
    }
}
