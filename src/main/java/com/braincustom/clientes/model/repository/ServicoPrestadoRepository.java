package com.braincustom.clientes.model.repository;

import com.braincustom.clientes.model.entity.ServicoPrestado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer> {

    @Query("select serv from ServicoPrestado serv join serv.cliente cli " +
            "where upper(cli.nome) like upper(:nome) and MONTH(serv.data) =:mes")
    List<ServicoPrestado> findByNomeClienteAndMes(
            @Param("nome") String nome,
            @Param("mes") Integer mes);
}
