import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MessageCounselorComponent } from './message-counselor.component';

describe('MessageCounselorComponent', () => {
  let component: MessageCounselorComponent;
  let fixture: ComponentFixture<MessageCounselorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MessageCounselorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MessageCounselorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
