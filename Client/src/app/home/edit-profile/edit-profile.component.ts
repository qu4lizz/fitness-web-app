import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { MessageService } from 'primeng/api';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SessionService } from '../../auth/services/session.service';
import { FileSelectEvent } from 'primeng/fileupload';
import { UserProfile } from '../../models/models';
import { UtilFunctions } from '../../utils/functions';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrl: './edit-profile.component.css',
})
export class EditProfileComponent implements OnInit {
  // info
  userInfo!: UserProfile;
  loadingInfo: boolean = false;
  userInfoForm: FormGroup;
  image?: any;
  showImg!: boolean;

  // password
  loadingPassword: boolean = false;
  passwordForm: FormGroup;

  constructor(
    public utilFunctions: UtilFunctions,
    private userService: UserService,
    private messageService: MessageService,
    private formBuilder: FormBuilder,
    private sessionService: SessionService
  ) {
    this.userInfoForm = this.formBuilder.group({
      name: [null, Validators.required],
      surname: [null, Validators.required],
      city: [null, Validators.required],
      mail: [null, [Validators.required, Validators.email]],
    });

    this.passwordForm = this.formBuilder.group({
      oldPassword: [null, Validators.required],
      newPassword: [null, Validators.required],
    });
  }

  ngOnInit(): void {
    this.loadInfo();
  }

  loadInfo() {
    this.userService
      .getUserInfoForEditing(this.sessionService.getUID()!)
      .subscribe({
        next: (res: any) => {
          console.log(res);
          this.userInfo = res;
          this.userInfoForm.patchValue({
            name: this.userInfo.name,
            surname: this.userInfo.surname,
            city: this.userInfo.city,
            mail: this.userInfo.mail,
          });
          this.image = this.userInfo.image;
          this.showImg = true;
        },
        error: (err: any) => {
          console.log(err);
        },
      });
  }

  changeInfo() {
    if (this.userInfoForm.valid) {
      const formData = new FormData();
      formData.append('name', this.userInfoForm.value.name);
      formData.append('surname', this.userInfoForm.value.surname);
      formData.append('city', this.userInfoForm.value.city);
      formData.append('mail', this.userInfoForm.value.mail);

      if (!this.showImg) {
        formData.append('image', this.image, this.image.name);
      }

      this.loadingInfo = true;

      this.userService
        .updateUser(this.sessionService.getUID()!, formData)
        .subscribe({
          error: (err: any) => {
            this.loadingInfo = false;
            this.messageService.add({
              severity: 'error',
              summary: 'Info Updating Failed',
              detail: 'Fields are not correctly filled',
            });
            console.log(err);
          },
          complete: () => {
            this.loadingInfo = false;
            this.loadInfo();
            this.messageService.add({
              severity: 'success',
              summary: 'Info Updating Successful',
              detail: 'You updated your info',
            });
          },
        });
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Info Updating Failed',
        detail: 'Fields are not correctly filled',
      });
    }
  }

  onImageSelect(event: FileSelectEvent) {
    console.log(event);
    this.image = event.files[0];
    this.showImg = false;
  }

  changePassword() {
    if (this.passwordForm.valid) {
      this.loadingPassword = true;

      this.userService
        .changePassword(this.sessionService.getUID()!, {
          oldPassword: this.passwordForm.value.oldPassword,
          newPassword: this.passwordForm.value.newPassword,
        })
        .subscribe({
          error: (err: any) => {
            this.loadingPassword = false;
            this.messageService.add({
              severity: 'error',
              summary: 'Password Invalid',
              detail: 'Old password does not match',
            });
          },
          complete: () => {
            this.loadingPassword = false;
            this.messageService.add({
              severity: 'success',
              summary: 'Password',
              detail: 'You changed your password successfully',
            });
          },
        });
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Form Invalid',
        detail: 'Passwords are not inputed correctly',
      });
    }
    this.passwordForm.reset();
  }
}
