import { Component, OnInit } from '@angular/core';
import {MatFormFieldModule} from '@angular/material/form-field';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  login:String
  password:String

  constructor() { }

  ngOnInit(): void {
  }

  
}
