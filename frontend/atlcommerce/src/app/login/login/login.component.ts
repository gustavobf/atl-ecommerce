import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Toast } from 'bootstrap';
import { Pessoa } from '../model/Pessoa';
import { Usuario } from '../model/Usuario';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @ViewChild('toastFailedLogin', { static: true }) toastEl!: ElementRef<HTMLDivElement>;
  toastFailedLogin: Toast | null = null;

  loginSignin!: string;
  senhaSignin!: string;

  loginSignup!: string;
  senhaSignup!: string;
  nomeSignup!: string;
  emailSignup!: string;

  constructor(private authService: LoginService, private router: Router) { }

  ngOnInit(): void {
    this.toastFailedLogin = new Toast(this.toastEl.nativeElement, {});
  }

  onSubmitSignin() {
    const usuario = new Usuario(this.loginSignin, this.senhaSignin);

    this.authService.signIn(usuario).subscribe(
      success => {
        if (success) {
          this.router.navigate(['/produtos']);
        }
      }, error => {
        this.showToastFailed();
      });

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

  showToastFailed() {
    this.toastFailedLogin!.show();
  }

}
