import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CookieService } from 'ngx-cookie-service';
import { AuthService } from '../services/auth.service';
import { UserDTO } from 'src/models/interfaces';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  login:String
  password:String
  email:String
  matRef

  public emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  matcher = new Matcher();

  constructor(private service: AuthService,private cookieService:CookieService,private snackBar:MatSnackBar) { }

  ngOnInit(): void {
  }

  onRegister(){   
    let userDTO:UserDTO={
      username:this.login,
      password:this.password,
      email:this.email
    };

    this.service.register(userDTO).subscribe(
      (val) =>  {if(val) this.authenticate()},
       () => {this.matRef = this.snackBar.open( 'Couldn\'t create account. Try another credentials' ,null,{ duration: 2000,})});
  }

  authenticate(){ this.service.auth(this.login,this.password).subscribe((token:string) =>  {this.cookieService.set("JWT",token["jwt"]); this.successfullLogin(); });}

  successfullLogin(){    this.service.isAuthenticated = true;   this.service.redirectIfAuthenticated() }

  ngOnDestroy(){  if(this.matRef)this.matRef.dismiss()  }  
}
export class Matcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}