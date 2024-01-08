import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { SessionService } from '../../auth/services/session.service';
import { MessageService } from 'primeng/api';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-message-counselor',
  templateUrl: './message-counselor.component.html',
  styleUrl: './message-counselor.component.css',
})
export class MessageCounselorComponent {
  @ViewChild('message') messageTextarea!: ElementRef;

  constructor(
    private userService: UserService,
    private sessionService: SessionService,
    private messageService: MessageService
  ) {}

  submitMessage() {
    const msg = this.messageTextarea.nativeElement.value;

    if (!msg && msg.trim().length === 0) {
      this.messageService.add({
        severity: 'error',
        summary: 'Sending Error',
        detail: 'Message cannot be empty',
      });
      return;
    }

    this.userService
      .sendCounselingMessage({
        message: msg,
        timestamp: new Date(),
        idUser: this.sessionService.getUID(),
      })
      .subscribe({
        error: (err: any) => {
          this.messageService.add({
            severity: 'error',
            summary: 'Sending Error',
            detail: 'Message sending failed',
          });
        },
        complete: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Sending Successful',
            detail:
              'Message was sent successfully, you will get response on email',
          });
          this.messageTextarea.nativeElement.value = '';
        },
      });
  }
}
