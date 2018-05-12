package org.oskworker.mail.configure;

import com.sun.istack.internal.Nullable;
import lombok.Builder;

/**
 * <h1>配置类</h1>
 * 可以使用API，注解，配置文件
 * Created by full on 2018/5/6.
 */
@Builder
public class Configure {

    private final String stmp;
    private final String sender;
    private final String password;

    public Configure(String stmp, String sender, String password) {
        this.stmp = stmp;
        this.sender = sender;
        this.password = password;
    }

    public String getStmp() {
        return stmp;
    }

    public String getSender() {
        return sender;
    }

    public String getPassword() {
        return password;
    }

}
