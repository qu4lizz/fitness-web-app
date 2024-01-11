import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipatedProgramsComponent } from './participated-programs.component';

describe('ParticipatedProgramsComponent', () => {
  let component: ParticipatedProgramsComponent;
  let fixture: ComponentFixture<ParticipatedProgramsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ParticipatedProgramsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ParticipatedProgramsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
