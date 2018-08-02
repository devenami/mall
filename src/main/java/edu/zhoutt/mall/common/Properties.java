package edu.zhoutt.mall.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
@ConfigurationProperties("mall")
public class Properties {

    @NestedConfigurationProperty
    private FTPProperties ftp = new FTPProperties();

    @Data
    public class FTPProperties {
        private String host;
        private Integer port = 21;
        private String username;
        private String password;
    }

}
