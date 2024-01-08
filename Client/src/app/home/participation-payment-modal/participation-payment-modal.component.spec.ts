import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipationPaymentModalComponent } from './participation-payment-modal.component';

describe('ParticipationPaymentModalComponent', () => {
  let component: ParticipationPaymentModalComponent;
  let fixture: ComponentFixture<ParticipationPaymentModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ParticipationPaymentModalComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ParticipationPaymentModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
