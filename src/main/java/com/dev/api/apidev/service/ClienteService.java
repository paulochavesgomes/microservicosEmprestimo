package com.dev.api.apidev.service;


import com.dev.api.apidev.dto.ClienteDTO;
import com.dev.api.apidev.dto.EmprestimoDTO;
import com.dev.api.apidev.model.Cliente;
import com.dev.api.apidev.model.Emprestimo;
import com.dev.api.apidev.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteDTO salvarCliente(ClienteDTO clienteDTO){
        Cliente cliente =  new Cliente(clienteDTO.getNome(), clienteDTO.getCpf());
        cliente =  clienteRepository.save(cliente);
        return  new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCpf());
    }
    public Page<ClienteDTO> listarTodos(Pageable pageable){
        Page<Cliente> entities = clienteRepository.findAll(pageable);
        Page<ClienteDTO> dtoPage2 = entities.map(ClienteDTO::new);
        Page<ClienteDTO> dtoPage = entities.map(new Function<Cliente, ClienteDTO>() {
            public ClienteDTO apply(Cliente cliente) {
                ClienteDTO dto = new ClienteDTO();
                dto.setId(cliente.getId());
                dto.setNome(cliente.getNome());
                dto.setCpf(cliente.getCpf());
                return dto;
             }
         }
        );
        return dtoPage;
    }

    public ClienteDTO atualizarCliente(ClienteDTO clienteDTO){
        Cliente cliente =  new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente =  clienteRepository.save(cliente);
        return  new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCpf());
    }

    public Optional<Cliente> findById(Long id){
        return clienteRepository.findById(id);
    }

    public void deletar(Cliente cliente){
        clienteRepository.delete(cliente);
    }



}
