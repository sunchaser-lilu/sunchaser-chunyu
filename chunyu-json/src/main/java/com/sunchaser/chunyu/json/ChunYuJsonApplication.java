package com.sunchaser.chunyu.json;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * SpringBoot整合JSON序列化 启动器
 *
 * @author sunchaser admin@lilu.org.cn
 * @since JDK8 2022/3/16
 */
@SpringBootApplication
public class ChunYuJsonApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ChunYuJsonApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
