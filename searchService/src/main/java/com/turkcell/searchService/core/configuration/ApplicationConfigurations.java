package com.turkcell.searchService.core.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfigurations {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
