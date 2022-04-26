import { HttpClient, HttpHandler, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StoreService {
  url = 'http://localhost:5050';
  constructor(private httpCLient:HttpClient) { }

  getAllItems(){
    let token = localStorage.getItem("token");
    let header = new HttpHeaders(
      {
        Authorization  : "Bearer " + token
      }
    )
    return this.httpCLient.get(`${this.url}/store/getAllItems`,{'headers':header});
  }
  placeAnOrder(order:any){
    let token = localStorage.getItem("token");
    let header = new HttpHeaders(
      {
        Authorization  : "Bearer " + token
      }
    )
    return this.httpCLient.post(`${this.url}/store/placeAnOrder`,order,{'headers':header});
  }
  getAllOrders(){
    let token = localStorage.getItem("token");
    let header = new HttpHeaders(
      {
        Authorization  : "Bearer " + token
      }
    )
    return this.httpCLient.get(`${this.url}/store/getAllOrders`,{'headers':header});
  }
}
