package com.crm.Form;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaForm {
    @NotBlank
    private String nome;
    private String sexo;
    private String email;
    @NotBlank
    private String dataNascimento;
    private String naturalidade;
    private String nacionalidade;
    @NotBlank
    private String cpf;
}
