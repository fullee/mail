package org.oskworker.mail.entity;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.InputStream;

/**
 * Created by full on 2018/5/13.
 */
public class MiddleWareEmail extends MimeMessage implements Email {

    public MiddleWareEmail(Session session) {
        super(session);
    }

    public MiddleWareEmail(Session session, InputStream is) throws MessagingException {
        super(session, is);
    }

    public MiddleWareEmail(MimeMessage source) throws MessagingException {
        super(source);
    }

    protected MiddleWareEmail(Folder folder, int msgnum) {
        super(folder, msgnum);
    }

    protected MiddleWareEmail(Folder folder, InputStream is, int msgnum) throws MessagingException {
        super(folder, is, msgnum);
    }

    protected MiddleWareEmail(Folder folder, InternetHeaders headers, byte[] content, int msgnum) throws MessagingException {
        super(folder, headers, content, msgnum);
    }

    public MiddleWareEmail(Session session, String subject, String content, String[] attachment) throws MessagingException {
        this(session);
        this.setSubject(subject);
        Multipart multipart = new MimeMultipart();
        if (attachment.length == 0) {
            this.setText(content);
        }else
        if (content != null && "".equals(content)) {
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText(content);
            multipart.addBodyPart(mimeBodyPart);
        }



    }

    public String subject() {
        return null;
    }

    public String content() {
        return null;
    }

    public String[] attachment() {
        return new String[0];
    }
}
