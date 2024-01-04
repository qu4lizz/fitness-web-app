import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TopTenCardComponent } from './top-ten-card.component';

describe('TopTenCardComponent', () => {
  let component: TopTenCardComponent;
  let fixture: ComponentFixture<TopTenCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TopTenCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TopTenCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
