import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'phoneList', standalone:false })
export class PhoneListPipe implements PipeTransform {
  transform(phones: { number: string }[] = []): string {
    return phones.length ? phones.map(p => p.number).join(', ') : 'No phone ';
  }
}