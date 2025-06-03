import { Component, OnInit } from '@angular/core';
import { PoMenuItem } from '@po-ui/ng-components';
import { MAIN_MENU_ITEMS } from './shared/constants/menu.constants';
import { SamplePoMenuHumanResourcesService } from './core/services/sample-po-menu-human-resources.service';

@Component({
  selector: 'app-root',
  standalone: false,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'TOTVS Case Técnico';
  isMenuCollapsed = false;
  menuItemSelected: string = "";
  isFilter: boolean = false;

  constructor(public samplePoMenuHumanResourcesService: SamplePoMenuHumanResourcesService) {}

  ngOnInit(): void {
  }

  toggleMenu(): void {
    this.isMenuCollapsed = !this.isMenuCollapsed;
  }

  menus: Array<PoMenuItem> = [
    { label: 'Client', icon: 'an an-user', shortLabel: 'Register', link: '/clients' },
  ];
}
