package com.example.excel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

//@Configuration
public class FileConfig {

    @Bean("multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxInMemorySize(4096);
        multipartResolver.setMaxUploadSize(1024000000);
        multipartResolver.setResolveLazily(true);
        multipartResolver.setDefaultEncoding("utf-8");
        return multipartResolver;
    }

}
