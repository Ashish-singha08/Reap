
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginService } from './services/login.service';
import { AuthGuard } from './services/auth.guard';
import { AuthInterceptor } from './services/auth.interceptor';
import { MatSelectModule } from '@angular/material/select';
import {EndorseService} from './services/endorse.service';
import {QuestionService} from "./services/question.service";
import {MatListModule} from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';
import {StoreService} from "./services/store.service";
import {MatBadgeModule} from '@angular/material/badge';
// import { MatOption } from '@angular/material/core';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import  {MatRadioModule} from '@angular/material/radio';
import { IonicModule } from '@ionic/angular';
import {StoreComponent} from "./components/store/store.component";
import {MatTabsModule} from "@angular/material/tabs";
import { NgxMatSelectSearchModule } from 'ngx-mat-select-search';
import {MatDialogModule} from "@angular/material/dialog";
import {DialogComponent} from "./components/dialogbox/dialog.component";
import {EndroseComponent} from "./components/endroseform/endrose.component";
import { ToastrModule } from 'ngx-toastr';
@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    HomeComponent,
    LoginComponent,
    DashboardComponent,
    StoreComponent,
    DialogComponent,
    EndroseComponent,
  ],
  imports: [
    MatBadgeModule,
    MatListModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    ToastrModule.forRoot({
      timeOut: 5000, // 5 seconds
      closeButton: true,
      progressBar: true,
    }),
    MatInputModule,
    FormsModule,
    HttpClientModule,
    MatSelectModule,
    MatCheckboxModule,
    MatAutocompleteModule,
    MatRadioModule,
    IonicModule.forRoot(),
    MatTabsModule,
    MatFormFieldModule,
    NgxMatSelectSearchModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatIconModule,
  ],
  providers: [StoreService,QuestionService,LoginService ,AuthGuard, EndorseService, [{provide:HTTP_INTERCEPTORS, useClass:AuthInterceptor, multi:true}]],
  bootstrap: [AppComponent]
})
export class AppModule { }
