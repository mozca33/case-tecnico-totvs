import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PoPageModule, PoModule } from '@po-ui/ng-components';
import { ClientService } from './clients/services/client.service';
import { Client } from './clients/models/client.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, PoPageModule, PoModule],
  template: `
    <po-page-default p-title="Clientes">
      <po-button
        p-label="Buscar clientes"
        (p-click)="loadClients()"
      ></po-button>
      <po-table [p-items]="clients" [p-columns]="columns"></po-table>
    </po-page-default>
  `,
})
export class AppComponent {
  clients: Client[] = [];
  columns = [
    { property: 'name', label: 'Nome' },
    { property: 'cpf', label: 'CPF' },
    { property: 'phones', label: 'Telefones' },
    { property: 'addresses', label: 'Endereços' },
  ];

  constructor(private clientService: ClientService) {}

  loadClients() {
    this.clientService.getClients().subscribe((data) => {
      this.clients = data;
    });
  }
}
