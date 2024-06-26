import { Component } from '@angular/core';
import { MenuItem, PrimeIcons } from 'primeng/api';
import { SessionService } from '../auth/services/session.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent {
  items: MenuItem[] | undefined;
  private session: SessionService;

  constructor(private sessionService: SessionService, private router: Router) {
    this.session = sessionService;
  }

  ngOnInit(): void {
    this.session.sessionStateChanged.subscribe(() => {
      this.handleSessionStateChanged();
    });

    this.handleSessionStateChanged();

    this.initItems();
  }

  private handleSessionStateChanged() {
    this.initItems();
  }

  private initItems() {
    const uid = this.session.getUID();
    const username = this.sessionService.getUsername();

    if (uid) {
      this.items = [
        {
          label: 'Home',
          icon: PrimeIcons.HOME,
          routerLink: '/',
        },
        {
          label: 'Programs',
          icon: PrimeIcons.LIST,
          items: [
            {
              label: 'All Programs',
              icon: PrimeIcons.LIST,
              routerLink: '/programs',
            },
            {
              label: 'Create New',
              icon: PrimeIcons.PLUS_CIRCLE,
              routerLink: '/programs/create-new',
            },
            {
              label: 'My Programs',
              icon: PrimeIcons.USER_EDIT,
              routerLink: '/my-programs',
            },
            {
              label: 'Programs Participation',
              icon: PrimeIcons.TABLE,
              routerLink: '/programs-participation',
            },
          ],
        },
        {
          label: 'Counseling',
          icon: PrimeIcons.COMMENT,
          routerLink: '/counseling',
        },
        {
          label: 'My Activities',
          icon: PrimeIcons.BOOK,
          routerLink: '/my-activities',
        },
        {
          label: 'Messages',
          icon: PrimeIcons.COMMENTS,
          routerLink: '/messages',
        },
        {
          label: `${username}'s Profile`,
          icon: PrimeIcons.USER,
          items: [
            {
              label: 'Edit Profile',
              icon: PrimeIcons.USER_EDIT,
              routerLink: '/edit-profile',
            },
            {
              label: 'Logout',
              icon: PrimeIcons.SIGN_OUT,
              command: () => {
                this.sessionService.removeUID();
                this.router.navigate(['']);
              },
            },
          ],
        },
      ];
    } else {
      this.items = [
        {
          label: 'Home',
          icon: PrimeIcons.HOME,
          routerLink: '/',
        },
        {
          label: 'All Programs',
          icon: PrimeIcons.LIST,
          routerLink: '/programs',
        },
        {
          label: 'Login',
          icon: PrimeIcons.SIGN_IN,
          routerLink: '/auth/login',
        },
        {
          label: 'Register',
          icon: PrimeIcons.USER_PLUS,
          routerLink: '/auth/register',
        },
      ];
    }
  }
}
