import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../login/login';
import { InicioService } from '../inicio/inicio.service';
import { Busqueda, Item, CodigoParse } from '../inicio/inicio';

@Component({
	selector: 'archivo-app',
	templateUrl: './archivo.component.html',
	styleUrls: ['./archivo.component.css']
})

export class ArchivoComponent {
	title = 'inicio';

	constructor(private _inicioservice: InicioService, private _router: Router) { }

	usuario = new Usuario();
	codigo_archivo: string = '';
	mensaje = new Item();
	ls_noprocesados: any[];
	files: FileList;

	ls_tipo = [
		{ codigo: "CONFIGURACION" },
		{ codigo: "CODIGOS" },
	]

	ngOnInit() {
		this.usuario = JSON.parse(localStorage.getItem("usuario"));
	}

	seleccionar_archivo(files: FileList) {
		this.files = files; console.log(this.files);
	}

	subir_archivo() {
		if (this.codigo_archivo != '' && this.files.length)
			this._inicioservice.subir_archivo(this.codigo_archivo, this.files[0]).subscribe(resp => {
				this.ls_noprocesados = resp.map(cod => cod.numero);
				this.mensaje.codigo = 1;
				this.mensaje.valor = "Se proceso correctamente"
			}, error => {
				this.mensaje.codigo = 0;
				this.mensaje.valor = "Error al procesar"
			})
	}

	salir() {
		localStorage.clear();
		this._inicioservice.estaLogueado();
	}
}
