package com.crm.Service;

import com.crm.Dto.PessoaDTO;
import com.crm.Entity.Pessoa;
import com.crm.Form.PessoaForm;
import com.crm.Mapper.PessoaMapper;
import com.crm.Repository.PessoaRepository;
import com.crm.Utils.LocalizarTextos;
import com.crm.Utils.ValidarCPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaMapper pessoaMapper;

    public Pessoa get(UUID id){
        if (Objects.isNull(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id Inválido");
        }
        Optional<Pessoa> p = pessoaRepository.findById(id);
        if (!p.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa não existe");
        }
        return p.get();
    }

    public Page<Pessoa> list(Pageable pageable, String nome, String cpf){
        Page<Pessoa> list = Page.empty();
        if (StringUtils.hasText(nome)
         | StringUtils.hasText(cpf)) list = pessoaRepository.findByNomeLikeOrCpfLike(nome, cpf, pageable);
        else list = pessoaRepository.findAll(pageable);
        if (list.hasContent()) return list;
        return Page.empty();
    }

    public Pessoa save(PessoaForm pForm) {
        if (!ValidarCPF.isCPF(pForm.getCpf())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF Inválido!");
        } else {
            Optional<Pessoa> hasCPF = pessoaRepository.findByCpf(LocalizarTextos.extractText(pForm.getCpf(), LocalizarTextos.APENAS_NUMEROS_SEM_ESPACO_OU_TABULACOES));
            if (hasCPF.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já existe!");
            }
        }
        if (StringUtils.hasText(pForm.getEmail()) && !LocalizarTextos.isEmail(pForm.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail Inválido!");
        }
        Optional<Pessoa> hasNome = pessoaRepository.findByNome(pForm.getNome());
        if (hasNome.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa já existe!");
        }

        Pessoa p = pessoaMapper.pessoaFormToPessoa(pForm);
        return pessoaRepository.save(p);
    }

    public Pessoa update(UUID id, PessoaForm pForm) {
        Pessoa p = get(id);
        if (!ValidarCPF.isCPF(pForm.getCpf())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF Inválido!");
        } else {
            if (!p.getCpf().equalsIgnoreCase(LocalizarTextos.extractText(pForm.getCpf(), LocalizarTextos.APENAS_NUMEROS_SEM_ESPACO_OU_TABULACOES))) {
                Optional<Pessoa> hasCPF = pessoaRepository.findByCpf(LocalizarTextos.extractText(pForm.getCpf(), LocalizarTextos.APENAS_NUMEROS_SEM_ESPACO_OU_TABULACOES));
                if (hasCPF.isPresent() && !id.equals(hasCPF.get().getId())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já existe!");
                }
            }
        }
        if (StringUtils.hasText(pForm.getEmail()) && !LocalizarTextos.isEmail(pForm.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail Inválido!");
        }
        if (!p.getNome().equalsIgnoreCase(pForm.getNome())) {
            Optional<Pessoa> hasNome = pessoaRepository.findByNome(pForm.getNome());
            if (hasNome.isPresent() && !id.equals(hasNome.get().getId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa já existe!");
            }
        }

        p = pessoaMapper.pessoaFormToPessoa_Update(pForm, p);
        return pessoaRepository.save(p);
    }
}
