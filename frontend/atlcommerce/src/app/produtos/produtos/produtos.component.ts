import { Produto } from './../model/Produto';
import { Component, OnInit } from '@angular/core';
import { ProdutosService } from '../service/produtos.service';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.css']
})
export class ProdutosComponent implements OnInit  {

  produtos: Produto[] = [];

  constructor(private produtoService: ProdutosService) {}

  ngOnInit(): void {
    this.produtoService.fetchData().subscribe(data => {
      this.produtos = data;
      console.log(this.produtos);
    }, error => {
      console.error('Ocorreu um erro ao buscar os itens.', error);
    });
  }

}
