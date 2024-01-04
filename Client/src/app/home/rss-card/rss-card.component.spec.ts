import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RssCardComponent } from './rss-card.component';

describe('RssCardComponent', () => {
  let component: RssCardComponent;
  let fixture: ComponentFixture<RssCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RssCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RssCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
