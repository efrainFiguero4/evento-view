import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { InicioService } from './inicio.service';
import { Usuario } from '../login/login';
import { Item, Busqueda } from './inicio';

@Component({
	selector: 'inicio-app',
	templateUrl: './inicio.component.html',
	styleUrls: ['./inicio.component.css']
})

export class InicioComponent {
	title = 'inicio';

	constructor(private _inicioservice: InicioService, private _router: Router) { }

	usuario = new Usuario();
	busqueda = new Busqueda();

	ngOnInit() {
		this._inicioservice.estaLogueado();
		this.usuario = JSON.parse(localStorage.getItem("usuario"));
		this.obtener_eventos();
		this.obtener_funciones();
		this.obtener_zonas();
	}

	ls_funciones: Item[];
	ls_zonas: Item[];
	ls_eventos: Item[];
	mensaje = new Item();

	obtener_eventos() {
		this._inicioservice.get_eventos().subscribe(resp => {
			this.ls_eventos = resp;
		})
	}

	obtener_funciones() {
		this._inicioservice.get_funciones().subscribe(resp => {
			this.ls_funciones = resp;
		})
	}

	obtener_zonas() {
		this._inicioservice.get_zonas().subscribe(resp => {
			this.ls_zonas = resp;
		})
	}

	buscar_codigo(busqueda: Busqueda) {
		this._inicioservice.enviar_busqueda(busqueda).subscribe(resp => {
			this.mensaje = resp;
		}, error => {
			error.error.errors.forEach(e => {
				this.mensaje.codigo = 0;
				this.mensaje.valor = e.defaultMessage;
			});
		})
	}
/*
	confirmar_codigo(codigo: number) {
		this._inicioservice.confirmar_codigo(codigo).subscribe(resp => {
			this.mensaje = resp;
		})
	}
*/
	cancelar() {
		this.mensaje = new Item();
	}

	salir() {
		localStorage.clear();
		this._inicioservice.estaLogueado();
	}
}
