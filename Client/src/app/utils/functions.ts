import { DatePipe } from '@angular/common';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UtilFunctions {
  private datePipe: DatePipe = new DatePipe('en-US');
  constructor() {}

  public formatDate(date: Date, format: string = 'dd.MM.yyyy.'): string | null {
    return this.datePipe.transform(date, format);
  }

  public getImageSrc(base64Image: any): string {
    const imageData = Uint8Array.from(atob(base64Image), (char) =>
      char.charCodeAt(0)
    );

    let imageType = 'jpeg';
    if (
      imageData[0] === 0x89 &&
      imageData[1] === 0x50 &&
      imageData[2] === 0x4e &&
      imageData[3] === 0x47
    ) {
      imageType = 'png';
    }

    return `data:image/${imageType};base64,${base64Image}`;
  }

  public convertBlobToUrl(image: Blob): string {
    return URL.createObjectURL(image);
  }
}
