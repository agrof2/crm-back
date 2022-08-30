package com.crm.Mapper;

import com.crm.Dto.PessoaDTO;
import com.crm.Entity.Pessoa;
import com.crm.Entity.Sexo;
import com.crm.Form.PessoaForm;
import com.crm.Utils.DatasEHoras;
import com.crm.Utils.LocalizarTextos;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
                .cpf(LocalizarTextos.extractText(p.getCpf(), LocalizarTextos.APENAS_NUMEROS_SEM_ESPACO_OU_TABULACOES))
                .build();
    }

    public Pessoa pessoaFormToPessoa_Update(PessoaForm pForm, Pessoa p) {
        p.setNome(pForm.getNome());
        p.setCpf(LocalizarTextos.extractText(p.getCpf(), LocalizarTextos.APENAS_NUMEROS_SEM_ESPACO_OU_TABULACOES));
        p.setSexo(Sexo.valueOfName(pForm.getSexo()));
        if (StringUtils.hasText(pForm.getNaturalidade())) p.setNaturalidade(pForm.getNaturalidade());
        if (StringUtils.hasText(pForm.getNacionalidade())) p.setNacionalidade(pForm.getNacionalidade());
        if (StringUtils.hasText(pForm.getEmail())) p.setEmail(pForm.getEmail());
        if (StringUtils.hasText(pForm.getDataNascimento()) && DatasEHoras.isDataValida(pForm.getDataNascimento(), "dd/MM/yyyy")) p.setDataNascimento(DatasEHoras.parseDate(pForm.getDataNascimento(), "dd/MM/yyyy"));

        return p;
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
