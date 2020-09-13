import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-transaction-description',
  templateUrl: './transaction-description.component.html',
  styleUrls: ['./transaction-description.component.scss']
})
export class TransactionDescriptionComponent implements OnInit {

  public offerDescription:String = "Witam, \
  zachęcam do kupna samochodu w najbogatszej wersji Executive. Auto kupione w Polsce w salonie Karlik w Poznaniu. Jestem drugim właścicielem. Auto serwisowane wyłącznie w ASO. Wszystkie przeglądy wykonywane były przed sugerowanym przez producenta terminem(wraz z wymianą oleju przekładniowego w automatycznej skrzyni biegów). Ostatnia wymiana oleju, kompletu filtrów wraz z regulacją zaworów wykonana pod koniec sierpnia 2020. Zainstalowałem najdroższą na rynku instalację LPG - Landi Renzo, której wszystkie przeglądy wykonywałem również przed sugerowanym przez producenta terminem(ostatni przegląd na przełomie lipca/sieprnia 2020. Auto garażowane. Założone są nowe opony całoroczne. Ostatni przegląd rejestracyjny - 05.09.2020. Auto posiada drobne, parkingowe usterki lakiernicze.\
  Zachęcam do kontaktu, chętnie odpowiem na wszystkie pytania.";

  constructor() { }

  ngOnInit(): void {
  }

}
