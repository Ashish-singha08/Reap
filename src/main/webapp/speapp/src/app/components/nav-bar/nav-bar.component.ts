import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';

import { LoginService } from 'src/app/services/login.service';
import {DialogComponent} from "../dialogbox/dialog.component";
import {EndroseComponent} from "../endroseform/endrose.component";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  public isLoggedIn = false;
  public coins = localStorage.getItem("coins");
  public fullName = localStorage.getItem("fullName");

  constructor(private loginService:LoginService,private dialog: MatDialog){

  }

  ngOnInit(): void {
    this.isLoggedIn = this.loginService.isLoggedIn();
  }

  logout(){
    this.loginService.logout();
    location.reload();
  }
  openEndorseDialog(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;

    this.dialog.open(EndroseComponent, dialogConfig)
  }
  openQuestionDialog(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data ={
      heading :"Ask a Question",
      showTypeBox : true,
      showIdField:true,
      question:[],
      buttonTitle :'Ask',
      forAskQuestion: true,
      forAnswerQuestion:false,
      forForwardQuestion:false,
      forBuyItem:false
    }

    this.dialog.open(DialogComponent,dialogConfig);
  }
}
