import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  PoNotificationService,
  PoPageModule,
  PoTableModule,
  PoButtonModule,
  PoNotificationModule,
  PoDynamicModule,
} from '@po-ui/ng-components';
import { ClientService } from '../services/client.service';
import { Client } from '../models/client.model';
import { CommonModule } from '@angular/common';
import { ClientsRoutingModule } from '../client-routing.module';

@Component({
  selector: 'app-client-form',
  templateUrl: './client-form.component.html',
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
export class ClientFormComponent implements OnInit {
  client: Client = { name: '', email: '', phone: '', address: '' };
  isEdit = false;

  constructor(
    private service: ClientService,
    private notification: PoNotificationService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.service
        .getClientById(id)
        .subscribe((client) => (this.client = client));
    }
  }

  save() {
    const request = this.isEdit
      ? this.service.updateClient(this.client.id!, this.client)
      : this.service.createClient(this.client);

    request.subscribe(() => {
      this.notification.success(
        `Cliente ${this.isEdit ? 'atualizado' : 'criado'} com sucesso`
      );
      this.router.navigate(['/clients']);
    });
  }

  cancel() {
    this.router.navigate(['/clients']);
  }
}
