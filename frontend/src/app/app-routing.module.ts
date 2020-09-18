import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MyOffersComponent } from './my-offers/my-offers.component';
import { RegisterComponent } from './register/register.component';
import { SearchComponent } from './search/search.component';
import { TransactionComponent } from './transaction/transaction.component';


const routes: Routes = [
  { path: 'search', component: SearchComponent },
  { path: 'offer/:id', component: TransactionComponent },
  { path: 'auth', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'myOffers', component: MyOffersComponent },
  {path: '',   redirectTo: 'search', pathMatch: 'full'} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
