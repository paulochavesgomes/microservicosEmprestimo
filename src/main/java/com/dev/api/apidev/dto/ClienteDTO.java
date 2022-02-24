package com.dev.api.apidev.dto;

import com.dev.api.apidev.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private String cpf;

    public ClienteDTO(Cliente cliente) {
        setId(cliente.getId());
        setNome(cliente.getNome());
        setCpf(cliente.getCpf());
    }
}
