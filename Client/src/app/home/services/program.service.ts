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
    return this.http.get(baseUrl + queryString);
  }

  public getAllByMe(queryString: string) {
    return this.http.get(baseUrl + '/my' + queryString);
  }

  public create(program: any) {
    return this.http.post(baseUrl, program);
  }

  public delete(id: number) {
    return this.http.delete(baseUrl + `/${id}`);
  }

  public getById(id: number) {
    return this.http.get(baseUrl + `/${id}`);
  }

  public addComment(obj: any) {
    return this.http.post(baseUrl + '/comments', obj);
  }

  public buyProgram(obj: any) {
    return this.http.post(baseUrl + '/buy', obj);
  }

  public userParticipatesProgram(idUser: number, idProgram: number) {
    return this.http.get(baseUrl + `/user/${idUser}/participates/${idProgram}`);
  }
}
