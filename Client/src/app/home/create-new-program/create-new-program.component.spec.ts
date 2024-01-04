import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateNewProgramComponent } from './create-new-program.component';

describe('CreateNewProgramComponent', () => {
  let component: CreateNewProgramComponent;
  let fixture: ComponentFixture<CreateNewProgramComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateNewProgramComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateNewProgramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
