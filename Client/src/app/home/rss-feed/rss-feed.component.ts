import { Component, OnInit } from '@angular/core';
import Parser from 'rss-parser';
import { RssService } from '../services/rss.service';

@Component({
  selector: 'app-rss-feed',
  templateUrl: './rss-feed.component.html',
  styleUrl: './rss-feed.component.css',
})
export class RssFeedComponent implements OnInit {
  private parser: Parser = new Parser();
  public feed: any;

  constructor(private rssService: RssService) {}

  ngOnInit(): void {
    this.rssService.getRSS().subscribe({
      error: (err: any) => console.log(err),
      next: (res: any) => {
        this.parser.parseString(res.value).then((res) => {
          this.feed = res;
          console.log(this.feed);
        });
      },
    });
  }
}
