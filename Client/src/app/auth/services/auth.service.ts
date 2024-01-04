import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

const baseUrl = environment.API_URL + 'auth/';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  public login(req: any) {
    return this.http.post(baseUrl + 'login', req);
  }

  public register(req: any) {
    return this.http.post(baseUrl + 'register', req);
  }

  public confirmEmail(id: number) {
    return this.http.post(baseUrl + 'mail-confirmation', { id });
  }
}
