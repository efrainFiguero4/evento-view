import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import {  Usuario } from './login';
import { Router } from '@angular/router';
import { isNullOrUndefined } from 'util';

@Injectable({ providedIn: 'root' })
export class LoginService {
	constructor(private httpClient: HttpClient, private _router: Router) { }

	loguearse(login: Usuario) {
		var method = "login/iniciarSesion";
		return this.httpClient.post<Usuario>(environment.urlservices + method, login);
	}


	estaLogueado() {
		var usuario = localStorage.getItem("usuario");
		if (isNullOrUndefined(usuario))
			this._router.navigate(["/iniciar-sesion"])
		else {
			return this._router.navigate(["/inicio"])
		}
	}
}
