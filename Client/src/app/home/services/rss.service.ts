import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';

const baseUrl = environment.API_URL + 'rss';

@Injectable({
  providedIn: 'root',
})
export class RssService {
  constructor(private http: HttpClient) {}

  public getRSS() {
    return this.http.get(baseUrl);
  }
}
