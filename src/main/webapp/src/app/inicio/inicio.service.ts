import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { isNullOrUndefined } from 'util';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { MethodCall } from '@angular/compiler';
import { Item, Busqueda, CodigoParse } from './inicio';

@Injectable({ providedIn: 'root' })
export class InicioService {
	constructor(private _http: HttpClient, private _router: Router) { }

	estaLogueado() {
		var usuario = localStorage.getItem("usuario");
		if (isNullOrUndefined(usuario))
			this._router.navigate(["/iniciar-sesion"])

	}

	get_eventos() {
		var method = "concepto/evento"
		return this._http.get<Item[]>(environment.urlservices + method);
	}

	get_funciones() {
		var method = "concepto/funcion"
		return this._http.get<Item[]>(environment.urlservices + method);
	}

	get_zonas() {
		var method = "concepto/zona"
		return this._http.get<Item[]>(environment.urlservices + method);
	}

	get_configuracion() {
		var method = "concepto/configuracion"
		return this._http.get<any>(environment.urlservices + method);
	}

	enviar_busqueda(busqueda: Busqueda) {
		var method = "concepto/busqueda";
		return this._http.post<Item>(environment.urlservices + method, busqueda);
	}

	confirmar_codigo(codigo: number) {
		var method = "concepto/confirmar?codigo=" + codigo;
		return this._http.get<Item>(environment.urlservices + method);
	}

	guardar_configuracion(busqueda: Busqueda) {
		var method = "concepto/guardarConfiguracion";
		return this._http.post<any>(environment.urlservices + method, busqueda);
	}

	subir_archivo(tipo: string, file: File) {
		var method = "archivo/subir";
		var form = new FormData();
		form.append("tipo", tipo);
		form.append("file", file, file.name);
		return this._http.post<CodigoParse[]>(environment.urlservices + method, form);
	}
}
