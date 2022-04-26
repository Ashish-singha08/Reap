import { Component, OnInit } from '@angular/core';
import {StoreService} from "../../services/store.service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {DialogComponent} from "../dialogbox/dialog.component";
import {ToastrService} from "ngx-toastr";

interface Item{
  coins :string,
  imageUrl:string,
  id:string,
  name:string
}
@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})


export class StoreComponent implements OnInit {



  constructor( private toastrService:ToastrService, private storeService :StoreService,private dialog: MatDialog) { }
  items:Item[]=[];
  ngOnInit(): void {
      this.getAllItems();
  }
  public showError(str:any): void {
    this.toastrService.error(str, 'Title Error!');
  }
  getAllItems(){
    this.storeService.getAllItems().subscribe(
      (response:any) => {
        this.items =[];
        for(let i=0;i<response.length;i++){
           const item :Item = {coins:response[i].coinsRequired,id:response[i].id,name:response[i].name
           ,imageUrl:response[i].imageUrl};
           this.items.push(item);
        }
      },
      (error:any) => {
        this.showError("Session Expired! Please Login again")
      }
    );
  }
  buy(item:any){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      heading: "Please Confirm to buy "+item.name+ " Worth "+item.coins+" coins?",
      showTypeBox: false,
      showIdField: false,
      question: item,
      buttonTitle: 'Confirm',
      forAskQuestion: false,
      forAnswerQuestion: false,
      forForwardQuestion: false,
      forBuyItem: true
    }
    this.dialog.open(DialogComponent,dialogConfig);
  }

}
