import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';
import { isNullOrUndefined } from 'util';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor() { }

  canActivate() {
    let usuario = localStorage.getItem("usuario");
    if (!isNullOrUndefined(usuario))
      return true;
    else {
      return false;
    }
  }
}
