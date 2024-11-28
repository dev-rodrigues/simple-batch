package com.example.simplebatchalura.infrastructure.adapter.writer;

import com.example.simplebatchalura.application.domain.Importacao;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatabaseWriter {
    @Bean
    public ItemWriter<Importacao> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Importacao>()
                .dataSource(dataSource)
                .sql("""
                            insert into importacoes (cpf, cliente, nascimento, evento, data, tipo_ingresso, valor, hora_importacao, taxa_adm)
                            values (:cpf, :cliente, :nascimento, :evento, :data, :tipoIngresso, :valor, current_timestamp, :taxaAdm)
                        """)
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }
}
