import {Component, OnInit, Inject, ViewChild} from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {FormBuilder, Validators, FormGroup, FormControl} from '@angular/forms';
import {take, takeUntil} from "rxjs/operators";
import {MatSelect} from "@angular/material/select";
import {ReplaySubject, Subject} from "rxjs";
import {ToastrService} from "ngx-toastr";
interface Employee {
  id: string;
  name: string;
}

@Component({
  selector: 'app-reap-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {
  title : string;
  question : string;
  form: FormGroup;
  askedOn :string;
  @ViewChild('singleSelect', { static: true }) singleSelect: MatSelect;

  protected _onDestroy = new Subject();

  constructor(private toastrService: ToastrService,private dialogRef: MatDialogRef<DialogComponent>, private fb: FormBuilder, @Inject(MAT_DIALOG_DATA) data:any){
    this.form = fb.group({
      title: [this.title, Validators.required]
    });
    this.askedOn = data.value;
  }

  protected employees: Employee[] = [
    {id: '1', name: 'ItSolutionStuff.com'},
    {id: '2', name: 'HDTuto.com'},
    {id: '3', name: 'Nicesnippets.com'},
    {id: '4', name: 'Google.com'},
    {id: '5', name: 'laravel.com'},
    {id: '6', name: 'npmjs.com'},
    {id: '7', name: 'HEEDTuto.com'},
    {id: '8', name: 'ENicesnippets.com'},
    {id: '9', name: 'VGoogle.com'},
    {id: '10', name: 'Alaravel.com'},
    {id: '11', name: 'nBpmjs.com'},
  ];
  selectedEmployee ={

  }
  public employeeCtrl: FormControl = new FormControl();
  public employeeFilterCtrl: FormControl = new FormControl();
  public filteredEmployees: ReplaySubject<Employee[]> = new ReplaySubject(1);


  ngOnInit() {

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
  public showSuccess(): void {
    this.toastrService.success('Appreciation is the Currency Of Success!', 'Endorsed Successfully');
  }
  public showError(): void {
    this.toastrService.error('Message Error!', 'Title Error!');
  }
  save() {
    console.log(this.askedOn);
    this.showSuccess();
    this.close();
    //this.title = this.form.get('title').value;
    // if (this.title) {
    //   this.kanbanService.saveNewKanban(this.title).subscribe(
    //
    //     response => {
    //       console.log(response)
    //     }
    //   )
    // }
    // this.dialogRef.close();
    // window.location.reload();
    console.log(this.selectedEmployee);
    console.log(this.question);
  }
}
