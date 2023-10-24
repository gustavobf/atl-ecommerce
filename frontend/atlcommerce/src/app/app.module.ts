import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProdutosComponent } from './produtos/produtos/produtos.component';
import { LoginComponent } from './login/login/login.component';
import { TokenInterceptor } from './login/token.interceptor';
import { CarrinhoComponent } from './carrinho/carrinho/carrinho.component';
import { HistoricoPedidosComponent } from './historicoPedidos/historico-pedidos/historico-pedidos.component';

@NgModule({
  declarations: [
    AppComponent,
    ProdutosComponent,
    LoginComponent,
    CarrinhoComponent,
    HistoricoPedidosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
