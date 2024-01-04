import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { AppPrimengModule } from '../app-primeng/app-primeng.module';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LayoutComponent } from './layout/layout.component';
import { RegisterComponent } from './register/register.component';
import { MailConfirmationComponent } from './mail-confirmation/mail-confirmation.component';

@NgModule({
  declarations: [LayoutComponent, LoginComponent, RegisterComponent, MailConfirmationComponent],
  imports: [
    CommonModule,
    AuthRoutingModule,
    AppPrimengModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class AuthModule {}
