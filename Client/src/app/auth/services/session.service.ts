import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SessionService {
  private userId: number | null = null;
  private key: string = 'session';

  public sessionStateChanged: EventEmitter<void> = new EventEmitter<void>();

  constructor() {
    const stored = localStorage.getItem(this.key);

    if (stored) {
      this.userId = JSON.parse(stored).userId;
    }
  }

  public getUID() {
    return this.userId;
  }

  public setUID(num: number) {
    this.userId = num;
    localStorage.setItem(this.key, JSON.stringify({ userId: num }));
    this.emitSessionStateChanged();
  }

  public removeUID() {
    this.userId = null;
    localStorage.removeItem(this.key);
    this.emitSessionStateChanged();
  }

  private emitSessionStateChanged() {
    this.sessionStateChanged.emit();
  }
}
