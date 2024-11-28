package com.example.simplebatchalura.application.config;

import com.example.simplebatchalura.application.domain.processor.ImportacaoProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImportacaoProcessorConfiguration {
    @Bean
    public ImportacaoProcessor processor() {
        return new ImportacaoProcessor();
    }
}
