package com.crm.Dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {
    private UUID id;
    private String nome;
    private String sexo;
    private String dataNascimento;
    private String naturalidade;
    private String nacionalidade;
    private String cpf;
}
