import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-sidebar-item',
  templateUrl: './sidebar-item.component.html',
  styleUrls: ['./sidebar-item.component.sass'],
  inputs: []

})
export class SidebarItemComponent implements OnInit {

  @Input('item') item: Object;

  constructor() {
  }

  ngOnInit() {
  }

}
