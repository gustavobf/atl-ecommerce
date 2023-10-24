import { Component } from '@angular/core';
import { LoginService } from '../service/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  login!: string;
  senha!: string;

  constructor(private authService: LoginService, private router: Router) { }

  onSubmit() {
    this.authService.login({ login: this.login, senha: this.senha }).subscribe(
      success => {
        if (success) {
          this.router.navigate(['/produtos']);
        } else {
          // Lide com o erro ou informe ao usuário sobre a falha no login
        }
      },
      error => {
        // Lide com o erro
      }
    );

  }

}
