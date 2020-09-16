import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';

  auth:AuthService;
  isUserAuthenticated = false;

    constructor(auth:AuthService){ 
      this.auth = auth; 
      auth.authenticationCheck()
    }

}
