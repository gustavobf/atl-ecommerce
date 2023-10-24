import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_ENDPOINT } from 'src/app.constants';
import { Produto } from '../model/Produto';

@Injectable({
  providedIn: 'root'
})
export class ProdutosService {

  private apiUrl = API_ENDPOINT + '/api/itempedido/adicionar/';

  constructor(private http: HttpClient) {}

  fetchData() {
    return this.http.get<Produto[]>(API_ENDPOINT + '/api/produto');
  }

  adicionarProduto(produtoId: number, quantidade: number) {
    const url = `${this.apiUrl}${produtoId}`;
    const params = new HttpParams().set('quantidade', quantidade.toString());
    return this.http.post(url, {}, { params: params });
  }

}
