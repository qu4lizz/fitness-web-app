import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivityService } from '../services/activity.service';
import { SessionService } from '../../auth/services/session.service';
import { MessageService } from 'primeng/api';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UtilFunctions } from '../../utils/functions';
import { Activity } from '../../models/models';

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html',
  styleUrl: './activities.component.css',
})
export class ActivitiesComponent implements OnInit {
  activities!: Activity[];

  data: any;
  options: any;

  labels: any = [];
  dataChart: any = [];

  visible: boolean = false;

  form!: FormGroup;

  constructor(
    private activityService: ActivityService,
    private sessionService: SessionService,
    private messageService: MessageService,
    private formBuilder: FormBuilder,
    public utilFunctions: UtilFunctions,
    private changeDetectorRef: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      exerciseType: [null, Validators.required],
      duration: [null, Validators.required],
      intensity: [null, Validators.required],
      result: [null, Validators.required],
      timestamp: new Date(),
      idUser: this.sessionService.getUID(),
    });

    this.initActivities(this.sessionService.getUID()!);
  }

  initActivities(uid: number) {
    this.activityService.getActivitiesOfUser(uid).subscribe({
      next: (res: any) => {
        this.activities = res;
        this.labels = [];
        this.dataChart = [];

        this.activities.forEach((a: any) => {
          this.labels.push(this.utilFunctions.formatDateWithDay(a.timestamp));
          this.dataChart.push(Number(a.result));
        });

        this.initChart();
        this.changeDetectorRef.detectChanges();
      },
      error: (err: any) => console.log(err),
    });
  }

  downloadPDF() {
    this.activityService.getPDF(this.sessionService.getUID()!);
  }

  initChart() {
    const documentStyle = getComputedStyle(document.documentElement);
    const textColor = documentStyle.getPropertyValue('--text-color');
    const textColorSecondary = documentStyle.getPropertyValue(
      '--text-color-secondary'
    );
    const surfaceBorder = documentStyle.getPropertyValue('--surface-border');

    this.data = {
      labels: this.labels,
      datasets: [
        {
          label: 'Kilograms',
          data: this.dataChart,
          fill: false,
          borderColor: documentStyle.getPropertyValue('--blue-500'),
          tension: 0.4,
        },
      ],
    };

    this.options = {
      maintainAspectRatio: true,
      aspectRatio: 2,
      plugins: {
        legend: {
          labels: {
            color: textColor,
          },
        },
      },
      scales: {
        x: {
          ticks: {
            color: textColorSecondary,
          },
          grid: {
            color: surfaceBorder,
            drawBorder: false,
          },
        },
        y: {
          ticks: {
            color: textColorSecondary,
          },
          grid: {
            color: surfaceBorder,
            drawBorder: false,
          },
        },
      },
    };
  }

  showDialog() {
    this.visible = true;
  }

  closeDialog() {
    this.visible = false;
  }

  addNewActivity() {
    this.closeDialog();

    if (this.form.valid) {
      this.activityService.addActivity(this.form.value).subscribe({
        error: (err: any) => {
          console.log(err);
          this.messageService.add({
            severity: 'error',
            summary: 'Adding New Activity Error',
            detail: 'Activity could not be added',
          });
        },
        complete: () => {
          this.initActivities(this.sessionService.getUID()!);
        },
      });
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Adding New Activity Error',
        detail: 'Form is not valid',
      });
    }
  }
}
