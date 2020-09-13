import { Component, OnInit } from '@angular/core';

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
  constructor() { 

    this.predictionChart = {
      chart: {
        caption: 'Car\'s price in past years with predicted price in future',
        subCaption: 'In (K) = 1000$',
        xAxisName: 'Year',
        yAxisName: 'Price',
        numberSuffix: 'K',
        theme: 'fusion',
        
      },
      data: [
        { label: '2015', value: '290' },
        { label: '2016', value: '260' },
        { label: '2017', value: '180' },
        { label: '2018', value: '140' },
        { label: '2019', value: '115' },
        { label: '2020', value: '100' },
        { label: '2021', value: '30' },
        { label: '2022', value: '30' }
      ]
    };

    this.averagePriceChart = {
      chart: {
        caption: 'Car\'s price position in offers containing this car model',
        subCaption: 'In (K) = 1000$',
        xAxisName: 'Year',
        yAxisName: 'Price',
        numberSuffix: 'K',
        theme: 'fusion',
        palettecolors: "006400,F0E68C,DC143C"
      },
      data: [
        { label: 'Cheaper offers', value: '70' },
        { label: 'Offers with same price', value: '5' },
        { label: 'More expensive offers', value: '29' },
  
      ]
    };


    this.popularityChart = {
      chart: {
        caption: 'Offer popularity',
        subCaption: 'In (K) = 1000$',
        // xAxisName: 'Offer',
        yAxisName: 'Popularity',
        numberSuffix: '%',
        theme: 'fusion',
   
      },
      "categories": [{
        "category": [{
            "label": "Offer visits",},
          {  "label": "Offer watchers"
        },]
    }],

    "dataset": [{
        "seriesname": "This offer visitors/subscribers",
        "data": [{
            "value": "10"
        },
      {
        "value":"20"
      } ]
    }, {
        "seriesname": "Difference between most common visited/subscribed offer",
        "data": [{
            "value": "90"
        },{
          "value":"80"
        } ]
    }]
    };

  }

  ngOnInit(): void {
  }

}
