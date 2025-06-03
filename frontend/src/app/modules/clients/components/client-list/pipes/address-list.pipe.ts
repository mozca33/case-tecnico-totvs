import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'addressList', standalone:false })
export class AddressListPipe implements PipeTransform {
  transform(addresses: {
      state?: string,
      city?: string,
      publicPlace?: string,
      zipCode?: string,
      street?: string,
      complement?: string,
    }[] = []): string {
    return addresses.length ? addresses.map(p => {
      return [p.state,
        p.city,
        p.publicPlace,
        p.zipCode,
        p.street,
        p.complement]
      .filter(Boolean)
      .join(', ');
    }).join(' | '): 'No address';
  }
}