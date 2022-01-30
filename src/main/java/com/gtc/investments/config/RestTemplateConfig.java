package com.gtc.investments.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Primary
    @Bean(name="default")
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        List<ClientHttpRequestInterceptor> intercepterList = restTemplate.getInterceptors();

        if (CollectionUtils.isEmpty(intercepterList)){
            intercepterList = new ArrayList<>();
        }

        intercepterList.add(new RequestResponseLoggingInterceptor());
        restTemplate.setInterceptors(intercepterList);
        return restTemplate;
    }
}
