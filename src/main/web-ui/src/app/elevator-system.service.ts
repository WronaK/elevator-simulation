import { Injectable } from '@angular/core';

import {Observable} from "rxjs";
import { HttpParams, HttpClient} from "@angular/common/http";
import {StateElevator} from "./model/state-elevator";
import {UpdateElevator} from "./model/update-elevator";
import {WaiterInfo} from "./model/waiter-info";

@Injectable({
  providedIn: 'root'
})
export class ElevatorSystemService {

  constructor(private http: HttpClient) { }

  getStatus(): Observable<StateElevator[]> {
    return this.http.get<StateElevator[]>('/status');
  }

  executeStep(): void {
    this.http.post<void>('/step', null).subscribe();
  }

  update(paramsUpdate: UpdateElevator): void {
    const params = new HttpParams()
      .set('id', paramsUpdate.idElevator.toString())
      .set('currentFloor', paramsUpdate.currentFloor.toString())
      .set('targetFloor', paramsUpdate.targetFloor.toString());

    this.http.put<void>('/update', params).subscribe();
  }

  pickup(paramsPickup: WaiterInfo): void {
    const params = new HttpParams()
      .set('fillingFloor', paramsPickup.fillingFloor.toString())
      .set('direction', paramsPickup.direction.toString());

    this.http.put<void>('/pickup', params).subscribe();
  }

}
