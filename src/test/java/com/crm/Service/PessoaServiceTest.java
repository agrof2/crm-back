package com.crm.Service;

import com.crm.Dto.PessoaDTO;
import com.crm.Entity.Pessoa;
import com.crm.Entity.Sexo;
import com.crm.Form.PessoaForm;
import com.crm.Mapper.PessoaMapper;
import com.crm.Repository.PessoaRepository;
import com.crm.Utils.DatasEHoras;
import com.crm.Utils.ValidarCPF;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Teste Serviços: Pessoa")
@ExtendWith(SpringExtension.class)
class PessoaServiceTest {
    @InjectMocks
    private PessoaService pessoaService;
    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private PessoaMapper pessoaMapper;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pageable = PageRequest.of(0, 10);
        UUID id=UUID.randomUUID();
        Pessoa pessoa = Pessoa.builder()
                .id(id)
                .nome("Felipe Salvador")
                .cpf("65601238472")
                .sexo(Sexo.MASC)
                .dataNascimento(DatasEHoras.parseDate("1992-01-13", "yyyy-MM-dd"))
                .deleted(false)
                .build();
        PessoaDTO pessoaDTO = PessoaDTO.builder()
                .id(id)
                .nome("Felipe Salvador")
                .cpf("65601238472")
                .sexo(Sexo.MASC.getName())
                .dataNascimento("13/01/1992")
                .build();

        when(pessoaRepository.findById(any())).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.findByNomeLikeOrCpfLike(any(), any(), any())).thenReturn(new PageImpl<>(List.of(pessoa)));
    }

    @Test
    void get() {
        Pessoa p = pessoaService.get(UUID.randomUUID());

        assertTrue(Objects.nonNull(p));
        assertTrue(ValidarCPF.isCPF(p.getCpf()));
        assertNotNull(p.getDataNascimento());
    }

    @Test
    void list() {
        Page<Pessoa> page = pessoaService.list(PageRequest.of(0, 10), "Felipe", "");
        assertTrue(page.getTotalPages() > 0);
        assertTrue(page.getTotalElements() > 0);
    }

    @Test
    void save() {
        PessoaForm pessoa = PessoaForm.builder()
                .nome("Felipe Salvador")
                .cpf("65601238472")
                .sexo(Sexo.MASC.getName())
                .dataNascimento("13/01/1992")
                .email("")
                .build();
        Pessoa res = pessoaService.save(pessoa);

        assertTrue(Objects.nonNull(res));
        assertTrue(Objects.nonNull(res.getId()));
        assertTrue(Objects.nonNull(res.getCreatedDate()));
    }

    @Test
    void update() {
        PessoaForm pessoa = PessoaForm.builder()
                .nome("Felipe Salvador")
                .cpf("65601238472")
                .sexo(Sexo.MASC.getName())
                .dataNascimento("13/01/1992")
                .email("")
                .build();
        when(pessoaMapper.pessoaFormToPessoa(any())).thenReturn(Pessoa.builder()
                .id(UUID.randomUUID())
                .nome("Felipe Salvador")
                .cpf("65601238472")
                .sexo(Sexo.MASC)
                .dataNascimento(DatasEHoras.parseDate("1992-01-13", "yyyy-MM-dd"))
                .deleted(false)
                .build());
        Pessoa res = pessoaService.save(pessoa);
        PessoaForm pessoaUpdated = PessoaForm.builder()
                .nome("Felipe Augusto")
                .cpf("65601238472")
                .sexo(Sexo.MASC.getName())
                .dataNascimento("13/01/1992")
                .email("")
                .build();
        res = pessoaService.update(res.getId(), pessoa);

        assertTrue(Objects.nonNull(res));
        assertTrue(Objects.nonNull(res.getId()));
        assertTrue(res.getNome().equals(pessoaUpdated.getNome()));
        assertTrue(Objects.nonNull(res.getUpdatedDate()));
    }
}