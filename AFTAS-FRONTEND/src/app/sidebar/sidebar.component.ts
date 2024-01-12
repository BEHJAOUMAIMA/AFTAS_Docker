import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  private sidebar: boolean | undefined;

  constructor() { }

  ngOnInit() {
    this.sidebar = false;
  }

  openSidebar() {
    this.sidebar = true;
  }

  closeSidebar() {
    this.sidebar = false;
  }

}
