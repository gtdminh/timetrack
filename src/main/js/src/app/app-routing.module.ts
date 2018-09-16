import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes : Routes = [
  {
    path: 'home',
    loadChildren: './modules/dashboard/dashboard.module#DashboardModule'
  }, {
    path: 'timesheet',
    loadChildren: './modules/timesheet/timesheet.module#TimesheetModule'
  }, {
    path: 'invoice',
    loadChildren: './modules/invoice/invoice.module#InvoiceModule'
  }, {
    path: 'profile',
    loadChildren: './modules/profile/profile.module#ProfileModule'
  }, {
    path: '**',
    redirectTo: '/home'
  }, {
    path: '',
    redirectTo: '',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {}