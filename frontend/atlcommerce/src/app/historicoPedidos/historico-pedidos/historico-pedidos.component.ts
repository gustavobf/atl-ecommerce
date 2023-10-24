import { Component, OnInit } from '@angular/core';
import { Pedido } from 'src/app/carrinho/model/Pedido';
import { CarrinhoService } from 'src/app/carrinho/service/carrinho.service';

@Component({
  selector: 'app-historico-pedidos',
  templateUrl: './historico-pedidos.component.html',
  styleUrls: ['./historico-pedidos.component.css']
})
export class HistoricoPedidosComponent implements OnInit {

  pedidos!: Pedido[];

  constructor(private carrinhoService: CarrinhoService) { }

  ngOnInit(): void {
    this.carrinhoService.obterHistorico().subscribe(data => {
      this.pedidos = data;
    }, error => {
      console.error('Ocorreu um erro ao buscar os itens.', error);
    });
  }

}
