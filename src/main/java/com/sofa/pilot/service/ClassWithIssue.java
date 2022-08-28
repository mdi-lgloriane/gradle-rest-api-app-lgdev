package com.sofa.pilot.service;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.MultipartConfigElement;

public class ClassWithIssue {

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolverUploadTest() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(104857600); // Sensitive (100MB)
        return multipartResolver;
    }
}