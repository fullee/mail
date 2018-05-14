package org.oskworker.mail.entity;

/**
 * Created by full on 2018/5/6.
 */
public abstract class Email {
    String subject;
    String content;
    String[] attachment;

    public abstract String subject();

    public abstract String content();

    public abstract String[] attachment();
}
