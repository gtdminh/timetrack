import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {DashboardRoutingModule} from './dashboard-routing.module';
import {DashboardViewComponent} from './dashboard-view/dashboard-view.component';

@NgModule({
  imports: [
    CommonModule, DashboardRoutingModule
  ],
  declarations: [DashboardViewComponent]
})
export class DashboardModule {}