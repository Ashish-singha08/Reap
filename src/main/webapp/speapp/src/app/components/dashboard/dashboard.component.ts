import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatSelect } from '@angular/material/select';
import { ReplaySubject, Subject } from 'rxjs';
import { take, takeUntil } from 'rxjs/operators';
import {MatTabChangeEvent} from "@angular/material/tabs";
import { EndorseService} from "src/app/services/endorse.service"
interface Website {
  id: string;
  name: string;
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})


export class DashboardComponent implements OnInit {
   tab1 = "Received";
   tab2 = "Sent";
   tab3="Endorse Someone";
  Record:any;
  search = {
    option : '',
    value : '',
  };

  protected websites: Website[] = [
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

  public websiteCtrl: FormControl = new FormControl();
  public websiteFilterCtrl: FormControl = new FormControl();
  public filteredWebsites: ReplaySubject<Website[]> = new ReplaySubject(1);


  @ViewChild('singleSelect', { static: true }) singleSelect: MatSelect;

  protected _onDestroy = new Subject();

  isPatientsFound = true;
  endorsements :any;

  testWebsite ={
    name :''
  }
  constructor(private endorseService:EndorseService) { }



  ngOnInit(): void {
      this.getAllEndorsements();
    this.websiteCtrl.setValue(this.websites[1]);
    this.filteredWebsites.next(this.websites.slice());

    this.websiteFilterCtrl.valueChanges
      .pipe(takeUntil(this._onDestroy))
      .subscribe(() => {
        this.filterBanks();
      });
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
    this.filteredWebsites
      .pipe(take(1), takeUntil(this._onDestroy))
      .subscribe(() => {
        this.singleSelect.compareWith = (a: Website, b: Website) => a && b && a.id === b.id;
      });
  }

  /**
   * Write code on Method
   *
   * method logical code
   */
  protected filterBanks() {
    if (!this.websites) {
      return;
    }

    let search = this.websiteFilterCtrl.value;
    if (!search) {
      this.filteredWebsites.next(this.websites.slice());
      return;
    } else {
      search = search.toLowerCase();
    }

    this.filteredWebsites.next(
      this.websites.filter(bank => bank.name.toLowerCase().indexOf(search) > -1)
    );
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



  onSubmit(){
   console.log(this.testWebsite);
  }
}
