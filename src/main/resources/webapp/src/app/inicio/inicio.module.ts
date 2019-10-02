import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { InicioComponent } from './inicio.component';
import { HttpClientModule } from '@angular/common/http';
import { InicioService } from './inicio.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ArchivoComponent } from '../archivo/archivo.component';

const routes: Routes = [
	{ path: '', component: InicioComponent },
	{ path: 'subir-archivo', component: ArchivoComponent }
]

@NgModule({
	declarations: [InicioComponent, ArchivoComponent],
	imports: [CommonModule, FormsModule, ReactiveFormsModule, RouterModule.forChild(routes), HttpClientModule],
	providers: [InicioService],
})

export class InicioModule { }
