import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { AppPrimengModule } from '../app-primeng/app-primeng.module';
import { LayoutComponent } from './layout/layout.component';

@NgModule({
  declarations: [LayoutComponent],
  imports: [CommonModule, HomeRoutingModule, AppPrimengModule],
})
export class HomeModule {}
