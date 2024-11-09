import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }
  savetoken(token:string):void{
    localStorage.setItem("access_token",token)
  }
  isLoged():boolean{
    const token = localStorage.getItem('access_token');
    return !!token;
  }
}
