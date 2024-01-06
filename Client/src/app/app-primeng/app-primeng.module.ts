import { NgModule } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { CardModule } from 'primeng/card';
import { MenubarModule } from 'primeng/menubar';
import { FileUploadModule } from 'primeng/fileupload';
import { PanelModule } from 'primeng/panel';
import { InputNumberModule } from 'primeng/inputnumber';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { DataViewModule } from 'primeng/dataview';
import { TagModule } from 'primeng/tag';
import { PaginatorModule } from 'primeng/paginator';

const primengModules = [
  InputTextModule,
  PasswordModule,
  ButtonModule,
  ToastModule,
  CardModule,
  MenubarModule,
  FileUploadModule,
  PanelModule,
  InputNumberModule,
  CalendarModule,
  DropdownModule,
  DataViewModule,
  TagModule,
  PaginatorModule,
];

@NgModule({
  imports: [...primengModules],
  exports: [...primengModules],
})
export class AppPrimengModule {}
