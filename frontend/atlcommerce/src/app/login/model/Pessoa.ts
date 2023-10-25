import { Usuario } from "./Usuario";

export class Pessoa {
  nome!: string;
  email!: string;
  usuario!: Usuario;

  constructor(nome: string, email: string, usuario: Usuario) {
    this.nome = nome;
    this.email = email;
    this.usuario = usuario;
  }

}

