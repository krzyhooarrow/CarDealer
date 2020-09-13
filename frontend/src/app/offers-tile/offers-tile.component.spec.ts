import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OffersTileComponent } from './offers-tile.component';

describe('OffersTileComponent', () => {
  let component: OffersTileComponent;
  let fixture: ComponentFixture<OffersTileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OffersTileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OffersTileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
