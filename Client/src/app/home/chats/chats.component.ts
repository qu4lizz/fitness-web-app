import { Component, OnInit } from '@angular/core';
import { ChatService } from '../services/chat.service';
import { SessionService } from '../../auth/services/session.service';
import { UtilFunctions } from '../../utils/functions';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chats',
  templateUrl: './chats.component.html',
  styleUrl: './chats.component.css',
})
export class ChatsComponent implements OnInit {
  chats!: any;

  constructor(
    private chatService: ChatService,
    private sessionService: SessionService,
    public utilFunctions: UtilFunctions,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.chatService
      .getAllChatsByUser(this.sessionService.getUID()!)
      .subscribe({
        next: (res: any) => {
          this.chats = res;
        },
      });
  }

  navigateToChat(id: number) {
    this.router.navigate([`./${id}`]);
  }
}
