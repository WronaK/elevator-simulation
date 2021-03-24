import { Component, OnInit } from '@angular/core';
import { WaiterInfo} from "../model/waiter-info";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ElevatorSystemService} from "../elevator-system.service";

@Component({
  selector: 'app-pickup-elevators',
  templateUrl: './pickup-elevators.component.html',
  styleUrls: ['./pickup-elevators.component.scss']
})
export class PickupElevatorsComponent implements OnInit {
  pickups: WaiterInfo[] = [];
  directionFC: FormControl;
  fillingFloorFC: FormControl;
  formGroup: FormGroup;

  constructor(private elevatorSystemService: ElevatorSystemService) {
    this.directionFC = new FormControl('', Validators.required);
    this.fillingFloorFC = new FormControl('', Validators.required);
    this.formGroup = new FormGroup({
      directionFC: this.directionFC,
      fillingFloorFC: this.fillingFloorFC,
    })
  }

  ngOnInit(): void {
  }

  addPickup(): void {
      this.pickups.push({fillingFloor: this.fillingFloorFC.value,
        direction: this.getDirection()});
      this.elevatorSystemService.pickup({fillingFloor: this.fillingFloorFC.value,
        direction: this.getDirection()});
      this.resetForm();
  }

  getDirection(): number {
    if(this.directionFC.value === "down") {
      return -1;
    } else {
      return 1;
    }
  }

  resetForm():void {
    this.directionFC.reset();
    this.fillingFloorFC.reset();
  }

}
