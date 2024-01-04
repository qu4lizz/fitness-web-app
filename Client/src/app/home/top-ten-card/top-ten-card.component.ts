import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-top-ten-card',
  templateUrl: './top-ten-card.component.html',
  styleUrl: './top-ten-card.component.css',
})
export class TopTenCardComponent {
  @Input() public name?: string;
  @Input() public difficulty?: string;
  @Input() public instructions?: string;

  constructor() {}
}
