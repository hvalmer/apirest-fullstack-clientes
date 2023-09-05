package com.braincustom.rest;

import com.braincustom.model.entity.Cliente;
import com.braincustom.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)//201 criado
    public Cliente salvar(@RequestBody Cliente cliente){
        return repository.save(cliente);
    }
}
