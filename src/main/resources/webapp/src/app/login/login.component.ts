import { Component } from '@angular/core';
import { LoginService } from './login.service';
import { Login, Usuario } from './login';
import { Router } from '@angular/router';
import { AuthGuard } from '../public/guards/auth.guard';

@Component({
  selector: 'login-app',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  title = 'login';

  constructor(private _loginservice: LoginService, private _router: Router) {
  }

  usuario = new Usuario();
  ngOnInit() {
    this.usuario.usuario = "admin";
    this.usuario.password = "admin";
    this._loginservice.estaLogueado();
  }

  login(login: Login) {
    this._loginservice.loguearse(login).subscribe(resp => {
      localStorage.setItem("usuario", JSON.stringify(resp));
      this._router.navigate(["/inicio"])
    }, error => {
      alert("credenciales incorrectas")
    })
  }
}
