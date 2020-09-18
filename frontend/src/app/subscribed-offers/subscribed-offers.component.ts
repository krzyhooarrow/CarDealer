import { Component, Input, OnInit } from '@angular/core';
import { TransactionDTO } from 'src/models/transaction-interfaces';

@Component({
  selector: 'app-subscribed-offers',
  templateUrl: './subscribed-offers.component.html',
  styleUrls: ['./subscribed-offers.component.scss']
})
export class SubscribedOffersComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  @Input() transactions:TransactionDTO[]

  no_images = [ {path: 'https://sertame.com/wp-content/uploads/2019/11/No-Product-Image.png'},
  {path: 'https://sertame.com/wp-content/uploads/2019/11/No-Product-Image.png'},
  ]
  img="https://sertame.com/wp-content/uploads/2019/11/No-Product-Image.png"

  rowSize = 1;
  selectSize = 20;
  bigSelectSize= 40;
  maxCols = 40;
  whiteColor = 'white'
  images = [
    {path: 'https://i.pinimg.com/originals/58/67/49/5867496ec9a6f50592a70f2219203aaf.jpg'},
    {path: 'https://i.pinimg.com/originals/58/67/49/5867496ec9a6f50592a70f2219203aaf.jpg'}
  ]
}
