export interface Produto {
  id: number;
  imagem: string;
  nome: string;
  preco: number;
  descricao: string;
  categoria: Categoria;
}

export interface Categoria {
  id: number;
  nome: string;
}
