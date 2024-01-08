import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SessionService {
  private userId?: number;
  private username?: string;
  private key: string = 'session';

  public sessionStateChanged: EventEmitter<void> = new EventEmitter<void>();

  constructor() {
    const stored = localStorage.getItem(this.key);

    if (stored) {
      this.userId = JSON.parse(stored).userId;
      this.username = JSON.parse(stored).username;
    }
  }

  public getUID() {
    return this.userId;
  }

  public setSession(uid: number, username: string) {
    this.userId = uid;
    this.username = username;
    localStorage.setItem(this.key, JSON.stringify({ userId: uid, username }));
    this.emitSessionStateChanged();
  }

  public removeUID() {
    this.userId = undefined;
    localStorage.removeItem(this.key);
    this.emitSessionStateChanged();
  }

  public getUsername() {
    return this.username;
  }

  private emitSessionStateChanged() {
    this.sessionStateChanged.emit();
  }
}
