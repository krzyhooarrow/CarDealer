import { Component, Input, OnInit } from '@angular/core';
import { TransactionDTO } from 'src/models/transaction-interfaces';
import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-transaction-price',
  templateUrl: './transaction-price.component.html',
  styleUrls: ['./transaction-price.component.scss']
})
export class TransactionPriceComponent implements OnInit {
  predictionChart: Object;
  averagePriceChart: Object;
  popularityChart: Object;
  title: string;
  priceData = [];
  priceRatioData;
  popularityRatioData;

  @Input() transaction:TransactionDTO;

  constructor(public transactionService:TransactionService) { }
  
  ngOnInit(): void {
    this.getPredictedPrices(this.transaction.id)
    this.getOfferPopularityRatios(this.transaction.id)
    this.getPricePositionRatios(this.transaction.id)
  }

  getPredictedPrices(offerId:number){
    this.transactionService.getPredictedPrices(offerId).subscribe(
      (values:number[][]) => {
      values.forEach(pair => this.priceData.push({ label: pair[0].toString(), value: pair[1]}))
      this.predictionChart = {
        chart: {
          caption: 'Car\'s price with predicted price in future',
          subCaption: 'In (K) = 1000PLN',
          xAxisName: 'Year',
          yAxisName: 'Price',
          theme: 'fusion',
        },
        data: this.priceData
      };
    },()=>{}
    );
  }

  getOfferPopularityRatios(offerId:number){
    this.transactionService.getOfferPopularityRatios(offerId).subscribe(
      values => { this.popularityRatioData = values

        const value = Math.round(100*(values['popularity']));

        this.popularityChart = {
          chart: {
            caption: 'Offer popularity ratio',
            yAxisName: 'Offer visits',
            numberSuffix: '%',
            theme: 'fusion',
            yAxisMaxValue: '100'
          },"categories": [{"category": [{ "label": "Offer visits",},]}],
        "dataset": [{"seriesname": "This offer visitors ", "data": [{ "value": value },]}, 
        {"seriesname": "Most common visited offer","data": [{ "value": 100 - value },]}]};

      },()=>{})
  }

  getPricePositionRatios(offerId:number){
    this.transactionService.getPricePositionRatios(offerId).subscribe(
      values => {this.priceRatioData = values   

      this.averagePriceChart = {
        chart: {
          caption: 'Car\'s price position in offers containing this car model',
          theme: 'fusion',
          palettecolors: "006400,F0E68C,DC143C"
        },
        data: [
          { label: 'Cheaper offers', value: values['less']},
          { label: 'Your offer', value: '0,01' },
          { label: 'More expensive offers', value: values['more'] },
        ]
      }},()=>{}

      );
  }
}
