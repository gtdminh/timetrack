import {Component} from '@angular/core';

@Component({selector: 'app-root', templateUrl: './app.component.html', styleUrls: ['./app.component.scss']})
export class AppComponent {
  title = 'app';
  items: Object[] = [
    {
      name: 'Dashboard',
      path: '/home',
      icon: 'fas fa-home'
    }, {
      name: 'Profile',
      path: '/profile',
      icon: 'fas fa-user-cog'
    }, {
      name: 'Timesheet',
      path: '/timesheet',
      icon: 'far fa-calendar-alt'
    }, {
      name: 'Invoice',
      path: '/invoice',
      icon: 'fas fa-file-invoice-dollar'
    }
  ];
}
