import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { MessageService } from 'primeng/api';
import { FileSelectEvent } from 'primeng/fileupload';
import { SessionService } from '../services/session.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  public form: FormGroup;
  public loading: boolean = false;
  private image: any = null;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private messageService: MessageService,
    private sessionService: SessionService,
    private router: Router
  ) {
    this.form = this.formBuilder.group({
      name: [null, Validators.required],
      surname: [null, Validators.required],
      username: [null, Validators.required],
      password: [null, Validators.required],
      city: [null, Validators.required],
      mail: [null, Validators.email],
    });
  }

  onImageSelect(event: FileSelectEvent) {
    this.image = event.files[0];
  }

  submit() {
    if (this.form.valid) {
      const request = {
        username: this.form.value.username,
        password: this.form.value.password,
        name: this.form.value.name,
        surname: this.form.value.surname,
        city: this.form.value.city,
        mail: this.form.value.mail,
        image: this.image,
      };

      const formData = new FormData();
      formData.append('username', request.username);
      formData.append('password', request.password);
      formData.append('name', request.name);
      formData.append('surname', request.surname);
      formData.append('city', request.city);
      formData.append('mail', request.mail);

      if (request.image) {
        formData.append('image', request.image, request.image.name);
      }

      console.log(formData);

      this.loading = true;
      this.authService.register(formData).subscribe({
        next: (response) => {
          this.sessionService.setUID(response as number);
          this.router.navigate(['/']);
        },
        error: (err) => {
          this.messageService.add({
            severity: 'error',
            summary: 'Registration failed',
            detail: 'An error occurred during registration.',
          });
          this.loading = false;
        },
        complete: () => {
          this.loading = false;
        },
      });
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Registration failed',
        detail: 'Fields are required',
      });
    }
  }
}
