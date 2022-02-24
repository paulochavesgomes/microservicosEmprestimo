package com.dev.api.apidev.repository;

import com.dev.api.apidev.model.Emprestimo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
   /* @Query(value = " select * from emprestimo e join cliente c order by e.nome ", nativeQuery = true)
    public Page<Emprestimo> listarTodos(Pageable pageable);*/
}
