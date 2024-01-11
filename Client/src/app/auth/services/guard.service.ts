import { Injectable, inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { SessionService } from './session.service';

@Injectable({
  providedIn: 'root',
})
export class GuardService {
  constructor(private sessionService: SessionService, private router: Router) {}

  canActivate(): boolean {
    if (this.sessionService.getUID()) return true;

    this.router.navigate(['']);
    return false;
  }
}

export const guestGuard: CanActivateFn = (route, state) => {
  return inject(GuardService).canActivate();
};
