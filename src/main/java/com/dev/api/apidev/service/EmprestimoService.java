package com.dev.api.apidev.service;

import com.dev.api.apidev.dto.EmprestimoDTO;
import com.dev.api.apidev.model.Emprestimo;
import com.dev.api.apidev.repository.EmprestimoRepository;
import com.fasterxml.jackson.databind.util.Converter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;


    public Emprestimo salvarEmprestimo(Emprestimo emprestimo){
        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo atualizar(Emprestimo emprestimo){
        return emprestimoRepository.save(emprestimo);
    }

    public  Page<EmprestimoDTO> listarTodos(Pageable pageable){
        Page<Emprestimo> entities = emprestimoRepository.findAll(pageable);
        Page<EmprestimoDTO> dtoPage2 = entities.map(EmprestimoDTO::new);
        Page<EmprestimoDTO> dtoPage = entities.map(new Function<Emprestimo, EmprestimoDTO>() {
            public EmprestimoDTO apply(Emprestimo emprestimo) {
                EmprestimoDTO dto = new EmprestimoDTO();
                return dto.converterToDTO(emprestimo);
            }
         }
        );
        return  dtoPage2;
    }

    public Optional<Emprestimo> findById(Long id){

        return emprestimoRepository.findById(id);
    }

    public void deletar(Emprestimo emprestimo){
        emprestimoRepository.delete(emprestimo);
    }




}
