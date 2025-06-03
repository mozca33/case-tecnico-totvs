import { Component, OnInit, OnDestroy, ViewChild, Inject } from '@angular/core';
import { Subject } from 'rxjs';

import { PoTableColumn, PoTableAction, PoDialogService, PoNotificationService, PoModalComponent, PoTableComponent } from '@po-ui/ng-components';

import { Client } from '../../models/client.model';
import { ClientService } from '../../services/client.service';
import { CLIENT_TABLE_COLUMNS, getClientTableActions } from '../../../../shared/constants/columns.constants';
import { ClientViewModalComponent } from './modals/client-view/client-view-modal.component';
import { ClientFormModalComponent } from './modals/client-form/client-form-modal.component';

@Component({
  selector: 'app-client-list',
  standalone: false,
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss']
})
export class ClientListComponent implements OnInit, OnDestroy {
  @ViewChild('formClientModal', { static: true }) formClientModal!: ClientFormModalComponent;
  @ViewChild('viewClientModal', { static: true }) viewClientModal!: ClientViewModalComponent;
  @ViewChild(PoTableComponent, { static: true }) poTable!: PoTableComponent;

  clients: Client[] = [];
  filteredClients: Client[] = [];
  searchValue: string = '';
  loading: boolean = false;
  error: string = '';
  tableActions: PoTableAction[] = [];
  columns: PoTableColumn[] = CLIENT_TABLE_COLUMNS
  private searchSubject = new Subject<string>();
  selectedClient: Client | null = null;
  properties: Array<string> = ['hideBatchActions', 'hideTableSearch'];
  total: number = 0;
  

  constructor(
    private clientService: ClientService,
    @Inject(PoDialogService) private poDialog: PoDialogService,
    private poNotification: PoNotificationService
  ) {}

  ngOnInit(): void {
    this.loadClients();
    this.tableActions = getClientTableActions(
      this.viewClient.bind(this),
      this.editClient.bind(this),
      this.deleteClient.bind(this)
    );
  }

  ngOnDestroy(): void {
    this.searchSubject.complete();
  }

  loadClients(): void {
    this.loading = true;
    this.clientService.getClients().subscribe({
      next: (data) => {
        this.clients = data;
        this.filteredClients = [...this.clients];
        this.loading = false;
      },
      error: (error) => {
        this.loading = false;
        this.poNotification.error(error);
      }
    });
  }

  async createNewClient(): Promise<void>{
     const emptyClient: Client = {
      id: '',
      name: '',
      cpf: '',
      phones: [],
      addresses: [],
    };
    if (this.formClientModal) {
      const clientSubmitted: Client | undefined = await this.formClientModal.openModal(emptyClient);

      if (clientSubmitted) {
        this.loadClients();
      }
    }
  }

  async editClient(client: Client): Promise<void> {
    if (this.formClientModal) {
      const clientSubmitted: Client | undefined = await this.formClientModal.openModal(client);
      if (clientSubmitted) {
        this.loadClients();
      }
    }
  }

  delete(clients: Array<Client>): void {
    clients.forEach(client => {
      this.clientService.deleteClient(client.id).subscribe({
        next: () => {
          this.loading = false;
          this.loadClients();
        },
        error: (error) => {
          this.loading = false;
          console.error('Error: ', error);
          this.poDialog.alert({
            title: 'Error',
            message: error
          });
        }
      });
    })
    this.poNotification.success('Client(s) deleted successfully!');
    
  }

  viewClient(client: Client): void {
    this.selectedClient = client;
    if (this.viewClientModal) {
      this.viewClientModal.openModal(this.selectedClient);
    }
  }

  closeViewClientModal(): void {
    if (this.viewClientModal) {
      this.viewClientModal.closeModal();
      this.selectedClient = null;
    }
  }

  decreaseTotal(row: any) {
    if (row.value) {
      this.total -= row.value;
    }
  }

  deleteItems(clients: Array<Client>) {
    this.clients = clients;
  }

  sumTotal(row: any) {
    if (row.value) {
      this.total += row.value;
    }
  }

  private deleteClient(clients: Array<Client>): void {
    this.loading = true;
    const selectedItems = this.poTable.getSelectedRows();

    if (selectedItems.length > 0) {
      this.poDialog.confirm({
        title: 'Confirm Delete',
        message: `Are you sure you want to delete the selected client(s)?`,
        confirm: () => this.delete(clients),
        cancel: () => {}
      });
    }
  }
}