import { Component } from '@angular/core';
import { LoginService } from '../service/login.service';
import { Router } from '@angular/router';
import { Usuario } from '../model/Usuario';

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

    const usuario = new Usuario(this.login, this.senha);

    this.authService.login(usuario).subscribe(
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
