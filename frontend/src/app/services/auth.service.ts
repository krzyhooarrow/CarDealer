import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Config } from './app-config';
import { UserDTO } from 'src/models/interfaces';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private config:Config ,private http:HttpClient,private cookieService:CookieService) {  }


  public auth(login:String,password:String){
    let JWT:string;
    this.http.post<string>(this.config.API_URL_SERVER.concat(this.config.LOGIN_USER_ENDPOINT),{username:login,password:password},this.config.HEADER).subscribe((token:string) => JWT=token);
    this.cookieService.set("JWT",JWT);
  }

  public register(userDTO:UserDTO){
      this.http.post<String>(this.config.API_URL_SERVER.concat(this.config.REGISTER_USER_ENDPOINT),userDTO,this.config.HEADER).subscribe()
  }

  public getAuthHeader() { return { headers:{"Content-type":"application/json","Authorization":"Bearer ".concat(this.cookieService.get("JWT"))}}}
    
 
   

}
