import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { MessageService } from 'primeng/api';
import { SessionService } from '../services/session.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  public form: FormGroup;
  public loading: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private messageService: MessageService,
    private sessionService: SessionService,
    private router: Router
  ) {
    this.form = this.formBuilder.group({
      username: [null, Validators.required],
      password: [null, Validators.required],
    });
  }

  submit() {
    const request = {
      username: this.form.value.username,
      password: this.form.value.password,
    };

    this.loading = true;
    this.authService.login(request).subscribe({
      next: (response) => {
        this.sessionService.setUID(response as number);
        this.router.navigate(['/']);
      },
      error: (err) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Login failed',
          detail: 'Invalid credentials',
        });
        this.loading = false;
      },
      complete: () => {
        this.messageService.add({
          severity: 'success',
          summary: 'Successful login',
          detail: 'You have logged in successfully',
        });
        this.loading = false;
      },
    });
  }
}
