import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'

import { Cliente } from '../cliente';
import { ClientesService } from 'src/app/clientes.service';

@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.css']
})
export class ClientesListaComponent implements OnInit {

  clientes: Cliente[] = [];
  clienteSelecionado: Cliente;
  mensagemSucesso: string;
  mensagemErro: string;

  //injeção de dependência
  constructor(
    private service: ClientesService,
    private router: Router){}

  //callback
  ngOnInit(): void {
    this.service
    .getClientes()
    .subscribe( resposta => this.clientes = resposta);
  }

  //método novoCadastro
  novoCadastro(){
    this.router.navigate(['/clientes-form'])
  }

  //modal
  preparaDelecao(cliente: Cliente){
    this.clienteSelecionado = cliente;
  }

  //delete
  deletarCliente(){
    this.service
    .deletar(this.clienteSelecionado)
    .subscribe( 
      response => { 
        this.mensagemSucesso = 'Cliente excluído com sucesso!'
        this.ngOnInit();
      },
      erro => this.mensagemErro = 'Ocorreu um erro ao deletar o cliente!'  
    )
  }
}
