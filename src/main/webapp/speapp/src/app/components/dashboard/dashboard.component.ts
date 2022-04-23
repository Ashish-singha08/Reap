import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';

import {MatTabChangeEvent} from "@angular/material/tabs";
import { EndorseService} from "src/app/services/endorse.service"
interface Question{
  text: string,
  AskedBy: string,
  AskedOn :string,
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

  public questions: Question[] = [
    {text: 'what are u', AskedBy:"Ashish Singhal" , AskedOn:"19th may"},
    {text: 'what are u', AskedBy:"Ashish Singhal" , AskedOn:"19th may"},
    {text: 'what are u', AskedBy:"Ashish Singhal" , AskedOn:"19th may"},

  ];

  constructor(private endorseService:EndorseService) { }

   endorsements :any;

  ngOnInit(): void {
      this.getAllEndorsements();

  }
  getAllEndorsements(){
    this.endorsements= [{name:"Ash",class:"10th"},{name:"Singhal",class:"12th"},{name:"Ashish",class:"10th"},{name:"Singhal",class:"12th"}];
    return this.endorsements;
  }
  onChange(event: MatTabChangeEvent) {
    const tab = event.tab.textLabel;
    console.log(tab);
    if(tab==this.tab1)
    {
      this.getAllEndorsements();
    }
  }



  /*
  getAllEndorsements(){
    this.endorseService.getAllEndorsements().subscribe(
      (response:any) => {
        console.log(response);
        this.Record = response;
        if(this.Record.length == 0)
        {
          this.isPatientsFound = false;
        }
        else
        {
          this.isPatientsFound = true;
        }

      },
      (error:any) => {
        console.log(error);
      }
    );
  }
  */

   save(s:any){
     console.log(s);
   }

  onSubmit(){

  }
}
