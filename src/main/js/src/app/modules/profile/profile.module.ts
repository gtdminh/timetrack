import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ProfileRoutingModule} from './profile-routing.module';
import {ProfileViewComponent} from './profile-view/profile-view.component';

@NgModule({
  imports: [
    CommonModule, ProfileRoutingModule
  ],
  declarations: [ProfileViewComponent]
})
export class ProfileModule {}