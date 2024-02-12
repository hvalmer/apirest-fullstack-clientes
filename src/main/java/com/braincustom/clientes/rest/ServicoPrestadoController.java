package com.braincustom.clientes.rest;

import com.braincustom.clientes.model.entity.Cliente;
import com.braincustom.clientes.model.entity.ServicoPrestado;
import com.braincustom.clientes.model.repository.ClienteRepository;
import com.braincustom.clientes.model.repository.ServicoPrestadoRepository;
import com.braincustom.clientes.rest.dto.ServicoPrestadoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {

    /*
    * @RequiredArgsConstructor - cria um construtor com os argumentos
    * obrigatórios, marcados com final, criando esses construtores
    * sem necessidade de escrever mais códigos
    */

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository servicoPrestadoRepository;

    @PostMapping
    public ServicoPrestado salvar( @RequestBody ServicoPrestadoDTO dto ){
         LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
         String idCliente = dto.getIdCliente();

        Cliente cliente = clienteRepository
                            .findById(idCliente)
                            .orElseThrow(() ->
                                    new ResponseStatusException(
                                            HttpStatus.BAD_REQUEST, "Cliente inexistente!"));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setDate(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor();
    }
}
