import { NgModule } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { CardModule } from 'primeng/card';
import { MenubarModule } from 'primeng/menubar';
import { FileUploadModule } from 'primeng/fileupload';
import { PanelModule } from 'primeng/panel';

const primengModules = [
  InputTextModule,
  PasswordModule,
  ButtonModule,
  ToastModule,
  CardModule,
  MenubarModule,
  FileUploadModule,
  PanelModule,
];

@NgModule({
  imports: [...primengModules],
  exports: [...primengModules],
})
export class AppPrimengModule {}
