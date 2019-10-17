import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { InicioService } from './inicio.service';
import { Usuario } from '../login/login';
import { Item, Busqueda } from './inicio';
import { Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged } from "rxjs/internal/operators";

declare var $: any;

@Component({
	selector: 'inicio-app',
	templateUrl: './inicio.component.html',
	styleUrls: ['./inicio.component.css']
})

export class InicioComponent {
	txtQueryChanged = new Subject<string>();

	constructor(private _inicioservice: InicioService) {
		this.txtQueryChanged
			.pipe(debounceTime(300))
			.subscribe(() => {
				this.buscar_codigo(this.busqueda);
			});
	}

	onFieldChange(event: any) {
		this.txtQueryChanged.next();
	}

	usuario = new Usuario();
	busqueda = new Busqueda();

	ngOnInit() {
		this._inicioservice.estaLogueado();
		this.usuario = JSON.parse(localStorage.getItem("usuario"));

		this.obtener_configuracion();

		if(this.usuario.usuario == "admin"){
			this.obtener_eventos();
			this.obtener_funciones();
			this.obtener_zonas();
		}

	}

	ls_funciones: Item[];
	ls_zonas: Item[];
	ls_eventos: Item[];
	configuracion: any;
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

	obtener_configuracion() {
		this._inicioservice.get_configuracion().subscribe(resp => {
		this.configuracion = resp;

		this.busqueda.evento = this.configuracion.evento.idEvento;
		this.busqueda.funcion = this.configuracion.funcion.idFuncion;
		this.busqueda.zona = this.configuracion.zona.idZona;
		})
	}

	buscar_codigo(busqueda: Busqueda) {

		this._inicioservice.enviar_busqueda(busqueda).subscribe(resp => {
			this.mensaje = resp;
			this.busqueda.numero = "";
		}, error => {
			error.error.errors.forEach(e => {
				this.mensaje.codigo = 0;
				this.busqueda.numero = "";
				this.mensaje.valor = e.defaultMessage;
			});
		})
	}

	guardar_configuracion(busqueda: Busqueda) {
		busqueda.numero = "0";
		this._inicioservice.guardar_configuracion(busqueda).subscribe(resp => {
			this.mensaje.codigo = 1;
			this.mensaje.valor = "Configuracion guardada";
		}, error => {
			error.error.errors.forEach(e => {
				this.mensaje.codigo = 0;
				this.busqueda.evento = "";
				this.busqueda.funcion = "";
				this.busqueda.evento = "";
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
