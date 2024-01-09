import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { SessionService } from '../../auth/services/session.service';
import { ProgramService } from '../services/program.service';
import { ActivatedRoute, Router } from '@angular/router';
import {
  ProgramDetailsResponse as ProgramDetails,
  UserCommentDTO,
} from '../../models/models';
import { UtilFunctions } from '../../utils/functions';
import { MessageService } from 'primeng/api';
import { ParticipationPaymentModalComponent } from '../participation-payment-modal/participation-payment-modal.component';
import { ChatService } from '../services/chat.service';

@Component({
  selector: 'app-program-details',
  templateUrl: './program-details.component.html',
  styleUrl: './program-details.component.css',
})
export class ProgramDetailsComponent implements OnInit {
  id!: number;

  isLogged!: boolean;
  isOwner!: boolean;
  loggedUserParticipates: boolean = false;

  @ViewChild('comment') commentTextarea!: ElementRef;
  @ViewChild(ParticipationPaymentModalComponent)
  participationPaymentModal!: ParticipationPaymentModalComponent;
  program!: ProgramDetails;

  price!: string;
  images: any[] = [];
  comments!: UserCommentDTO[];

  responsiveOptions!: any[];

  constructor(
    public utilFunctions: UtilFunctions,
    private sessionService: SessionService,
    private programService: ProgramService,
    private route: ActivatedRoute,
    private messageService: MessageService,
    private chatService: ChatService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.isLogged = this.sessionService.getUID() !== null;

    this.route.params.subscribe((params) => {
      this.id = params['id'];

      this.reloadPage();

      this.programService
        .userParticipatesProgram(this.sessionService.getUID()!, this.id)
        .subscribe((res: any) => {
          this.loggedUserParticipates = res;
        });

      this.responsiveOptions = [
        {
          breakpoint: '1024px',
          numVisible: 5,
        },
        {
          breakpoint: '768px',
          numVisible: 3,
        },
        {
          breakpoint: '560px',
          numVisible: 1,
        },
      ];
    });
  }

  reloadPage() {
    if (this.id) {
      this.programService.getById(this.id).subscribe({
        next: (res: any) => {
          this.program = res;

          this.price = this.program.price + '€';

          this.comments = this.program.comments.sort(
            (c1, c2) =>
              new Date(c1.timestamp).getTime() -
              new Date(c2.timestamp).getTime()
          );

          this.program.programImages.forEach((img) =>
            this.images.push(this.utilFunctions.getImageSrc(img.image))
          );

          this.isOwner =
            this.program.instructor.id === this.sessionService.getUID();
        },
        error: (err: any) => {
          console.log(err);
        },
      });
    }
  }

  buyProgram() {
    this.participationPaymentModal.showDialog();
  }

  sendMessage() {
    let chatId: any;

    this.chatService
      .initChat({
        userOne: this.sessionService.getUID(),
        userTwo: this.program.instructor.id,
      })
      .subscribe({
        next: (res: any) => (chatId = res),
        error: (err: any) => console.log(err),
        complete: () => this.router.navigate([`/messages/${chatId}`]),
      });
  }

  submitComment(text: string) {
    if (!text || text.trim().length == 0) {
      this.messageService.add({
        severity: 'error',
        summary: 'Comment Error',
        detail: 'Comment has to have content',
      });
      return;
    }

    this.programService
      .addComment({
        idUser: this.sessionService.getUID(),
        comment: text,
        timestamp: new Date(),
        idProgram: this.program.id,
      })
      .subscribe({
        error: (err: any) => {
          console.log(err);
          this.messageService.add({
            severity: 'error',
            summary: 'Comment Error',
            detail: 'Comment has to have content',
          });
        },
        complete: () => {
          this.commentTextarea.nativeElement.value = '';
          this.reloadPage();
        },
      });
  }

  canShowVideoURL(program: ProgramDetails) {
    if (
      program.videoUrl &&
      new Date(program.start) < new Date() &&
      this.loggedUserParticipates
    ) {
      return true;
    }
    return false;
  }

  getSeverity(difficultyName: string) {
    switch (difficultyName.toLowerCase()) {
      case 'beginner':
        return 'success';
      case 'intermediate':
        return 'warning';
      case 'expert':
        return 'danger';
      default:
        return undefined;
    }
  }
}
