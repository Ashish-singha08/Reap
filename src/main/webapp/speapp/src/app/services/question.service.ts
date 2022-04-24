import { HttpClient, HttpHandler, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  url = 'http://localhost:5050';

  constructor(private httpCLient:HttpClient) { }

  getAllQuestionsUserAnswered(){
    let token = localStorage.getItem("token");
    let header = new HttpHeaders(
      {
        Authorization  : "Bearer " + token
      }
    )
    return this.httpCLient.get(`${this.url}/getAllQuestionsUserAnswered`,{'headers':header});
  }
  getAllQuestionUserAsked(){
    let token = localStorage.getItem("token");
    let header = new HttpHeaders(
      {
        Authorization  : "Bearer " + token
      }
    )
    return this.httpCLient.get(`${this.url}/getAllQuestionUserAsked`,{'headers':header});
  }
  getAllQuestionAskedToUser(){
    let token = localStorage.getItem("token");
    let header = new HttpHeaders(
      {
        Authorization  : "Bearer " + token
      }
    )
    return this.httpCLient.get(`${this.url}/getAllQuestionAskedToUser`,{'headers':header});
  }
  askQuestion(question:any){
    let token = localStorage.getItem("token");
    let header = new HttpHeaders(
      {
        Authorization  : "Bearer " + token
      }
    )
    return this.httpCLient.post(`${this.url}/ques/askQuestion`,question,{'headers':header});
  }
  updateQuestion(question:any){
    let token = localStorage.getItem("token");
    let header = new HttpHeaders(
      {
        Authorization  : "Bearer " + token
      }
    )
    return this.httpCLient.post(`${this.url}/updateQuestion`,question,{'headers':header});
  }
  answerQuestion(answer:any){
    let token = localStorage.getItem("token");
    let header = new HttpHeaders(
      {
        Authorization  : "Bearer " + token
      }
    )
    return this.httpCLient.post(`${this.url}/answerQuestion`,answer,{'headers':header});
  }
}
