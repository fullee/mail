package org.oskworker.mail.entity;

import lombok.Builder;

@Builder
public class AttachmentEmail extends Email{

    private String subject;
    private String content;
    private String[] attachment;

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
