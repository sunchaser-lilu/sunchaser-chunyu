package com.sunchaser.chunyu.apollo.web.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import com.sunchaser.chunyu.apollo.config.ChunYuApolloConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Apollo客户端读取配置项
 * 需添加VM Options启动参数：-Denv=DEV -Dapollo.meta=http://localhost:8080
 *
 * @author sunchaser admin@lilu.org.cn
 * @since JDK8 2022/3/27
 */
@RestController
@Slf4j
public class ApolloConfigController {

    @ApolloConfig(value = "application.yml")
    private Config config;

    @GetMapping("/config")
    public String getConfig(String key) {
        return config.getProperty(key, null);
    }

    /**
     * yml format:
     * chunyu:
     * apollo:
     * jsonstr: '[{"name":"sun", "age": 18}, {"name":"chaser", "age": 20}]'
     */
    @ApolloJsonValue("${chunyu.apollo.jsonstr:[]}")
    private List<JsonBean> jsonBeanList;

    @Data
    public static class JsonBean {
        private String name;
        private Integer age;
    }

    @GetMapping("/json-value")
    public List<JsonBean> getJsonValue() {
        return jsonBeanList;
    }

    @Value("${chunyu.apollo.name:defaultVal}")
    private String key;
    @Value("#{'${chunyu.apollo.jsonstr:[]}'.split(',')}")
    private List<String> jsonList;

    @GetMapping("/placeholder")
    public void getConfigByPlaceholder() {
        log.info("key={}, jsonList={}", key, jsonList);
    }

    @Autowired
    private ChunYuApolloConfig chunyuApolloConfig;

    @GetMapping("/configurationProperties")
    public void getChunYuApolloConfig() {
        String name = chunyuApolloConfig.getName();
        List<String> jsonstr = chunyuApolloConfig.getJsonstr();
        log.info("name={}, jsonstr={}", name, jsonstr);
    }
}
