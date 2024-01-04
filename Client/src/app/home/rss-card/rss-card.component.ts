import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-rss-card',
  templateUrl: './rss-card.component.html',
  styleUrl: './rss-card.component.css',
})
export class RssCardComponent {
  @Input() public category?: string;
  @Input() public title?: string;
  @Input() public description?: string;
  @Input() public link?: string;

  constructor() {}

  openLink($event: MouseEvent) {
    window.open(this.link, '_blank');
  }
}
