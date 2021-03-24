import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { MainLayoutComponent } from './main-layout/main-layout.component';
import { PickupElevatorsComponent } from './pickup-elevators/pickup-elevators.component';
import { UpdateElevatorsComponent } from './update-elevators/update-elevators.component';
import { StatusElevatorsComponent } from './status-elevators/status-elevators.component';
import { MaterialModule } from './material.module';
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {MatPaginatorModule} from "@angular/material/paginator";

@NgModule({
  declarations: [
    AppComponent,
    MainLayoutComponent,
    PickupElevatorsComponent,
    UpdateElevatorsComponent,
    StatusElevatorsComponent
  ],
  imports: [
    BrowserModule,
    MaterialModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatPaginatorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
