import { HttpClient, HttpHandler, HttpHeaders, HttpParams } from '@angular/common/http';
import { Token } from '@angular/compiler';
import { Injectable } from '@angular/core';
import {LoginService} from "./login.service";

@Injectable({
  providedIn: 'root'
})
export class EndorseService{
  constructor(private httpClient:HttpClient) { }
  url = 'http://localhost:5050';
  getAllEndorsements(){
    return [{name:"Ashish",class:"10th"},{name:"Singhal",class:"12th"}];
  }
  getAllUserEndorsements(){

  }
  addEndorsement(endorse:any){
    let token = localStorage.getItem("token");
    let header = new HttpHeaders(
      {
        Authorization  : "Bearer " + token
      }
    )
    return this.httpClient.post(`${this.url}/addEndorsement`,endorse,{'headers':header});
  }
  getTags(){
    let token = localStorage.getItem("token");
    let header = new HttpHeaders(
      {
        Authorization  : "Bearer " + token
      }
    )
    return this.httpClient.get(`${this.url}/getTags`,{'headers':header});
  }

}
