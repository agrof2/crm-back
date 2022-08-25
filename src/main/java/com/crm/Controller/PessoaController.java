package com.crm.Controller;

import com.crm.Dto.PessoaDTO;
import com.crm.Entity.Pessoa;
import com.crm.Form.PessoaForm;
import com.crm.Mapper.PessoaMapper;
import com.crm.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaMapper pessoaMapper;

    @GetMapping
    public ResponseEntity<PessoaDTO> get(@RequestParam(required = true) UUID id) {
        return ResponseEntity.ok(pessoaMapper.pessoaToPessoaDTO(pessoaService.get(id)));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Pessoa>> list(@PageableDefault(sort = "nome") Pageable page,
                                             @RequestParam(required = false) String nome,
                                             @RequestParam(required = false) String cpf) {
        return ResponseEntity.ok(pessoaService.list(page, nome, cpf));
    }

    @PutMapping
    public ResponseEntity<PessoaDTO> save(@Valid @RequestBody PessoaForm pessoaForm) {
        return ResponseEntity.ok(pessoaMapper.pessoaToPessoaDTO(pessoaService.save(pessoaForm)));
    }

    @PostMapping("/id")
    public ResponseEntity<PessoaDTO> update(@RequestParam(required = true) UUID id,
                                          @Valid @RequestBody PessoaForm pessoaForm) {
        return ResponseEntity.ok(pessoaMapper.pessoaToPessoaDTO(pessoaService.update(id, pessoaForm)));
    }
}
