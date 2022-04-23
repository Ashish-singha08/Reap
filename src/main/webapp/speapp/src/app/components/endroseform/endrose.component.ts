import {Component, OnInit, Inject, ViewChild} from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {FormBuilder, Validators, FormGroup, FormControl} from '@angular/forms';
import {ReplaySubject, Subject} from "rxjs";
import {MatSelect} from "@angular/material/select";
import {take, takeUntil} from "rxjs/operators";
import { ToastrService } from 'ngx-toastr';

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
  // constructor(
  //   // private fb: FormBuilder,
  //   // private dialogRef: MatDialogRef<EndroseComponent>,
  //   // @Inject(MAT_DIALOG_DATA) data,
  //   // //private kanbanService: KanbanService) {
  //   //
  //   //   this.form = fb.group({
  //   //     title: [this.title, Validators.required]
  //   // });
  //   }
  constructor(private toastrService: ToastrService,private dialogRef: MatDialogRef<EndroseComponent>, private fb: FormBuilder, @Inject(MAT_DIALOG_DATA) data:any){
    this.form = fb.group({
          title: [this.title, Validators.required]
      });
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

  protected tags: Tag[] = [
    {id : '1', name:'Saviour'},
    {id : '2', name :'helper'},
    {id : '3', name :'Solver'},
    {id : '4', name:'Combatter'}
  ];

  public employeeCtrl: FormControl = new FormControl();
  public employeeFilterCtrl: FormControl = new FormControl();
  public filteredEmployees: ReplaySubject<Employee[]> = new ReplaySubject(1);

  public tagCtrl: FormControl = new FormControl();
  public tagFilterCtrl: FormControl = new FormControl();
  public filteredTags: ReplaySubject<Tag[]> = new ReplaySubject(1);


  @ViewChild('singleSelect', { static: true }) singleSelect: MatSelect;

  protected _onDestroy = new Subject();


  selectedEmployee ={

  }

  selectedTag={

  }
  ngOnInit() {
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
  public showSuccess(): void {
    this.toastrService.success('Appreciation is the Currency Of Success!', 'Endorsed Successfully');
  }
  public showError(): void {
    this.toastrService.error('Message Error!', 'Title Error!');
  }
  save() {
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
    console.log(this.selectedTag);
    console.log(this.message);
  }

}
