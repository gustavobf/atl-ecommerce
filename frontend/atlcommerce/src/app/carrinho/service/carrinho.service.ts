import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_ENDPOINT } from 'src/app.constants';
import { Pedido } from '../model/Pedido';

@Injectable({
  providedIn: 'root'
})
export class CarrinhoService {

  constructor(private http: HttpClient) {}

  obterCarrinho() {
    return this.http.get<Pedido>(API_ENDPOINT + '/api/itempedido/obterpedidos');
  }

  finalizarPedido() {
    return this.http.post(API_ENDPOINT + '/api/pedido/finalizar', {});
  }

  obterHistorico(){
    return this.http.get<Pedido[]>(API_ENDPOINT + '/api/pedido/obterhistorico');
  }

}
