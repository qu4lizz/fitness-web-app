import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { AppPrimengModule } from '../app-primeng/app-primeng.module';
import { LayoutComponent } from './layout/layout.component';
import { RssCardComponent } from './rss-card/rss-card.component';
import { HomePageComponent } from './home-page/home-page.component';
import { RssFeedComponent } from './rss-feed/rss-feed.component';
import { TopTenComponent } from './top-ten/top-ten.component';
import { TopTenCardComponent } from './top-ten-card/top-ten-card.component';
import { CreateNewProgramComponent } from './create-new-program/create-new-program.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { ProgramsComponent } from './programs/programs.component';

@NgModule({
  declarations: [
    LayoutComponent,
    RssCardComponent,
    HomePageComponent,
    RssFeedComponent,
    TopTenComponent,
    TopTenCardComponent,
    CreateNewProgramComponent,
    ProgramsComponent,
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    AppPrimengModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class HomeModule {}