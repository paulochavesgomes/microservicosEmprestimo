package com.dev.api.apidev.dto;

import com.dev.api.apidev.model.Emprestimo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoDTO {

    private Long id;

    private String nome;

    private BigDecimal montante;

    private Integer qtdparcela;

    private Long idCliente;

    public EmprestimoDTO(Emprestimo emprestimo) {
        setId(emprestimo.getId());
        setNome(emprestimo.getNome());
        setMontante(emprestimo.getMontante());
        setQtdparcela(emprestimo.getQtdparcela());
        setIdCliente(emprestimo.getCliente().getId());
    }

    public  List<EmprestimoDTO> converterLista(List<Emprestimo> emprestimos) {
        return emprestimos.stream().map(emprestimo -> new EmprestimoDTO(
                emprestimo.getId(),
                emprestimo.getNome(),
                emprestimo.getMontante(),
                emprestimo.getQtdparcela(),
                emprestimo.getIdCliente())).collect(Collectors.toList());
    }
    public EmprestimoDTO converterToDTO(Emprestimo emprestimo){
        EmprestimoDTO emprestimoDTO = new EmprestimoDTO(
                emprestimo.getId(),
                emprestimo.getNome(),
                emprestimo.getMontante(),
                emprestimo.getQtdparcela(),
                emprestimo.getIdCliente()
        );
        return emprestimoDTO;
    }
    public Emprestimo converter(EmprestimoDTO emprestimoDTO){
        Emprestimo emprestimo = new Emprestimo(
                emprestimoDTO.getNome(),
                emprestimoDTO.getMontante(),
                emprestimoDTO.getQtdparcela(),
                emprestimoDTO.getIdCliente()
        );
        return emprestimo;
    }
}
