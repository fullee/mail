package org.oskworker.mail.configure;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * <h1>配置类</h1>
 * 可以使用API，配置文件
 * Created by full on 2018/5/6.
 */
@Builder
@Getter
@ToString
public class Configure {

    private final String stmp;
    private final String sender;
    private final String password;
    private final int port;
    private final boolean debug;

    public Configure(String stmp, String sender, String password, int port, boolean debug) {
        this.stmp = stmp;
        this.sender = sender;
        this.password = password;
        this.port = port;
        this.debug = debug;
    }
}
