import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

const baseUrl = environment.API_URL + 'difficulties';

@Injectable({
  providedIn: 'root',
})
export class DifficultyService {
  constructor(private http: HttpClient) {}

  public getAll() {
    return this.http.get(baseUrl);
  }
}
