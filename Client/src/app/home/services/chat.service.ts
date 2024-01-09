import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';

const baseUrl = environment.API_URL + 'chats';

@Injectable({
  providedIn: 'root',
})
export class ChatService {
  constructor(private http: HttpClient) {}

  public getAllChatsByUser(userId: number) {
    return this.http.get(baseUrl + `/user/${userId}`);
  }

  public getSingleChat(chatId: number) {
    return this.http.get(baseUrl + `/${chatId}`);
  }

  public sendMessage(obj: any) {
    return this.http.post(baseUrl + '/message', obj);
  }

  public initChat(obj: any) {
    return this.http.post(baseUrl, obj);
  }
}
