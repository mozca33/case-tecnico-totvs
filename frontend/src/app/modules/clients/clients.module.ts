import { NgModule } from '@angular/core';
import { ClientsRoutingModule } from './clients-routing.module';
import { ClientListComponent } from './components/client-list/client-list.component';
import { SharedModule } from '../../shared/shared.module';
import { ClientsModalModule } from './components/client-list/modals/client-modal.module';

@NgModule({
  declarations: [ ClientListComponent ],
  imports: [
    ClientsRoutingModule,
    ClientsModalModule,
    SharedModule
  ],
  providers: [
  ]
})
export class ClientsModule { }