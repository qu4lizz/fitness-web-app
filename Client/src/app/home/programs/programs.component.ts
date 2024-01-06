import {
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
  ViewChild,
} from '@angular/core';
import { Category, Difficulty, ProgramDataView } from '../../models/models';
import { UtilFunctions } from '../../utils/functions';
import { ProgramService } from '../services/program.service';
import { SessionService } from '../../auth/services/session.service';
import { DataView, DataViewPageEvent } from 'primeng/dataview';
import { first } from 'rxjs';
import { MessageService, SelectItem } from 'primeng/api';
import { CategoryService } from '../services/category.service';
import { DifficultyService } from '../services/difficulty.service';
import { DropdownChangeEvent } from 'primeng/dropdown';

@Component({
  selector: 'app-programs',
  templateUrl: './programs.component.html',
  styleUrl: './programs.component.css',
})
export class ProgramsComponent implements OnInit {
  @Input() public type!: 'all' | 'my' | 'participated';
  @ViewChild('dv', { static: false }) dataView!: DataView;

  programs?: ProgramDataView[];
  categories!: Category[];
  difficulties!: Difficulty[];
  dateStatusOptions!: SelectItem[];

  buyButtonDisabled!: boolean;

  // pagination
  page: number = 0;
  rows: number = 2;
  totalRecords: number = 0;

  // sorting
  sortOptions!: SelectItem[];
  sortOrder?: number;
  sortField?: string;

  // filtering
  idCategory?: number;
  idDifficulty?: number;
  dateStatus?: string;

  currentDate!: Date;

  constructor(
    public utilFunctions: UtilFunctions,
    private programService: ProgramService,
    public sessionsService: SessionService,
    private categoryService: CategoryService,
    private difficultyService: DifficultyService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.refreshPrograms();

    this.buyButtonDisabled = !this.sessionsService.getUID();
    this.currentDate = new Date();

    this.sortOptions = [
      { label: 'No Sorting', value: '' },
      { label: 'Price Low to High', value: 'price' },
      { label: 'Price High to Low', value: '!price' },
      { label: 'Category A-Z', value: 'category.name' },
      { label: 'Category Z-A', value: '!category.name' },
      { label: 'Start Date Ascending', value: 'start' },
      { label: 'Start Date Descending', value: '!start' },
    ];

    if (this.type === 'my' || this.type === 'participated') {
      this.dateStatusOptions = [
        { label: 'All', value: 'all' },
        { label: 'Completed', value: 'completed' },
        { label: 'Upcoming', value: 'upcoming' },
      ];
    }

    this.categoryService.getAll().subscribe({
      next: (res: any) => {
        this.categories = res;
      },
    });

    this.difficultyService.getAll().subscribe({
      next: (res: any) => {
        this.difficulties = res;
      },
    });
  }

  onPageChange(event: DataViewPageEvent): void {
    this.rows = event.rows;
    this.page = Math.floor(event.first / this.rows);
    this.refreshPrograms();
  }

  refreshPrograms(): void {
    let queryString = `?page=${this.page}&size=${
      this.rows
    }${this.getFieldsToSort()}${this.getCategoryFilter()}${this.getDificultyFilter()}`;

    if (this.type === 'all') {
      this.programService.getAll(queryString).subscribe({
        next: (res: any) => {
          this.programs = res.content;
          this.totalRecords = res.totalElements;
        },
        error: (err: any) => {
          console.log(err);
        },
      });
    } else if (this.type === 'my') {
      queryString +=
        `&idUser=${this.sessionsService.getUID()}` + this.getDateStatusFilter();
      console.log(queryString);
      this.programService.getAllByMe(queryString).subscribe({
        next: (res: any) => {
          this.programs = res.content;
          this.totalRecords = res.totalElements;
        },
        error: (err: any) => {
          console.log(err);
        },
      });
    } else if (this.type === 'participated') {
    } else {
      throw new Error('not correct type');
    }
  }

  onSortChange(event: any) {
    const value = event.value;

    if (value === '') {
      this.sortField = undefined;
      this.sortOrder = undefined;
    }

    if (value.indexOf('!') === 0) {
      this.sortOrder = -1;
      this.sortField = value.substring(1, value.length);
    } else {
      this.sortOrder = 1;
      this.sortField = value;
    }

    this.resetPage();

    this.refreshPrograms();
  }

  getFieldsToSort() {
    if (!this.sortField || !this.sortOrder) return '';
    const direction = this.sortOrder === 1 ? 'ASC' : 'DESC';
    return `&sort=${this.sortField},${direction}`;
  }

  onCategoryChange(event: DropdownChangeEvent) {
    this.idCategory = event.value;

    this.resetPage();

    this.refreshPrograms();
  }

  getCategoryFilter() {
    if (!this.idCategory) return '';
    else return `&idCategory=${this.idCategory}`;
  }

  onDifficultyChange(event: DropdownChangeEvent) {
    this.idDifficulty = event.value;

    this.resetPage();

    this.refreshPrograms();
  }

  getDificultyFilter() {
    if (!this.idDifficulty) return '';
    else return `&idDifficulty=${this.idDifficulty}`;
  }

  onDateStatusChange(event: DropdownChangeEvent) {
    this.dateStatus = event.value;

    this.resetPage();

    this.refreshPrograms();
  }

  getDateStatusFilter() {
    if (!this.dateStatus || this.dateStatus === 'all') return '';
    else return `&dateStatus=${this.dateStatus}`;
  }

  resetPage() {
    this.page = 0;
    this.dataView.paginate({
      first: 0,
      page: this.page,
      rows: this.rows,
      pageCount: Math.ceil(this.totalRecords / this.rows),
    });
  }

  navigateToProgram(programId: number) {
    console.log(programId);
    // TODO
  }

  deleteProgram(program: ProgramDataView) {
    this.programService.delete(program.id).subscribe({
      error: (err: any) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Deletion Failed',
          detail: `Trying to delete "${program.name}" failed`,
        });
      },
      complete: () => {
        this.messageService.add({
          severity: 'success',
          summary: 'Deletion Successful',
          detail: `You have successfully deleted "${program.name}"`,
        });

        this.refreshPrograms();
      },
    });
  }

  getSeverity(program: ProgramDataView) {
    switch (program.difficulty.name.toLowerCase()) {
      case 'beginner':
        return 'success';
      case 'intermediate':
        return 'warning';
      case 'expert':
        return 'danger';
      default:
        return undefined;
    }
  }

  isOver(date: Date): boolean {
    return new Date(date) < this.currentDate;
  }
}
