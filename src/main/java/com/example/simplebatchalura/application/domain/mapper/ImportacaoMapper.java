package com.example.simplebatchalura.application.domain.mapper;

import com.example.simplebatchalura.application.domain.Importacao;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ImportacaoMapper implements FieldSetMapper<Importacao> {

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Importacao mapFieldSet(@NonNull FieldSet fieldSet) {
        Importacao importacao = new Importacao();
        importacao.setCpf(fieldSet.readString("cpf"));
        importacao.setCliente(fieldSet.readString("cliente"));
        importacao.setNascimento(LocalDate.parse(fieldSet.readString("nascimento"), dateFormatter));
        importacao.setEvento(fieldSet.readString("evento"));
        importacao.setData(LocalDate.parse(fieldSet.readString("data"), dateFormatter));
        importacao.setTipoIngresso(fieldSet.readString("tipoIngresso"));
        importacao.setValor(fieldSet.readDouble("valor"));
        importacao.setHoraImportacao(LocalDateTime.now());

        return importacao;
    }
}
