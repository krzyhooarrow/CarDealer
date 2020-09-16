import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-my-offers',
  templateUrl: './my-offers.component.html',
  styleUrls: ['./my-offers.component.scss']
})
export class MyOffersComponent implements OnInit {


  auth:AuthService
  constructor(auth:AuthService) {
    this.auth = auth 
    auth.authenticationCheck()
  }

  ngOnInit(): void {
  }

}
