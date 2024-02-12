package com.braincustom.clientes.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class ServicoPrestado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false, length = 150)
    private  String descricao;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private  Cliente cliente;

    @Column
    private BigDecimal valor;

    @Column
    private LocalDate date;
}
