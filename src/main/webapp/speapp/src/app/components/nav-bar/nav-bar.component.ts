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
    dialogConfig.data={
      id :"1",
      value : "ashu"
    };

    this.dialog.open(DialogComponent,dialogConfig)
  }
}
