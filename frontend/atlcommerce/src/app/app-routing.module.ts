import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login/login.component';
import { ProdutosComponent } from './produtos/produtos/produtos.component';
import { CarrinhoComponent } from './carrinho/carrinho/carrinho.component';
import { HistoricoPedidosComponent } from './historicoPedidos/historico-pedidos/historico-pedidos.component';

const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: "full" },
  { path: 'login', component: LoginComponent },
  { path: 'produtos', component: ProdutosComponent },
  { path: 'carrinho', component: CarrinhoComponent },
  { path: 'historicopedidos', component: HistoricoPedidosComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
