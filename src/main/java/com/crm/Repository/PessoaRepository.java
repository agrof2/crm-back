package com.crm.Repository;

import com.crm.Entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    public Optional<Pessoa> findById(UUID id);

    public Page<Pessoa> findByNomeLike(String nome, Pageable pageable);

    public Page<Pessoa> findByCpfLike(String cpf, Pageable pageable);

    public Page<Pessoa> findByNomeLikeOrCpfLike(String nome, String cpf, Pageable pageable);

    public Optional<Pessoa> findByCpf(String cpf);

    public Optional<Pessoa> findByNome(String nome);
}
