import { Component } from '@angular/core';
import { AuthGuard } from './public/guards/auth.guard';
import { Router } from '@angular/router';
import { isNullOrUndefined } from 'util';

@Component({
	selector: 'app-root',
	template: '<router-outlet>'
})

export class AppComponent {
	title = 'webapp';

	constructor(private _router: Router) { }

	ngOnInit() {
		let usuario = localStorage.getItem("usuario");

		if (isNullOrUndefined(usuario))
			this._router.navigate(["/iniciar-sesion"]);
		else {
			//return this._router.navigate(["/inicio"]);
		}
	}
}
