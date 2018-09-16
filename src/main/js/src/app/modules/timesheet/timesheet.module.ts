import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {TimesheetRoutingModule} from './timesheet-routing.module';
import {TimesheetViewComponent} from './timesheet-view/timesheet-view.component';

@NgModule({
  imports: [
    CommonModule, TimesheetRoutingModule
  ],
  declarations: [TimesheetViewComponent]
})
export class TimesheetModule {}