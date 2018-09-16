import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {InvoiceRoutingModule} from './invoice-routing.module';
import {InvoiceViewComponent} from './invoice-view/invoice-view.component';

@NgModule({
  imports: [
    CommonModule, InvoiceRoutingModule
  ],
  declarations: [InvoiceViewComponent]
})
export class InvoiceModule {}