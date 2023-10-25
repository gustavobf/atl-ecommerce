import { Component } from '@angular/core';
import { LoginService } from '../service/login.service';
import { Router } from '@angular/router';
import { Usuario } from '../model/Usuario';
import { Pessoa } from '../model/Pessoa';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginSignin!: string;
  senhaSignin!: string;

  loginSignup!: string;
  senhaSignup!: string;
  nomeSignup!: string;
  emailSignup!: string;

  constructor(private authService: LoginService, private router: Router) { }

  onSubmitSignin() {

    const usuario = new Usuario(this.loginSignin, this.senhaSignin);

    this.authService.signIn(usuario).subscribe(
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

  onSubmitSignup() {

    const usuario = new Usuario(this.loginSignup, this.senhaSignup);
    const pessoa = new Pessoa(this.nomeSignup, this.emailSignup, usuario);

    this.authService.signUp(pessoa).subscribe(
      success => {
        if (success) {
          this.router.navigate(['/login']);
        }
      },
      error => {
      }
    );

  }

}
