export class ProgramDataView {
  id: number;
  name: string;
  price: number;
  start: Date;
  duration: number;
  idInstructor: number;
  difficulty: Difficulty;
  category: Category;
  programImages: ProgramImage;

  constructor(
    id: number,
    name: string,
    price: number,
    start: Date,
    duration: number,
    idUser: number,
    difficulty: Difficulty,
    category: Category,
    programImages: ProgramImage
  ) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.start = start;
    this.duration = duration;
    this.idInstructor = idUser;
    this.difficulty = difficulty;
    this.category = category;
    this.programImages = programImages;
  }
}

export class Difficulty {
  id: number;
  name: string;

  constructor(id: number, name: string) {
    this.id = id;
    this.name = name;
  }
}

export class Category {
  id: number;
  name: string;

  constructor(id: number, name: string) {
    this.id = id;
    this.name = name;
  }
}

export class ProgramImage {
  id: number;
  image: Blob;

  constructor(id: number, image: Blob) {
    this.id = id;
    this.image = image;
  }
}

export interface PageEvent {
  first: number;
  rows: number;
  page: number;
  pageCount: number;
}
