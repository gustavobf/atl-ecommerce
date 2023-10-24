import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/login/service/login.service';
import { ProdutosService } from '../service/produtos.service';
import { Produto } from './../model/Produto';
import { Toast } from 'bootstrap';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.css']
})
export class ProdutosComponent implements OnInit {

  produtos: Produto[] = [];
  openedModalId: number | null = null;
  quantidade!: number;

  @ViewChild('toastSucessProduto', { static: true }) toastEl!: ElementRef<HTMLDivElement>;
  toast: Toast | null = null;

  @ViewChild('toastFailedProduto', { static: true }) toastEl2!: ElementRef<HTMLDivElement>;
  toastFailed: Toast | null = null;

  @ViewChild('toastFailedGetProduto', { static: true }) toastEl3!: ElementRef<HTMLDivElement>;
  toastFailedGet: Toast | null = null;


  constructor(private produtoService: ProdutosService, public authService: LoginService, private router: Router) { }

  ngOnInit(): void {
    this.toast = new Toast(this.toastEl.nativeElement, {});
    this.toastFailed = new Toast(this.toastEl2.nativeElement, {});
    this.toastFailedGet = new Toast(this.toastEl3.nativeElement, {});
    this.produtoService.fetchData().subscribe(data => {
      this.produtos = data;
    }, error => {
      this.showToastFailedGet();
    });

  }

  showToastSucess() {
    this.toast!.show();
  }

  showToastFailed() {
    this.toastFailed!.show();
  }

  showToastFailedGet() {
    this.toastFailedGet!.show();
  }

  openModal(productId: number): void {
    this.openedModalId = productId;
  }

  closeModal(): void {
    this.openedModalId = null;
  }

  adicionarAoPedido(produtoId: number) {
    this.produtoService.adicionarProduto(produtoId, this.quantidade).subscribe(response => {
      this.showToastSucess();
    }, error => {
      this.showToastFailed();
    });
  }

  navigateToCarrinho() {
    this.router.navigate(['/carrinho']);
  }

}
