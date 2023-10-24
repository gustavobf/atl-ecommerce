import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Pedido } from '../model/Pedido';
import { CarrinhoService } from './../service/carrinho.service';


@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.css']
})
export class CarrinhoComponent implements OnInit {

  pedido!: Pedido;

  constructor(private carrinhoService: CarrinhoService, private router: Router, private location: Location) { }

  ngOnInit(): void {
    this.carrinhoService.obterCarrinho().subscribe(data => {
      this.pedido = data;
    }, error => {
      console.error('Ocorreu um erro ao buscar os itens.', error);
    });
  }

  navigateToHistorico() {
    this.router.navigate(['/historicopedidos']);
  }

  finalizarPedido() {
    this.carrinhoService.finalizarPedido().subscribe(response => {
      window.location.reload();
    }, error => {
      console.error('Erro ao finalizar pedido:', error);
    });
  }

}
