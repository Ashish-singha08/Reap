import {Component, OnInit, Inject, ViewChild} from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {FormBuilder, Validators, FormGroup, FormControl} from '@angular/forms';
import {ReplaySubject, Subject} from "rxjs";
import {MatSelect} from "@angular/material/select";
import {take, takeUntil} from "rxjs/operators";
import { ToastrService } from 'ngx-toastr';
import {EndorseService} from "../../services/endorse.service";
import {UserService} from "../../services/user.service";

interface Employee {
  id: string;
  name: string;
}
interface Tag{
  id : string;
  name : string;
}
@Component({
  selector: 'app-kanban-dialog',
  templateUrl: './endrose.component.html',
  styleUrls: ['./endrose.component.css']
})
export class EndroseComponent implements OnInit {

  title : string;
  form: FormGroup;
  message : string

  constructor(private endorseService : EndorseService, private userService : UserService,private toastrService: ToastrService,private dialogRef: MatDialogRef<EndroseComponent>, private fb: FormBuilder, @Inject(MAT_DIALOG_DATA) data:any){
    this.form = fb.group({
          title: [this.title, Validators.required]
      });
  }

  protected employees: Employee[] = [];

  protected tags: Tag[] = [];

  public employeeCtrl: FormControl = new FormControl();
  public employeeFilterCtrl: FormControl = new FormControl();
  public filteredEmployees: ReplaySubject<Employee[]> = new ReplaySubject(1);

  public tagCtrl: FormControl = new FormControl();
  public tagFilterCtrl: FormControl = new FormControl();
  public filteredTags: ReplaySubject<Tag[]> = new ReplaySubject(1);


  @ViewChild('singleSelect', { static: true }) singleSelect: MatSelect;

  protected _onDestroy = new Subject();


  selectedEmployee:Employee;
  coins:string;
  selectedTag:Tag;

  ngOnInit() {
    this.fetchTags();
    this.fetchUsers();
    this.employeeCtrl.setValue(this.employees[1]);
    this.filteredEmployees.next(this.employees.slice());
    this.tagCtrl.setValue(this.tags[1]);
    this.filteredTags.next(this.tags.slice());

    this.tagFilterCtrl.valueChanges.pipe(takeUntil(this._onDestroy)).subscribe(() => {
      this.filterTags();
    });
    this.employeeFilterCtrl.valueChanges
      .pipe(takeUntil(this._onDestroy))
      .subscribe(() => {
        this.filterEmployees();
      });
  }
  ngAfterViewInit() {
    this.setInitialValue();
  }

  /**
   * Write code on Method
   *
   * method logical code
   */
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

  /**
   * Write code on Method
   *
   * method logical code
   */
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

  protected filterTags() {
    if (!this.tags) {
      return;
    }

    let search = this.tagFilterCtrl.value;
    if (!search) {
      this.filteredTags.next(this.tags.slice());
      return;
    } else {
      search = search.toLowerCase();
    }

    this.filteredTags.next(
      this.tags.filter(tag => tag.name.toLowerCase().indexOf(search) > -1)
    );
  }

  close() {
    this.dialogRef.close();
  }
  public showSuccess(str:string): void {
    this.toastrService.success(str, 'Endorsed Successfully');
  }
  public showError(error:any): void {
    this.toastrService.error(error, 'Error!');
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
  fetchTags(){
    this.endorseService.getTags().subscribe(
      (response:any) => {
        console.log(response);
        for(let i = 0; i < response.length; i++){
          const tag : Tag ={ id:response[i].id.toString(),name:response[i].tag};
          //console.log(response[i][0]);

          this.tags.push(tag);
        }

      },
      (error:any) => {

        console.log(error.error.message);

      }
    );
  }
  save() {
    if(parseInt(this.coins)>parseInt(localStorage.getItem("coins")!)){
      this.showError("Insufficient Coins!!");
    }
    else{
      const endorse ={
        userId : this.selectedEmployee.id,
        message : this.message,
        coins :this.coins,
        tagId : this.selectedTag.id
      }
      console.log(endorse);
      this.endorseService.addEndorsement(endorse).subscribe(
        (response:any)=>{

          this.close();
          let coin = parseInt(localStorage.getItem("coins")!)-parseInt(this.coins);
          localStorage.setItem("coins",coin.toString());
          //window.location.href="/dashboard";
          this.showSuccess("Appreciation is the Currency Of Success!");
        },
        (error:any)=>{
          this.showError(error);
        }
      );
    }

  }
}
