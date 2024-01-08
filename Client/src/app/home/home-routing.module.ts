import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';
import { HomePageComponent } from './home-page/home-page.component';
import { CreateNewProgramComponent } from './create-new-program/create-new-program.component';
import { AllProgramsComponent } from './all-programs/all-programs.component';
import { MyProgramsComponent } from './my-programs/my-programs.component';
import { ProgramDetailsComponent } from './program-details/program-details.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { MessageCounselorComponent } from './message-counselor/message-counselor.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', component: HomePageComponent },
      { path: 'programs/create-new', component: CreateNewProgramComponent },
      { path: 'programs', component: AllProgramsComponent },
      { path: 'my-programs', component: MyProgramsComponent },
      { path: 'programs/:id', component: ProgramDetailsComponent },
      { path: 'edit-profile', component: EditProfileComponent },
      { path: 'counseling', component: MessageCounselorComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomeRoutingModule {}
