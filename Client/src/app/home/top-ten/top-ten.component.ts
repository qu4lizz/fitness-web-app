import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-top-ten',
  templateUrl: './top-ten.component.html',
  styleUrl: './top-ten.component.css',
})
export class TopTenComponent implements OnInit {
  private apiURL: string = 'https://api.api-ninjas.com/v1/exercises';
  public exercises: any;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    // fetch(this.apiURL, {
    //   method: 'GET',
    //   headers: {
    //     'X-Api-Key': environment.API_KEY,
    //     'Content-Type': 'application/json',
    //   },
    // })
    //   .then((response) => {
    //     if (!response.ok) {
    //       throw new Error('Network response was not ok: ' + response.status);
    //     }
    //     return response.json();
    //   })
    //   .then((result) => {
    //     this.exercises = result;
    //   })
    //   .catch((error) => {
    //     console.error('Error:', error.message);
    //   });
  }
}
