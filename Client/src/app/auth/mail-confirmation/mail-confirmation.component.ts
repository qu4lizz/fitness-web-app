import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-mail-confirmation',
  templateUrl: './mail-confirmation.component.html',
  styleUrl: './mail-confirmation.component.css',
})
export class MailConfirmationComponent implements OnInit {
  public message: string = '';

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      const id = params['id'];
      if (id) {
        this.authService.confirmEmail(id).subscribe({
          next: (res: any) => {
            this.message = 'Email successfully verified';
          },
          error: (err: any) => {
            if (err.status === 400)
              this.message = 'Your Email is already verified';
            else this.message = 'An Error Occured';
          },
          complete: () => {},
        });
      } else {
        this.message = 'Incorrect address';
      }
    });
  }
}
