import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { CategoryService } from '../services/category.service';
import { DifficultyService } from '../services/difficulty.service';
import { ProgramService } from '../services/program.service';
import { SessionService } from '../../auth/services/session.service';
import { MessageService } from 'primeng/api';
import { FileSelectEvent } from 'primeng/fileupload';

@Component({
  selector: 'app-create-new-program',
  templateUrl: './create-new-program.component.html',
  styleUrl: './create-new-program.component.css',
})
export class CreateNewProgramComponent implements OnInit {
  public form: FormGroup;
  public loading: boolean = false;
  public minDate: Date = new Date();
  public difficulties: any;
  public categories: any;
  private image?: File[];

  constructor(
    private formBuilder: FormBuilder,
    private categoryService: CategoryService,
    private difficultyService: DifficultyService,
    private programService: ProgramService,
    private sessionService: SessionService,
    private messageService: MessageService
  ) {
    this.form = this.formBuilder.group({
      name: [null, Validators.required],
      description: [null, Validators.required],
      price: [null, Validators.required],
      start: [null, Validators.required],
      duration: [null, Validators.required],
      location: [null, Validators.required],
      url: [null, Validators.nullValidator],
      idDifficulty: [null, Validators.required],
      idCategory: [null, Validators.required],
    });
  }

  ngOnInit(): void {
    this.categoryService.getAll().subscribe({
      next: (response: any) => {
        this.categories = response;
      },
    });

    this.difficultyService.getAll().subscribe({
      next: (response: any) => {
        this.difficulties = response;
      },
    });
  }

  public onImageSelect(event: FileSelectEvent) {
    this.image = event.files;
  }

  public submit() {
    const formData = new FormData();
    formData.append('name', this.form.value.name);
    formData.append('description', this.form.value.description);
    formData.append('price', this.form.value.price);
    formData.append('start', new Date(this.form.value.start).toISOString());
    formData.append('duration', this.form.value.duration);
    formData.append('location', this.form.value.location);
    formData.append('url', this.form.value.url);
    formData.append('idDifficulty', this.form.value.idDifficulty);
    formData.append('idCategory', this.form.value.idCategory);
    formData.append('active', 'true');
    formData.append('idUser', '' + this.sessionService.getUID());

    const imageArray = Array.from(this.image || []);

    if (imageArray.length > 0) {
      imageArray.forEach((image) => {
        formData.append('programImages[]', image, image.name);
      });
    }

    if (this.form.valid && this.image) {
      this.programService.create(formData).subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Program Successfully Created',
            detail: 'You have created program successfully',
          });
        },
        error: () => {
          this.messageService.add({
            severity: 'error',
            summary: 'Program Creation Error',
            detail: 'Server failed',
          });
        },
      });
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Program Creation Error',
        detail: 'Fields are not correctly filled',
      });
    }
  }
}
