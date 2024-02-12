package com.braincustom.clientes.rest;

import com.braincustom.clientes.model.repository.ClienteRepository;
import com.braincustom.clientes.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/*
* @Valid para salvar e atualizar, ja fazendo tratamento de errors,
* no ControllerAdvice, aplica o ExceptionHandler na aplicacao BACKEND, onde
* possui o @Valid
* */

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("http://localhost:4200")
public class ClienteController {

    private final ClienteRepository repository;

    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    //retornando todos os clientes
    @GetMapping
    public List<Cliente> obterTodos(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)//201 criado
    public Cliente salvar(@RequestBody @Valid Cliente cliente){
        return repository.save(cliente);
    }

    @GetMapping("{id}")
    public Cliente buscarPorId(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar( @PathVariable Integer id){
        repository
                .findById(id)
                .map( cliente -> {
                    repository.delete(cliente);
                    return  Void.TYPE;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id,
                          @RequestBody @Valid Cliente clienteAtualizado){
        repository
                .findById(id)
                .map( cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    return repository.save(clienteAtualizado);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }
}
