import { Component, Input, OnInit } from '@angular/core';
import { TransactionDTO } from 'src/models/transaction-interfaces';

@Component({
  selector: 'app-user-offers',
  templateUrl: './user-offers.component.html',
  styleUrls: ['./user-offers.component.scss']
})
export class UserOffersComponent implements OnInit {

  @Input() transactions:TransactionDTO[]

  constructor() { }


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

  ngOnInit(): void {
  }

}
