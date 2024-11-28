package com.example.simplebatchalura.application.job;

import com.example.simplebatchalura.application.tasklet.MoverArquivoTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

public class ImportacaoJob {

    private final PlatformTransactionManager platformTransactionManager;
    private final MoverArquivoTasklet moverArquivoTasklet;

    public ImportacaoJob(PlatformTransactionManager platformTransactionManager, MoverArquivoTasklet moverArquivoTasklet) {
        this.platformTransactionManager = platformTransactionManager;
        this.moverArquivoTasklet = moverArquivoTasklet;
    }

    @Bean
    public Job job(Step passoInicial, JobRepository jobRepository) {
        return new JobBuilder("geracao-tickets", jobRepository)
                .start(passoInicial)
                .next(moverArquivoStep(jobRepository))
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step moverArquivoStep(JobRepository jobRepository) {
        return new StepBuilder("mover-arquivo", jobRepository)
                .tasklet(moverArquivoTasklet.task(), platformTransactionManager)
                .build();
    }
}
