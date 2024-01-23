import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente';
import { ClientesService } from 'src/app/clientes.service';

@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.css']
})
export class ClientesListaComponent implements OnInit {

  clientes: Cliente[] = [];

  //injeção de dependência
  constructor(private service: ClientesService){
  }

  //callback
  ngOnInit(): void {
    this.service
    .getClientes()
    .subscribe( resposta => this.clientes = resposta);
  }
}
