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
import { ChatsComponent } from './chats/chats.component';
import { SingleChatComponent } from './single-chat/single-chat.component';
import { ActivitiesComponent } from './activities/activities.component';
import { GuardService } from '../auth/services/guard.service';
import { ParticipatedProgramsComponent } from './participated-programs/participated-programs.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', component: HomePageComponent },
      {
        path: 'programs/create-new',
        component: CreateNewProgramComponent,
        canActivate: [GuardService],
      },
      { path: 'programs', component: AllProgramsComponent },
      {
        path: 'my-programs',
        component: MyProgramsComponent,
        canActivate: [GuardService],
      },
      { path: 'programs/:id', component: ProgramDetailsComponent },
      {
        path: 'edit-profile',
        component: EditProfileComponent,
        canActivate: [GuardService],
      },
      {
        path: 'counseling',
        component: MessageCounselorComponent,
        canActivate: [GuardService],
      },
      {
        path: 'messages',
        component: ChatsComponent,
        canActivate: [GuardService],
      },
      {
        path: 'messages/:id',
        component: SingleChatComponent,
        canActivate: [GuardService],
      },
      {
        path: 'my-activities',
        component: ActivitiesComponent,
        canActivate: [GuardService],
      },
      {
        path: 'programs-participation',
        component: ParticipatedProgramsComponent,
        canActivate: [GuardService],
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomeRoutingModule {}
