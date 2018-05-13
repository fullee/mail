package org.oskworker.mail.entity;

/**
 * Created by full on 2018/5/6.
 */
public interface Email {

    String subject();

    String content();

    String[] attachment();
}
