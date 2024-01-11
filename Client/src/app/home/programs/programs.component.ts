import {
  ChangeDetectorRef,
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
  ViewChild,
} from '@angular/core';
import {
  Category,
  Difficulty,
  ProgramDataView,
  SubscribedCategories,
} from '../../models/models';
import { UtilFunctions } from '../../utils/functions';
import { ProgramService } from '../services/program.service';
import { SessionService } from '../../auth/services/session.service';
import { DataView, DataViewPageEvent } from 'primeng/dataview';
import { first } from 'rxjs';
import { MessageService, SelectItem } from 'primeng/api';
import { CategoryService } from '../services/category.service';
import { DifficultyService } from '../services/difficulty.service';
import { DropdownChangeEvent } from 'primeng/dropdown';
import { Router } from '@angular/router';
import { ParticipationPaymentModalComponent } from '../participation-payment-modal/participation-payment-modal.component';

@Component({
  selector: 'app-programs',
  templateUrl: './programs.component.html',
  styleUrl: './programs.component.css',
})
export class ProgramsComponent implements OnInit {
  @Input() public type!: 'all' | 'my' | 'participated';
  @ViewChild('dv', { static: false }) dataView!: DataView;
  @ViewChild(ParticipationPaymentModalComponent)
  participationPaymentModal!: ParticipationPaymentModalComponent;

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

  subscribedCategories!: SubscribedCategories[];

  constructor(
    public utilFunctions: UtilFunctions,
    private programService: ProgramService,
    public sessionsService: SessionService,
    private categoryService: CategoryService,
    private difficultyService: DifficultyService,
    private messageService: MessageService,
    private router: Router,
    private cdref: ChangeDetectorRef
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

    this.getSubsribedCategories();
  }

  getSubsribedCategories() {
    this.categoryService
      .getSubscribedCategories(this.sessionsService.getUID()!)
      .subscribe({
        next: (res: any) => {
          console.log('subs:', res);
          this.subscribedCategories = res;
        },
        error: (err: any) => {
          console.log(err);
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
      queryString +=
        `&idUser=${this.sessionsService.getUID()}` + this.getDateStatusFilter();

      this.programService.getAllParticipated(queryString).subscribe({
        next: (res: any) => {
          this.programs = res.content;
          this.totalRecords = res.totalElements;
        },
        error: (err: any) => {
          console.log(err);
        },
      });
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

  subscribeToCategory(subscribe: boolean) {
    this.categoryService
      .subscribe({
        idUser: this.sessionsService.getUID(),
        idCategory: this.idCategory,
        subscribe: subscribe,
      })
      .subscribe({
        error: (err: any) => {
          console.log(err);
        },
        complete: () => {
          if (subscribe) {
            this.messageService.add({
              severity: 'success',
              summary: 'Successfully Subscribed',
              detail: 'You have subscribed to new category',
            });
          } else {
            this.messageService.add({
              severity: 'success',
              summary: 'Successfully Unsubscribed',
              detail: 'You have unsubscribed from category',
            });
          }

          this.getSubsribedCategories();
        },
      });
  }

  isCategorySubscribed(idCategory: number): boolean {
    return this.subscribedCategories.some((c) => c.idCategory === idCategory);
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
    this.router.navigate([`/programs/${programId}`]);
  }

  buyProgramId: any;
  buyProgramLocation: any;

  buyProgram(program: any) {
    this.buyProgramId = program.id;
    this.buyProgramLocation = program.location;

    this.cdref.detectChanges();

    this.participationPaymentModal.showDialog();
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
