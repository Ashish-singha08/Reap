import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EndroseComponent } from './endrose.component';

describe('KanbanDialogComponent', () => {
  let component: EndroseComponent;
  let fixture: ComponentFixture<EndroseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EndroseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EndroseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
