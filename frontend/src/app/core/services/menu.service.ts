// src/app/core/services/menu.service.ts
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { PoMenuItem } from '@po-ui/ng-components';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  constructor(private router: Router) {}

  getMainMenuItems(): PoMenuItem[] {
    return [
      {
        label: 'Início',
        icon: 'po-icon-home',
        shortLabel: 'Início',
        action: () => this.router.navigate(['/'])
      },
      {
        label: 'Clientes',
        icon: 'po-icon-users',
        shortLabel: 'Clientes',
        action: () => this.router.navigate(['/clients'])
      },
      {
        label: 'Relatórios',
        icon: 'po-icon-chart-columns',
        shortLabel: 'Relatórios',
        action: () => this.router.navigate(['/reports'])
      }
    ];
  }
}