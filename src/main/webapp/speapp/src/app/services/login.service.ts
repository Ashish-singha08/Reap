import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  credentials =  {
    "username":'',
    "password":''
  }

  url = "http://localhost:5050";

  constructor(private http:HttpClient) { }

  generateToken(credentials:any){
    return this.http.post(`${this.url}/token`, credentials);
  }

  loginUser(token:any,coins:any,fullName:any){
     localStorage.setItem("token", token);
     localStorage.setItem("coins",coins);
     localStorage.setItem("fullName",fullName);
     return true;
  }

  isLoggedIn(){
    let token = localStorage.getItem("token");

    if(token == undefined || token === '' || token == null){
      return false;
    }

    return true;
  }

  logout(){
    localStorage.removeItem("token");
    localStorage.removeItem("coins");
    localStorage.removeItem("fullName");
    return true;
  }

  getToken(){
     return localStorage.getItem("token");
  }
}
