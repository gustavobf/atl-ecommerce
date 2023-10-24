import { ItemPedido } from "./ItemPedido";

export class Pedido {
  idPedido!: number;
  dataPedido!: Date | null;
  itemPedido!: ItemPedido[];
}
