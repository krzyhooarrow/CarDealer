import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatSnackBar, MatSnackBarRef } from '@angular/material/snack-bar';
import { CookieService } from 'ngx-cookie-service';
import { error } from 'protractor';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  login:String
  password:String
  service:AuthService
  cookieService:CookieService
  snackBar:MatSnackBar
  matRef

  constructor(service: AuthService,cookieService:CookieService,snackBar:MatSnackBar) { 
    this.service = service
    this.cookieService = cookieService;
    this.snackBar = snackBar;
   }

  ngOnInit(): void {
  
  }

  onLogin(){   
    this.service.auth(this.login,this.password).subscribe(
        (token:string) =>  {this.cookieService.set("JWT",token["jwt"]); this.successfullLogin(); },
         () => {this.matRef = this.snackBar.open( 'Wrong credentials' ,null,{ duration: 2000,})
        }
         );
  }

  successfullLogin(){    this.service.isAuthenticated = true;   this.service.redirectIfAuthenticated() }



  ngOnDestroy(){  if(this.matRef)this.matRef.dismiss()  }  
  
}
