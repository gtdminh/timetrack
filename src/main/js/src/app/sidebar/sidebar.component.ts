import {Component, HostBinding, Input, OnInit} from '@angular/core';

@Component({selector: 'app-sidebar', templateUrl: './sidebar.component.html', styleUrls: ['./sidebar.component.scss']})
export class SidebarComponent implements OnInit {
  @HostBinding('attr.class') cssClass = 'column  is-4-tablet is-3-desktop is-1-widescreen sidebar sidebar-default ';

  @Input('items') items: Array<Object>;
  constructor() {}

  ngOnInit() {}

}
