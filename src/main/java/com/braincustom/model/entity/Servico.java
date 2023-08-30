package com.braincustom.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Servico {

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
}
