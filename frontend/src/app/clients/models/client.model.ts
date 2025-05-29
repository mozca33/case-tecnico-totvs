export interface PhoneDTO {
  number: string;
}

export interface AddressDTO {
  city: string;
  state: string;
  zipCode: string;
  publicPlace: string;
  street: string;
  complement?: string;
}

export interface Client {
  id?: string;
  name: string;
  email: string;
  phone: string;
  address: string;
}
