import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './public/guards/auth.guard';


const routes: Routes = [
	{ path: '', redirectTo: 'iniciar-sesion', pathMatch: 'full' },
	{ path: 'iniciar-sesion', loadChildren: './login/login.module#LoginModule' },
	{ path: 'inicio', loadChildren: './inicio/inicio.module#InicioModule', canActivate: [AuthGuard] },
]

@NgModule({
	imports: [RouterModule.forRoot(routes, { useHash: true })],
	exports: [RouterModule]
})
export class AppRoutingModule { }
