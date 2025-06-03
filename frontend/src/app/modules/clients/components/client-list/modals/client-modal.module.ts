import { NgModule } from '@angular/core';
import { ClientViewModalComponent } from './client-view/client-view-modal.component';
import { SharedModule } from '../../../../../shared/shared.module';
import { PoModalModule } from '@po-ui/ng-components';
import { PhoneListPipe } from '../pipes/phone-list.pipe';
import { AddressListPipe } from '../pipes/address-list.pipe';
import { ClientFormModalComponent } from './client-form/client-form-modal.component';

@NgModule({
  imports: [
    SharedModule,
    PoModalModule,
  ],
  exports: [
    PoModalModule,
    ClientViewModalComponent,
    ClientFormModalComponent,
    PhoneListPipe,
    AddressListPipe
  ],
  declarations: [
    ClientViewModalComponent,
    ClientFormModalComponent,
    PhoneListPipe,
    AddressListPipe
  ]
})
export class ClientsModalModule { }