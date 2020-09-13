import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SearchComponent } from './search/search.component';
import { TransactionComponent } from './transaction/transaction.component';


const routes: Routes = [
  { path: 'search', component: SearchComponent },
  { path: 'offer', component: TransactionComponent },
  { path: 'auth', component: LoginComponent },
  {path: '',   redirectTo: 'search', pathMatch: 'full'} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
