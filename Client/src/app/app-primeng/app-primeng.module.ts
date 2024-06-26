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
import { GalleriaModule } from 'primeng/galleria';
import { AvatarModule } from 'primeng/avatar';
import { ImageModule } from 'primeng/image';
import { DividerModule } from 'primeng/divider';
import { MessagesModule } from 'primeng/messages';
import { DialogModule } from 'primeng/dialog';
import { ChartModule } from 'primeng/chart';
import { TableModule } from 'primeng/table';

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
  GalleriaModule,
  AvatarModule,
  ImageModule,
  DividerModule,
  MessagesModule,
  DialogModule,
  ChartModule,
  TableModule,
];

@NgModule({
  imports: [...primengModules],
  exports: [...primengModules],
})
export class AppPrimengModule {}
