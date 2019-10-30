package com.epoint.bbs.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    /**
     * mybatis个性化配置
     * @return
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return (org.apache.ibatis.session.Configuration configuration) -> configuration.setMapUnderscoreToCamelCase(true);
        /**
         * new ConfigurationCustomizer(){
        @Override
        public void customize(org.apache.ibatis.session.Configuration configuration) {
        //开启驼峰命名
        configuration.setMapUnderscoreToCamelCase(true);
        }
         */
        }
    }

