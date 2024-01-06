import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

const baseUrl = environment.API_URL + 'programs';

@Injectable({
  providedIn: 'root',
})
export class ProgramService {
  constructor(private http: HttpClient) {}

  public getAll(queryString: string) {
    console.log(baseUrl + queryString);
    return this.http.get(baseUrl + queryString);
  }

  public create(program: any) {
    return this.http.post(baseUrl, program);
  }
}
