import {Component, OnInit, Inject, ViewChild} from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {FormBuilder, Validators, FormGroup, FormControl} from '@angular/forms';
import {take, takeUntil} from "rxjs/operators";
import {MatSelect} from "@angular/material/select";
import {ReplaySubject, Subject} from "rxjs";
import {ToastrService} from "ngx-toastr";
import {QuestionService} from "../../services/question.service";
import {UserService} from "../../services/user.service";
import {StoreService} from "../../services/store.service";

interface Employee {
  id: string;
  name: string;
}
interface Question{
  id:string,
  text: string,
  AskedBy: string,
  AskedOn :string,
  answer:string
}
@Component({
  selector: 'app-reap-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {
  title : string;
  form: FormGroup;
  askQues:string;
  //Dialog Config Params
  heading : string;
  showIdField :boolean;
  showTypeBox: boolean;
  question :Question;
  buttonTitle : string;
  forAskQuestion: boolean;
  forAnswerQuestion:boolean;
  forForwardQuestion:boolean;
  forBuyItem:boolean;

  order:any;
  protected employees:Employee[] =[];

  @ViewChild('singleSelect', { static: true }) singleSelect: MatSelect;

  protected _onDestroy = new Subject();

  constructor(private storeService :StoreService,private userService : UserService,private questionService: QuestionService
   ,private toastrService: ToastrService,private dialogRef: MatDialogRef<DialogComponent>, private fb: FormBuilder, @Inject(MAT_DIALOG_DATA) data:any){
    this.form = fb.group({
      title: [this.title, Validators.required]
    });

   this.heading = data.heading;
   this.showIdField = data.showIdField;
   this.showTypeBox = data.showTypeBox;
   this.buttonTitle =data.buttonTitle;
   this.forAnswerQuestion = data.forAnswerQuestion;
   this.forForwardQuestion = data.forForwardQuestion;
   this.forAskQuestion = data.forAskQuestion;
   this.forBuyItem = data.forBuyItem;
   if(this.forBuyItem)
     this.order =data.question;
   else this.question = data.question;
  }


  selectedEmployee:Employee;

  public employeeCtrl: FormControl = new FormControl();
  public employeeFilterCtrl: FormControl = new FormControl();
  public filteredEmployees: ReplaySubject<Employee[]> = new ReplaySubject(1);


  ngOnInit() {
    this.fetchUsers();
    this.employeeCtrl.setValue(this.employees[1]);
    this.filteredEmployees.next(this.employees.slice());

    this.employeeFilterCtrl.valueChanges
      .pipe(takeUntil(this._onDestroy))
      .subscribe(() => {
        this.filterEmployees();
      });
  }
  ngAfterViewInit() {
    this.setInitialValue();
  }
  ngOnDestroy() {
    this._onDestroy.next(true);
    this._onDestroy.complete();
  }
  protected setInitialValue() {
    this.filteredEmployees
      .pipe(take(1), takeUntil(this._onDestroy))
      .subscribe(() => {
        this.singleSelect.compareWith = (a: Employee, b: Employee) => a && b && a.id === b.id;
      });
  }
  protected filterEmployees() {
    if (!this.employees) {
      return;
    }

    let search = this.employeeFilterCtrl.value;
    if (!search) {
      this.filteredEmployees.next(this.employees.slice());
      return;
    } else {
      search = search.toLowerCase();
    }

    this.filteredEmployees.next(
      this.employees.filter(employee => employee.name.toLowerCase().indexOf(search) > -1)
    );
  }
  close() {
    this.dialogRef.close();
  }
  public showSuccess(str:any): void {
    this.toastrService.success(str, 'Success!');
  }
  public showError(str:any): void {
    this.toastrService.error(str, 'Error!');
  }
  public showInfo(str:any):void{
    this.toastrService.info(str,"Info!!")
  }
  askQuestion(){
   let newQues ={ Question : this.askQues,answerId:this.selectedEmployee.id};
   this.questionService.askQuestion(newQues).subscribe(
     (response:any) => {
       console.log(response);

       this.close();

       this.showSuccess('Successfully Created a new Question!!');
     },
     (error:any) => {

       console.log(error);
       this.showError("Error While Creating a Question!");
     }
   );
  }
  updateQuestion(){
    let newQues ={ QuestionId : this.question.id,ForwardedToUserId:this.selectedEmployee.id};
    this.questionService.updateQuestion(newQues).subscribe(
      (response:any) => {
        console.log(response);

        this.close();
        //window.location.href="/dashboard";
        this.showSuccess('Successfully forwarded this question!');
      },
      (error:any) => {

        console.log(error);
        this.showError("Error while forwarding this Question!");
      }
    );
  }
  answerQuestion(){
    let newQues ={ QuestionId : this.question.id,Answer:this.askQues};
    this.questionService.answerQuestion(newQues).subscribe(
      (response:any) => {
        console.log(response);

        this.close();
        let coin = parseInt(localStorage.getItem("coins")!)+20;
        localStorage.setItem("coins",coin.toString());
        //window.location.href="/dashboard";
        this.showSuccess('Successfully Answered this question! Earned 20 Coins :) ');
      },
      (error:any) => {

        console.log(error);
        this.showError("Error While answering Question!");
      }
    );
  }
  placeOrder(){
    if(parseInt(this.order.coins)>parseInt(localStorage.getItem("coins")!)){
      this.showError("Insufficient Coins!!");
    }
    else{
      const item = {itemId:this.order.id.toString(),coins:this.order.coins.toString()};
      this.storeService.placeAnOrder(item).subscribe(
        (response:any)=>{
          this.showSuccess("Order Placed Successfully!!");
          let coin = parseInt(localStorage.getItem("coins")!)-parseInt(this.order.coins);
          localStorage.setItem("coins",coin.toString());
          this.close();
        },
        (error:any)=>{
          this.showError("error while placing Order");
        }
      );
    }


  }
  save() {
    if(this.forAskQuestion){
      this.askQuestion();
    }
    else if(this.forForwardQuestion){
      this.updateQuestion();
    }
    else if(this.forAnswerQuestion){
      this.answerQuestion();
    }
    else if(this.forBuyItem){
      this.placeOrder();
    }

  }

  fetchUsers(){
    this.userService.getUsers().subscribe(
      (response:any) => {
        for(let i = 0; i < response.length; i++){
          const emp : Employee ={ id:response[i][0].toString(),name:response[i][1] +" "+  response[i][2]};
          //console.log(response[i][0]);

          this.employees.push(emp);
        }

      },
      (error:any) => {

        console.log(error.error.message);

      }
    );
  }

}
