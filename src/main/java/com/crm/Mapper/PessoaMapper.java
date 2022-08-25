package com.crm.Mapper;

import com.crm.Dto.PessoaDTO;
import com.crm.Entity.Pessoa;
import com.crm.Entity.Sexo;
import com.crm.Form.PessoaForm;
import com.crm.Utils.DatasEHoras;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class PessoaMapper {

    public Pessoa pessoaFormToPessoa(PessoaForm p) {
        return Pessoa.builder()
                .nome(p.getNome())
                .sexo(Sexo.valueOfName(p.getSexo()))
                .email(p.getEmail())
                .dataNascimento(DatasEHoras.parseDate(p.getDataNascimento(), "dd/MM/yyyy"))
                .naturalidade(p.getNaturalidade())
                .nacionalidade(p.getNacionalidade())
                .cpf(p.getCpf())
                .build();
    }

    public PessoaDTO pessoaToPessoaDTO(Pessoa p) {
        return PessoaDTO.builder()
                .id(p.getId())
                .nome(p.getNome())
                .sexo(p.getSexo().getName())
                .dataNascimento(p.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .naturalidade(p.getNaturalidade())
                .nacionalidade(p.getNacionalidade())
                .cpf(p.getCpf())
                .build();
    }
}
