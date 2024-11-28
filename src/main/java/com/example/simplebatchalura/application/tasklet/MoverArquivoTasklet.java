package com.example.simplebatchalura.application.tasklet;

import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MoverArquivoTasklet {
    @Bean
    public Tasklet task() {
        return (contribution, chunkContext) -> {
            File pastaOrigem = new File("files");
            File pastaDestino = new File("imported-files");

            if (!pastaDestino.exists()) {
                pastaDestino.mkdir();
            }

            File[] arquivos = pastaOrigem.listFiles((dir, name) -> name.endsWith(".csv"));

            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    File arquivoDestino = new File(pastaDestino, arquivo.getName());
                    if (arquivo.renameTo(arquivoDestino)) {
                        System.out.println("Arquivo movido com sucesso");
                    } else {
                        throw new RuntimeException("Não foi possível mover o arquivo");
                    }
                }
            }

            return RepeatStatus.FINISHED;
        };
    }
}
