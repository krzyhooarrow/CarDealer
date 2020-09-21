import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Config } from './app-config';
import { UserDTO } from 'src/models/interfaces';
import { CookieService } from 'ngx-cookie-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  public isAuthenticated = false;

  constructor(private config:Config ,private http:HttpClient,private cookieService:CookieService,private router: Router) {  }

  public redirectIfAuthenticated(){
      if(this.isAuthenticated)
      this.router.navigate(['/search']);
  }

  public auth(login:String,password:String){
    return this.http.post<string>(this.config.API_URL_SERVER.concat(this.config.LOGIN_USER_ENDPOINT),{username:login,password:password},this.config.HEADER)
  }

  public register(userDTO:UserDTO){
      this.http.post<String>(this.config.API_URL_SERVER.concat(this.config.REGISTER_USER_ENDPOINT),userDTO,this.config.HEADER).subscribe()
  }

  public getAuthHeader() { return { headers:{"Content-type":"application/json","Authorization":"Bearer ".concat(this.cookieService.get("JWT"))}}}
    
  public authenticationCheck(){  
    return this.http.get<string>(this.config.API_URL_SERVER,this.getAuthHeader())
    .subscribe(
      () => this.isAuthenticated = true,
      () => this.isAuthenticated = false
       );
      }
  
      
   public logout(){this.cookieService.delete("JWT"); this.authenticationCheck(); this.redirectIfAuthenticated()}

}
