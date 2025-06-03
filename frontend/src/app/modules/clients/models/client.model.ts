import { Address } from "./address.model";
import { Phone } from "./phone.model";

export interface Client {
  id: string;
  name: string;
  cpf: string;
  phones: Phone[];
  addresses: Address[];
}