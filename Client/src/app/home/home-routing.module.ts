import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';
import { HomePageComponent } from './home-page/home-page.component';
import { CreateNewProgramComponent } from './create-new-program/create-new-program.component';
import { ProgramsComponent } from './programs/programs.component';
import { AllProgramsComponent } from './all-programs/all-programs.component';
import { MyProgramsComponent } from './my-programs/my-programs.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', component: HomePageComponent },
      { path: 'programs/create-new', component: CreateNewProgramComponent },
      { path: 'programs', component: AllProgramsComponent },
      { path: 'my-programs', component: MyProgramsComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomeRoutingModule {}
