package com.br.oauth2.rsa.bean;

import com.br.oauth2.rsa.RsaKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyBean {

    @Bean
    public RsaKeyGenerator rsaKeyGenerator() {
        return new RsaKeyGenerator();
    }
}
