import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchTileComponent } from './search-tile.component';

describe('SearchTileComponent', () => {
  let component: SearchTileComponent;
  let fixture: ComponentFixture<SearchTileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchTileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchTileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
