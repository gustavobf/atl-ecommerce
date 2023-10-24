import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_ENDPOINT } from 'src/app.constants';
import { Produto } from '../model/Produto';

@Injectable({
  providedIn: 'root'
})
export class ProdutosService {

  constructor(private http: HttpClient) {}

  fetchData() {
    return this.http.get<Produto[]>(API_ENDPOINT + '/api/produto');
  }

}
