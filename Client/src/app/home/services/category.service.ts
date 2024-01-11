import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

const baseUrl = environment.API_URL + 'categories';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private http: HttpClient) {}

  public getAll() {
    return this.http.get(baseUrl);
  }

  public subscribe(obj: any) {
    return this.http.post(baseUrl + '/subscribe', obj);
  }

  public getSubscribedCategories(uid: number) {
    return this.http.get(baseUrl + `/subscribed/${uid}`);
  }
}
