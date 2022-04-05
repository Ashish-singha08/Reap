import { HttpClient, HttpHandler, HttpHeaders, HttpParams } from '@angular/common/http';
import { Token } from '@angular/compiler';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EndorseService{

  getAllEndorsements(){
    return [{name:"Ashish",class:"10th"},{name:"Singhal",class:"12th"}];
  }
}
