import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { MessageService, SelectItem } from 'primeng/api';
import { DropdownChangeEvent } from 'primeng/dropdown';
import { ProgramService } from '../services/program.service';
import { SessionService } from '../../auth/services/session.service';

@Component({
  selector: 'app-participation-payment-modal',
  templateUrl: './participation-payment-modal.component.html',
  styleUrl: './participation-payment-modal.component.css',
})
export class ParticipationPaymentModalComponent {
  @Input() programId!: number;
  @Input() programLocation!: string;

  @ViewChild('inputCard') inputCard!: any;

  visible: boolean = false;

  paymentOptions!: SelectItem[];
  paymentValue!: string;

  constructor(
    private programService: ProgramService,
    private messageService: MessageService,
    private sessionService: SessionService
  ) {}

  showDialog() {
    this.visible = true;

    this.paymentOptions = [
      { label: 'Choose Payment Option', value: '' },
      { label: 'Card', value: 'card' },
      { label: 'PayPal', value: 'paypal' },
    ];
    if (this.programLocation.toLowerCase() !== 'online') {
      this.paymentOptions.push({ label: 'In Person', value: 'inperson' });
    }
  }

  closeDialog() {
    this.visible = false;
  }

  onPaymentChange(event: DropdownChangeEvent) {
    this.paymentValue = event.value;
  }

  buyProgram() {
    if (!this.paymentValue || this.paymentValue === '') {
      this.messageService.add({
        severity: 'error',
        summary: 'Payment Error',
        detail: 'Payment type not selected',
      });
      return;
    }

    if (
      (this.paymentValue === 'paypal' || this.paymentValue === 'card') &&
      !this.isCardNumber(this.inputCard.nativeElement.value)
    ) {
      this.messageService.add({
        severity: 'error',
        summary: 'Payment Error',
        detail: 'Invalid card number',
      });
      return;
    }

    this.programService
      .buyProgram({
        idUser: this.sessionService.getUID(),
        idProgram: this.programId,
        paymentType: this.paymentValue,
      })
      .subscribe({
        error: (err: any) => {
          this.messageService.add({
            severity: 'error',
            summary: 'Payment Error',
            detail: 'Transaction failed, you already participate this program',
          });
        },
        complete: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Payment Successful',
            detail: 'You now participate this program',
          });
          if (this.inputCard) this.inputCard.nativeElement.value = '';
          this.closeDialog();
        },
      });
  }

  isCardNumber(input: string) {
    return /^\d{4}$/.test(input.trim());
  }
}
