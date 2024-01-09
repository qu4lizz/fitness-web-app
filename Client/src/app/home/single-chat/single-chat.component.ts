import {
  ChangeDetectionStrategy,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
} from '@angular/core';
import { SessionService } from '../../auth/services/session.service';
import { ChatService } from '../services/chat.service';
import { UtilFunctions } from '../../utils/functions';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-single-chat',
  templateUrl: './single-chat.component.html',
  styleUrls: ['./single-chat.component.css'],
})
export class SingleChatComponent implements OnInit {
  chatId!: number;
  chat!: any;
  loggedUser!: any;
  otherUser!: any;

  @ViewChild('msgInput') message!: ElementRef;

  constructor(
    private sessionService: SessionService,
    private chatService: ChatService,
    public utilFunctions: UtilFunctions,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.refreshChat();
  }

  refreshChat() {
    const loggedUserId = this.sessionService.getUID()!;

    this.route.params.subscribe((params) => {
      this.chatId = params['id'];

      this.chatService.getSingleChat(this.chatId).subscribe({
        next: (res: any) => {
          console.log(res);
          this.chat = res;
          if (this.chat.userOne.id === loggedUserId) {
            this.loggedUser = this.chat.userOne;
            this.otherUser = this.chat.userTwo;
          } else {
            this.loggedUser = this.chat.userTwo;
            this.otherUser = this.chat.userOne;
          }
        },
        error: (err: any) => {
          console.log(err);
        },
      });
    });
  }

  sendMessage() {
    const msg = this.message.nativeElement.value;

    if (!msg && msg.trim().length === 0) return;

    const obj = {
      senderId: this.loggedUser.id,
      chatId: this.chatId,
      message: msg,
      timestamp: new Date(),
    };

    this.chatService.sendMessage(obj).subscribe({
      error: (err: any) => console.log(err),
      complete: () => window.location.reload(),
    });

    this.message.nativeElement.value = '';
  }
}
