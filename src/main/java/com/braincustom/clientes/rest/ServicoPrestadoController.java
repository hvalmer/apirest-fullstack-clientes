package com.braincustom.clientes.rest;

import com.braincustom.clientes.model.entity.Cliente;
import com.braincustom.clientes.model.entity.ServicoPrestado;
import com.braincustom.clientes.model.repository.ClienteRepository;
import com.braincustom.clientes.model.repository.ServicoPrestadoRepository;
import com.braincustom.clientes.rest.dto.ServicoPrestadoDTO;
import com.braincustom.clientes.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private final ServicoPrestadoRepository repository;
    private final BigDecimalConverter bigDecimalConverter;

    /*método para salvar os serviços prestados para cadastrar um cliente*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar( @RequestBody ServicoPrestadoDTO dto ){
         LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
         Integer idCliente = dto.getIdCliente();

        Cliente cliente =
                clienteRepository
                            .findById(idCliente)
                            .orElseThrow(() ->
                                    new ResponseStatusException(
                                            HttpStatus.BAD_REQUEST, "Cliente inexistente!"));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setDate(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));

        return repository.save(servicoPrestado);
    }
}
