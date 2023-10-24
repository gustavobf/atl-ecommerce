import { Injectable } from '@angular/core';
import { API_ENDPOINT } from 'src/app.constants';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiUrl = API_ENDPOINT + '/api/usuario/login';

  constructor(private http: HttpClient) {}

  login(credentials: {login: string, senha: string}) {
    return this.http.post<{token: string}>(this.apiUrl, credentials)
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
        })
      );
  }
}
