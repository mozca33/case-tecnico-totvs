import { Client } from './../../../../models/client.model';
import { Component, Input, ViewChild } from '@angular/core';
import { PoModalComponent } from '@po-ui/ng-components';

@Component({
  selector: 'app-client-view-modal',
  standalone: false,
  templateUrl: './client-view-modal.component.html'
})
export class ClientViewModalComponent {
  @ViewChild('clientModal', { static: true }) modal!: PoModalComponent;
  @Input() client!: Client;

  openModal(clientData: any): void {
    this.client = clientData;
    console.log(this.client.addresses);
    this.modal.open();
  }

  closeModal(): void {
    this.modal.close();
  }
}