import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import { RouterModule, Routes } from '@angular/router';
import { LoginService } from './login.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

const routes: Routes = [
	{ path: '', component: LoginComponent }
]

@NgModule({
	declarations: [LoginComponent],
	imports: [CommonModule, RouterModule.forChild(routes), HttpClientModule, FormsModule, ReactiveFormsModule],
	exports: [],
	providers: [LoginService],
})
export class LoginModule { }
