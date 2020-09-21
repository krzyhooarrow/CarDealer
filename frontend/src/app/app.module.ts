import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';
import {MatStepperModule} from '@angular/material/stepper';

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatIconModule} from '@angular/material/icon';
import { OffersTileComponent } from './offers-tile/offers-tile.component';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule ,ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {IvyCarouselModule} from 'angular-responsive-carousel';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatCardModule} from '@angular/material/card';
import { OfferTextComponent } from './offer-text/offer-text.component';
import {MatRippleModule} from '@angular/material/core';
import {MatChipsModule} from '@angular/material/chips';
import {MatTabsModule} from '@angular/material/tabs';
import {MatDialogModule} from '@angular/material/dialog';
import {MatRadioModule} from '@angular/material/radio';


import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { SearchComponent } from './search/search.component';
import { TransactionComponent } from './transaction/transaction.component';
import {MatDividerModule} from '@angular/material/divider';
import { TransactionDetailsComponent } from './transaction-details/transaction-details.component';
import { TransactionEquipmentComponent } from './transaction-equipment/transaction-equipment.component';
import { TransactionDescriptionComponent } from './transaction-description/transaction-description.component';
import { TransactionPriceComponent } from './transaction-price/transaction-price.component';
import { TransactionUserDetailsComponent } from './transaction-user-details/transaction-user-details.component';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { NgxDropzoneModule } from 'ngx-dropzone';


import * as FusionCharts from 'fusioncharts';
import * as Charts from 'fusioncharts/fusioncharts.charts';
import * as FusionTheme from 'fusioncharts/themes/fusioncharts.theme.fusion';
import { FusionChartsModule } from 'angular-fusioncharts';
import {MatListModule} from '@angular/material/list';
import {MatExpansionModule} from '@angular/material/expansion';
import { MatInputModule } from '@angular/material/input';
import {MatFormField, MatFormFieldModule} from '@angular/material/form-field';
import { RegisterComponent } from './register/register.component';
import { HttpClientModule } from '@angular/common/http';
import { MyOffersComponent } from './my-offers/my-offers.component';
import { NotLoggedComponent } from './not-logged/not-logged.component';
import { UserOffersComponent } from './user-offers/user-offers.component';
import { NewOfferComponent } from './new-offer/new-offer.component';
import { OffersHistoryComponent } from './offers-history/offers-history.component';
import {MatTableModule} from '@angular/material/table';
import {MatSortModule} from '@angular/material/sort';
import { SubscribedOffersComponent } from './subscribed-offers/subscribed-offers.component';
import { OfferPreviewComponent } from './offer-preview/offer-preview.component';
FusionChartsModule.fcRoot(FusionCharts, Charts,FusionTheme);


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    OffersTileComponent,
    OfferTextComponent,
    SearchComponent,
    TransactionComponent,
    TransactionDetailsComponent,
    TransactionEquipmentComponent,
    TransactionDescriptionComponent,
    TransactionPriceComponent,
    TransactionUserDetailsComponent,
    RegisterComponent,
    MyOffersComponent,
    NotLoggedComponent,
    UserOffersComponent,
    NewOfferComponent,
    OffersHistoryComponent,
    SubscribedOffersComponent,
    OfferPreviewComponent
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSliderModule,
    MatIconModule,
    MatToolbarModule,
    MatGridListModule,
    MatSelectModule,
    FormsModule,
    MatButtonModule,
    IvyCarouselModule,
    MatPaginatorModule,
    MatCardModule,
    MatRippleModule,
    MatChipsModule,
    MatTabsModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    MatDividerModule,
    FusionChartsModule,
    MatListModule,
    MatSlideToggleModule,
    MatExpansionModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSnackBarModule,
    MatTableModule,
    MatSortModule,
    MatStepperModule,
    MatRadioModule,
    NgxDropzoneModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  exports: []
})
export class AppModule { }
