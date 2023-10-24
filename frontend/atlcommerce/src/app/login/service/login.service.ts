import { Injectable } from '@angular/core';
import { API_ENDPOINT } from 'src/app.constants';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Usuario } from '../model/Usuario';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private _currentUser: Usuario | null = null;

  private apiUrl = API_ENDPOINT + '/api/usuario/login';

  constructor(private http: HttpClient) { }

  login(usuario: Usuario) {
    return this.http.post<{ token: string }>(this.apiUrl, usuario)
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
          this.setCurrentUser(usuario);
        })
      );
  }

  getCurrentUser(): Usuario | null {
    return this._currentUser;
  }

  setCurrentUser(usuario: Usuario): void {
    this._currentUser = usuario;
  }

}
