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

export class ProgramDetailsResponse {
  id: number;
  name: string;
  description: string;
  price: number;
  duration: number;
  start: Date;
  location: string;
  videoUrl: string;
  active: boolean;
  difficulty: Difficulty;
  category: Category;
  instructor: User;
  programImages: ProgramImage[];
  comments: UserCommentDTO[];

  constructor(
    id: number,
    name: string,
    description: string,
    price: number,
    duration: number,
    start: Date,
    location: string,
    videoUrl: string,
    active: boolean,
    difficulty: Difficulty,
    category: Category,
    instructor: User,
    programImages: ProgramImage[],
    comments: UserCommentDTO[]
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.duration = duration;
    this.start = start;
    this.location = location;
    this.videoUrl = videoUrl;
    this.active = active;
    this.difficulty = difficulty;
    this.category = category;
    this.instructor = instructor;
    this.programImages = programImages;
    this.comments = comments;
  }
}

export class User {
  id: number;
  name: string;
  surname: string;
  city: string;
  username: string;
  password: string;
  mail: string;
  image: number[];
  verified: boolean;
  active: boolean;

  constructor(
    id: number,
    name: string,
    surname: string,
    city: string,
    username: string,
    password: string,
    mail: string,
    image: number[],
    verified: boolean,
    active: boolean
  ) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.city = city;
    this.username = username;
    this.password = password;
    this.mail = mail;
    this.image = image;
    this.verified = verified;
    this.active = active;
  }
}

export class UserCommentDTO {
  user: User;
  id: number;
  comment: string;
  timestamp: string;

  constructor(user: User, id: number, comment: string, timestamp: string) {
    this.user = user;
    this.id = id;
    this.comment = comment;
    this.timestamp = timestamp;
  }
}
