import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';

import {MatTabChangeEvent} from "@angular/material/tabs";
import { EndorseService} from "src/app/services/endorse.service"
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {DialogComponent} from "../dialogbox/dialog.component";
import { ToastrService } from 'ngx-toastr';
import {QuestionService} from "../../services/question.service";



interface Question{
  id:string,
  text: string,
  AskedBy: string,
  AskedOn :string,
  answer:string
}
interface Endorsement{
  tag:string,
  imageUrl:string,
  coins:string,
  giverName:string,
  time:string,
  message:string,
}
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})


export class DashboardComponent implements OnInit {

   tab1 = "Received";
   tab2 = "Sent";
   tab3 = "Question Asked To You";
   tab4 = "Questions You Have Answered";

  questions: Question[] = [];
  endorsements : Endorsement[]=[];
  userEndorsements : Endorsement[]=[];
  answers:Question[]=[];

  constructor( private questionService: QuestionService, private toastrService:ToastrService,
               private endorseService:EndorseService, private dialog: MatDialog) { }


  ngOnInit(): void {
      this.getAllUserEndorsements();
  }

  onChange(event: MatTabChangeEvent) {
    const tab = event.tab.textLabel;
    console.log(tab);
    if(tab==this.tab1)
    {
      this.getAllUserEndorsements();
    }
    else if(tab==this.tab2){
      this.getAllEndorsements();
    }
    else if(tab==this.tab3){
      this.getAllQuestionAskedToUser();
    }
    else if(tab==this.tab4){
      this.getAllQuestionsUserAnswered();
    }
  }

  public showInfo(msg:any): void {
    this.toastrService.info(msg, 'Info!');
  }
  public showError(msg:any): void {
    this.toastrService.error(msg, 'Error!');
  }
  public showSuccess(msg:any):void{
    this.toastrService.success(msg,'Success!!')
  }


   save(s:any){
     console.log(s);
   }
   answer(question:Question){
     const dialogConfig = new MatDialogConfig();
     dialogConfig.autoFocus = true;
     dialogConfig.data ={
       heading :"Answer This Question",
       showTypeBox : true,
       showIdField:  false,
       question : question,
       buttonTitle : 'Answer',
       forAskQuestion: false,
       forAnswerQuestion:true,
       forForwardQuestion:false,
       forBuyItem:false
     }

     this.dialog.open(DialogComponent,dialogConfig);
   }
   forward(question:any){
     const dialogConfig = new MatDialogConfig();
     dialogConfig.autoFocus = true;
     dialogConfig.data ={
       heading :"Forward this Question",
       showTypeBox : false,
       showIdField : true,
       question :question,
       buttonTitle : 'Forward',
       forAskQuestion: false,
       forAnswerQuestion:false,
       forForwardQuestion:true,
       forBuyItem:false
     }

     this.dialog.open(DialogComponent,dialogConfig);
   }
  getAllQuestionsUserAnswered(){
    this.questionService.getAllQuestionsUserAnswered().subscribe(
      (response:any) =>{
        if(response.length==0) this.showInfo("No Answered Question For you!")
        for(let i=0;i<response.length;i++){
          this.answers =[];
          const ans: Question ={text:response[i][0],AskedBy:response[i][1]
            ,AskedOn:response[i][2].substr(0,10),id:response[i][3],answer:response[i][4]};
          this.answers.push(ans);
        }

      },
      (error:any) => {
        console.log(error);
      }
    );
  }
  getAllQuestionAskedToUser(){
    this.questionService.getAllQuestionAskedToUser().subscribe(
      (response:any) =>{
        if(response.length==0) this.showInfo("No new Question For you!")
        this.questions =[];
        for(let i=0;i<response.length;i++){

          const ques: Question ={text:response[i][0],AskedBy:response[i][1]
            ,AskedOn:response[i][2].substr(0,10),id:response[i][3],answer:response[i][4]};
          this.questions.push(ques);
        }

      },
      (error:any) => {
        console.log(error);
     }
    );
  }
  getAllEndorsements(){
    this.endorseService.getAllEndorsements().subscribe(
      (response:any) => {
        this.endorsements=[];

        if(response.length==0) this.showInfo("You have not endorse anyone yet!!")
        for(let i=0;i<response.length;i++){
          const en :Endorsement ={tag:response[i][0],imageUrl:response[i][4],coins:response[i][5],
            giverName:response[i][3],time:response[i][2].substr(0,10),message:response[i][1]};
          this.endorsements.push(en);
        }
      },
      (error:any) => {
        this.showError("Session Expired: Login Again!");
      }
    );
  }
  getAllUserEndorsements(){
    this.endorseService.getAllUserEndorsements().subscribe(
      (response:any) => {
        this.userEndorsements=[];
        if(response.length==0) this.showInfo("You have not received any endorsement yet!!")
        for(let i=0;i<response.length;i++){
          const en :Endorsement ={tag:response[i][0],imageUrl:response[i][4],coins:response[i][5],
            giverName:response[i][3],time:response[i][2].substr(0,10),message:response[i][1]};
          this.userEndorsements.push(en);
        }
      },
      (error:any) => {
        this.showError("Session Expired: Login Again!");
      }
    );
  }
  onSubmit(){

  }
}
