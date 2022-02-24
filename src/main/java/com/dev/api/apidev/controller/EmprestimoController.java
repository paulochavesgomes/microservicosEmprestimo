package com.dev.api.apidev.controller;

import com.dev.api.apidev.dto.EmprestimoDTO;
import com.dev.api.apidev.model.Cliente;
import com.dev.api.apidev.model.Emprestimo;
import com.dev.api.apidev.service.ClienteService;
import com.dev.api.apidev.service.EmprestimoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/emprestimo")
@Data
@AllArgsConstructor
public class EmprestimoController {


    private EmprestimoService emprestimoService;
    private ClienteService clienteService;

    @PostMapping("/salvar")
    public ResponseEntity<EmprestimoDTO> salvarEmprestimo(@RequestBody @Valid EmprestimoDTO emprestimoDTO) {
        Optional<Cliente> cliente =  clienteService.findById(emprestimoDTO.getIdCliente());

        if(!cliente.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            Emprestimo emprestimo = emprestimoDTO.converter(emprestimoDTO);
            emprestimo.setCliente(cliente.get());
            emprestimo =  emprestimoService.salvarEmprestimo(emprestimo);
            return new ResponseEntity<EmprestimoDTO>(emprestimoDTO.converterToDTO(emprestimo), HttpStatus.CREATED);
        }


    }
  @GetMapping("/listar")
    public ResponseEntity<Page<EmprestimoDTO>> listarTodos(Pageable pageable){

       Page<EmprestimoDTO> lista = emprestimoService.listarTodos(pageable);
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.ok().body(lista);
        }
    }


    @PutMapping("/atualizar")
    public ResponseEntity<EmprestimoDTO> atualizar(@RequestBody  @Valid EmprestimoDTO emprestimoDTO){
        Optional<Emprestimo> emprestimoOptional = emprestimoService.findById(emprestimoDTO.getId());
        Optional<Cliente> cliente =  clienteService.findById(emprestimoDTO.getIdCliente());
        if(!emprestimoOptional.isPresent() || !cliente.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            Emprestimo emprestimo = emprestimoDTO.converter(emprestimoDTO);
            emprestimo.setCliente(cliente.get());
            emprestimo =  emprestimoService.atualizar(emprestimo);
            return new ResponseEntity<EmprestimoDTO>(emprestimoDTO.converterToDTO(emprestimo), HttpStatus.OK);
        }
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value="id") Long id){
        Optional<Emprestimo> emprestimoOptional = emprestimoService.findById(id);
        if(!emprestimoOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            Emprestimo emprestimo = emprestimoOptional.get();
            emprestimoService.deletar(emprestimo);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @GetMapping("/buscarporid/{id}")
    public ResponseEntity<EmprestimoDTO> buscarPorId(@PathVariable(value="id") Long id){
        Optional<Emprestimo> dto = emprestimoService.findById(id);
        EmprestimoDTO emprestimoDTO = new EmprestimoDTO();
        if(!dto.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            Emprestimo emprestimo = dto.get();
            emprestimoDTO = emprestimoDTO.converterToDTO(emprestimo);
            emprestimoDTO.setIdCliente(emprestimo.getCliente().getId());
            return new ResponseEntity<EmprestimoDTO>(emprestimoDTO, HttpStatus.OK);
        }

    }



}
