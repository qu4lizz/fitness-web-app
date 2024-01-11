import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';

const baseUrl = environment.API_URL + 'activities';

@Injectable({
  providedIn: 'root',
})
export class ActivityService {
  constructor(private http: HttpClient) {}

  public getActivitiesOfUser(userId: number) {
    return this.http.get(baseUrl + `/user/${userId}`);
  }

  public addActivity(obj: any) {
    return this.http.post(baseUrl, obj);
  }

  public getPDF(uid: number) {
    this.http
      .get(baseUrl + `/pdf/${uid}`, { responseType: 'blob' })
      .subscribe((response) => {
        const blob = new Blob([response], { type: 'application/pdf' });
        const link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        link.download = 'table.pdf';
        link.click();
      });
  }
}
