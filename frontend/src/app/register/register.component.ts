import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
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
  matRef

  public formGroup:FormGroup = this._formBuilder.group({
    username: ['', [Validators.required,Validators.minLength(5),Validators.maxLength(15)]],
    password: ['',[Validators.required,Validators.minLength(5),Validators.maxLength(15)]],
    email:['', [Validators.required,Validators.email,Validators.minLength(5),Validators.maxLength(15)]]
  });

  matcher = new Matcher();

  constructor(private _formBuilder: FormBuilder,private service: AuthService,private cookieService:CookieService,private snackBar:MatSnackBar) { }

  getErrorMessage() { return 'You \'ve entered wrong values. Please try another'; }

  ngOnInit(): void {}

  onRegister(){ 
    let userDTO:UserDTO = {username:this.formGroup.get('username').value, password:this.formGroup.get('password').value, email:this.formGroup.get('email').value};

    this.service.register(userDTO).subscribe((response) =>  {if(response) this.authenticate();},
       () => {this.matRef = this.snackBar.open( 'Couldn\'t create account. Try another credentials' ,null,{ duration: 2000,})});
  }

  authenticate(){ this.service.auth(this.formGroup.get('username').value,this.formGroup.get('password').value).subscribe((token:string) => {this.cookieService.set("JWT",token["jwt"]); this.successfullLogin(); });}

  successfullLogin(){ this.service.isAuthenticated = true; this.service.redirectIfAuthenticated()}

  ngOnDestroy(){  if(this.matRef)this.matRef.dismiss()  }  
}

export class Matcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}