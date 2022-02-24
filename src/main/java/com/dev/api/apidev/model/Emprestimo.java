package com.dev.api.apidev.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Emprestimo {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nome;
        @Column(length = 200, nullable = false)
        private BigDecimal montante;
        @Column(nullable = false)
        private Integer qtdparcela;
        @Transient
        private Long idCliente;
        @ManyToOne
        @JoinColumn(name = "id_cliente")
        private Cliente cliente;

        public Emprestimo(String nome, BigDecimal montante, Integer qtdparcela, Long idCliente) {
                this.nome = nome;
                this.montante = montante;
                this.qtdparcela = qtdparcela;
                this.idCliente = idCliente;
        }

        public Emprestimo(Long id, String nome, BigDecimal montante, Integer qtdparcela, Long idCliente) {
                this.id = id;
                this.nome = nome;
                this.montante = montante;
                this.qtdparcela = qtdparcela;
                this.idCliente = idCliente;
        }
}
