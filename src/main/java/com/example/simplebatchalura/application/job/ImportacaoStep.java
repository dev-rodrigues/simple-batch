package com.example.simplebatchalura.application.job;

import com.example.simplebatchalura.application.domain.processor.ImportacaoProcessor;
import com.example.simplebatchalura.application.domain.Importacao;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
public class ImportacaoStep {

    private final PlatformTransactionManager platformTransactionManager;

    public ImportacaoStep(PlatformTransactionManager platformTransactionManager) {
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public Step passoInicial(
            ItemReader<Importacao> reader,
            ItemWriter<Importacao> writer,
            JobRepository jobRepository,
            ImportacaoProcessor processor
    ) {
        return new StepBuilder("passo-inicial", jobRepository)
                .<Importacao, Importacao>chunk(1, platformTransactionManager)
                .reader(reader) // leitura-csv
                .processor(processor) // processamento
                .writer(writer) // escrita
                .build();
    }
}
