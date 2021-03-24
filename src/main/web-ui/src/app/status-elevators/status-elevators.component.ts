import { Component, OnInit } from '@angular/core';
import {StateElevator} from "../model/state-elevator";
import {ElevatorSystemService} from "../elevator-system.service";
import {tap} from "rxjs/operators";

@Component({
  selector: 'app-status-elevators',
  templateUrl: './status-elevators.component.html',
  styleUrls: ['./status-elevators.component.scss']
})
export class StatusElevatorsComponent implements OnInit {
  elevatorsStatus: StateElevator[] = [];
  displayedColumns: string[] = ['idElevator', 'currentFloor', 'targetFloor'];

  constructor(private elevatorSystemService: ElevatorSystemService) { }

  ngOnInit(): void {
  }

  step(): void {
    this.elevatorSystemService.executeStep();
    this.elevatorSystemService.getStatus().pipe(
      tap(s => this.elevatorsStatus = s)).subscribe();
  }

}
