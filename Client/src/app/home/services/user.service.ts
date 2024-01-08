import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

const baseUrl = environment.API_URL + 'users';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  public getUserInfoForEditing(uid: number) {
    return this.http.get(baseUrl + `/${uid}`);
  }

  public updateUser(uid: number, obj: any) {
    return this.http.put(baseUrl + `/${uid}`, obj);
  }

  public changePassword(uid: number, obj: any) {
    console.log(baseUrl + `/${uid}/update-password`, obj);
    return this.http.put(baseUrl + `/${uid}/update-password`, obj);
  }
}
