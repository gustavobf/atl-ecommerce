import { Injectable } from '@angular/core';
import { API_ENDPOINT } from 'src/app.constants';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Usuario } from '../model/Usuario';
import { Pessoa } from '../model/Pessoa';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private _currentUser!: Usuario;

  private apiUrlSignIn = API_ENDPOINT + '/api/usuario/login';
  private apiUrlSignUp = API_ENDPOINT + '/api/usuario/novo';

  constructor(private http: HttpClient) { }

  signIn(usuario: Usuario) {
    return this.http.post<{ token: string }>(this.apiUrlSignIn, usuario)
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
          this.setCurrentUser(usuario);
        })
      );
  }

  signUp(pessoa: Pessoa) {
    return this.http.post(this.apiUrlSignUp, pessoa);
  }

  getCurrentUser(): Usuario | null {
    return this._currentUser;
  }

  setCurrentUser(usuario: Usuario): void {
    this._currentUser = usuario;
  }

}
