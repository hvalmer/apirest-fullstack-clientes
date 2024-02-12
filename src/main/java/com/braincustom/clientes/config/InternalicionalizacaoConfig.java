package com.braincustom.clientes.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

/*
* Padronizando as classes de configuração
**/
@Configuration
public class InternalicionalizacaoConfig {

    @Bean //primeiro Bean vai ser a fonte das mensagens sera utilizada no sistema
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");//arquivo de mensagens(messages.properties)
        messageSource.setDefaultEncoding("ISO-8859-1");//caracteres especiais
        messageSource.setDefaultLocale(Locale.getDefault());//Locale do SO
        return messageSource;
    }

    @Bean//outro Bean que faça a interpolacao
    public LocalValidatorFactoryBean validatorFactoryBean(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
