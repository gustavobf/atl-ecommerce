import { ProdutoCarrinho } from "./ProdutoCarrinho";

export class ItemPedido {
  idItem!: number;
  precoTotal!: number;
  quantidade!: number;
  produto!: ProdutoCarrinho;
}
