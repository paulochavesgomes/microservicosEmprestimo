package com.dev.api.apidev.controller;


import com.dev.api.apidev.dto.ClienteDTO;
import com.dev.api.apidev.dto.EmprestimoDTO;
import com.dev.api.apidev.model.Cliente;
import com.dev.api.apidev.model.Emprestimo;
import com.dev.api.apidev.service.ClienteService;
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
@RequestMapping("/emprestimo/cliente")
@Data
@AllArgsConstructor
public class ClienteControlller {

    private ClienteService clienteService;


    @PostMapping("/salvar")
    public ResponseEntity<ClienteDTO> salvarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteDTO reponse = clienteService.salvarCliente(clienteDTO);
        return new ResponseEntity<ClienteDTO>(reponse, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<ClienteDTO>> listarTodos(Pageable pageable){
        Page<ClienteDTO> lista = clienteService.listarTodos(pageable);
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.ok().body(lista);
        }
    }


    @PutMapping("/atualizar")
    public ResponseEntity<ClienteDTO> atualizarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        Optional<Cliente> dtd = clienteService.findById(clienteDTO.getId());
        if(!dtd.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            ClienteDTO response = new ClienteDTO();
            response = clienteService.atualizarCliente(clienteDTO);
            return new ResponseEntity<ClienteDTO>(response, HttpStatus.OK);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable(value="id") Long id) {
        Optional<Cliente> dtd = clienteService.findById(id);
        if(!dtd.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            Cliente cliente = dtd.get();
            clienteService.deletar(cliente);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @GetMapping("/buscarporid/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable(value="id") Long id) {
        Optional<Cliente> dtd = clienteService.findById(id);
        if(!dtd.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            Cliente cliente = dtd.get();
            ClienteDTO response =  new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCpf());
            return new ResponseEntity<ClienteDTO>(response, HttpStatus.OK);
        }
    }



}
