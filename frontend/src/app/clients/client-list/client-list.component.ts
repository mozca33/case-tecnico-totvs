import { Component, OnInit } from '@angular/core';
import {
  PoTableColumn,
  PoNotificationService,
  PoPageModule,
  PoTableModule,
  PoButtonModule,
  PoNotificationModule,
  PoDynamicModule,
} from '@po-ui/ng-components';
import { Router } from '@angular/router';
import { ClientService } from '../services/client.service';
import { Client } from '../models/client.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  standalone: true,
  imports: [
    CommonModule,
    PoPageModule,
    PoTableModule,
    PoButtonModule,
    PoNotificationModule,
    PoDynamicModule,
  ],
})
export class ClientListComponent implements OnInit {
  clients: Client[] = [];
  readonly columns: PoTableColumn[] = [
    { property: 'name', label: 'Nome' },
    { property: 'email', label: 'Email' },
    { property: 'phone', label: 'Telefone' },
    { property: 'address', label: 'Endereço' },
    {
      property: 'actions',
      label: 'Ações',
      type: 'icon',
      icons: [
        {
          value: 'true',
          action: this.editClient.bind(this),
          icon: 'po-icon-edit',
          tooltip: 'Editar',
        },
        {
          value: 'true',
          action: this.deleteClient.bind(this),
          icon: 'po-icon-delete',
          tooltip: 'Excluir',
        },
      ],
    },
  ];

  constructor(
    private clientService: ClientService,
    private notification: PoNotificationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients() {
    this.clientService
      .getClients()
      .subscribe((clients) => (this.clients = clients));
  }

  editClient(client: Client) {
    this.router.navigate(['/clients/form', client.id]);
  }

  deleteClient(client: Client) {
    if (confirm(`Deseja excluir o cliente ${client.name}?`)) {
      this.clientService.deleteClient(client.id!).subscribe(() => {
        this.notification.success('Cliente excluído com sucesso');
        this.loadClients();
      });
    }
  }

  newClient() {
    this.router.navigate(['/clients/form']);
  }
}
