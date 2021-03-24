import { Component, OnInit } from '@angular/core';
import { UpdateElevator} from "../model/update-elevator";
import {Form, FormControl, FormGroup, Validators} from "@angular/forms";
import {ElevatorSystemService} from "../elevator-system.service";

@Component({
  selector: 'app-update-elevators',
  templateUrl: './update-elevators.component.html',
  styleUrls: ['./update-elevators.component.scss']
})
export class UpdateElevatorsComponent implements OnInit {
  updates: UpdateElevator[] = [];
  elevatorUpdate!: UpdateElevator;
  formGroup: FormGroup;
  idElevatorFC: FormControl;
  currentFloorFC: FormControl;
  targetFloorFC: FormControl;

  constructor(private elevatorSystemService: ElevatorSystemService) {
    this.idElevatorFC = new FormControl('', Validators.required);
    this.currentFloorFC = new FormControl('', Validators.required);
    this.targetFloorFC = new FormControl('', Validators.required);
    this.formGroup = new FormGroup({
      idElevatorFC: this.idElevatorFC,
      currentFloorFC: this.currentFloorFC,
      targetFloorFC: this.targetFloorFC
    })
  }

  ngOnInit(): void {
  }

  addUpdate(): void {
    this.elevatorUpdate = {idElevator: this.idElevatorFC.value,
      currentFloor: this.currentFloorFC.value,
      targetFloor: this.targetFloorFC.value};
    this.updates.push(this.elevatorUpdate);
    this.elevatorSystemService.update(this.elevatorUpdate);
    this.resetForm();
  }

  resetForm():void {
    this.idElevatorFC.reset();
    this.targetFloorFC.reset();
    this.currentFloorFC.reset();
  }
}
