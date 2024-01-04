import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import Parser from 'rss-parser';

@Component({
  selector: 'app-rss-feed',
  templateUrl: './rss-feed.component.html',
  styleUrl: './rss-feed.component.css',
})
export class RssFeedComponent implements OnInit {
  private RSS_URL: string = 'http://localhost:9000/api/rss';
  private parser: Parser = new Parser();
  public feed: any;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get(this.RSS_URL).subscribe((data: any) => {
      this.parser.parseString(data.value).then((res) => {
        this.feed = res;
        //console.log(res);
      });
    });
  }
}
