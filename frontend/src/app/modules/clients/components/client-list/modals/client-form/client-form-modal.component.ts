import { ClientService } from './../../../../services/client.service';
import { Address } from '../../../../models/address.model';
import { Phone } from '../../../../models/phone.model';
import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { PoModalComponent, PoNotificationService } from '@po-ui/ng-components';
import { Client } from '../../../../models/client.model';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-client-form-modal',
  standalone: false,
  templateUrl: './client-form-modal.component.html',
  styleUrl: './client-form-modal.component.scss'
})
export class ClientFormModalComponent implements OnInit{
  @ViewChild('clientModal', { static: true }) modal!: PoModalComponent;
  @Output() clientSaved = new EventEmitter<Client>();
  formErrors: any = {};
  serverErrors: any = {};
  private currentResolve!: (value: Client | undefined) => void; 
  clientForm!: FormGroup

  constructor(
    private fb: FormBuilder,
    private clientService: ClientService,
    private poNotification: PoNotificationService 
  ) {}

  ngOnInit() {
    this.initForm();
  }

  get Phones(): FormArray {
    return this.clientForm.get('phones') as FormArray;
  }

  get Addresses(): FormArray {
    return this.clientForm.get('addresses') as FormArray;
  }

  initForm(): void {
    this.clientForm = this.fb.group({
      id: [''],
      name: ['', Validators.required],
      cpf: ['', Validators.required],
      phones: this.fb.array([this.createPhoneFormGroup()]), 
      addresses: this.fb.array([this.createAddressFormGroup()])
    });
  }

  async openModal(clientData: Client): Promise<Client | undefined> {
    this.clientForm.patchValue({
      id: clientData.id || "",
      name: clientData.name || "",
      cpf: clientData.cpf || "",
    })
    this.Phones.clear();
    this.Addresses.clear();

    if(clientData.phones && clientData.phones.length > 0){
      clientData.phones.forEach((phone: Phone) => this.addPhone(phone));
    } else{
      this.addPhone();
    }
    
    if(clientData.addresses && clientData.addresses.length > 0){
      clientData.addresses.forEach((address: Address) => this.addAddress(address));
    } else{
      this.addAddress();
    }

    this.modal.open();

    return new Promise<Client | undefined>(resolve => {
      this.currentResolve = resolve;
    });
  }

  closeModal(): void {
    this.modal.close();
    this.clientForm.reset();
    this.Phones.clear();
    this.Addresses.clear();
  }

  createPhoneFormGroup(phone?: Phone): FormGroup {
    return this.fb.group({
      number: [phone ? phone.number : '', Validators.required],
    });
  }

  addPhone(phone?: Phone): void {
    this.Phones.push(this.createPhoneFormGroup(phone));
  }

  removePhone(index: number): void {
    this.Phones.removeAt(index);
  }

  createAddressFormGroup(address?: Address): FormGroup {
    return this.fb.group({
      city: [address ? address.city : '', Validators.required],
      state: [address ? address.state : '', Validators.required],
      publicPlace: [address ? address.publicPlace : ''],
      zipCode: [address ? address.zipCode : '', Validators.required],
      street: [address ? address.street : '', Validators.required],
      complement: [address ? address.complement : '', Validators.required],
      number: [ address ? address.number: '']
    });
  }

  addAddress(address?: Address): void {
    this.Addresses.push(this.createAddressFormGroup(address));
  }

  removeAddress(index: number): void {
    this.Addresses.removeAt(index);
  }

  saveClient(): void { 
    if (this.clientForm.valid) {
      this.clearErrors();
      const submittedClient: Client = this.clientForm.value;
      const isUpdate = submittedClient.id && submittedClient.id.trim() !== "";

      const operation = isUpdate
      ? this.clientService.updateClient(submittedClient.id, submittedClient) 
      : this.clientService.createClient(submittedClient)

      operation.subscribe({
        next: (response) => {
          const message = isUpdate? 'Client updated successfully' : 'Client created successfully'
          this.poNotification.success(message);
          this.closeModal();
          this.clientForm.reset();
          this.closeModal();
          if (this.currentResolve) {
            this.currentResolve(response)
          }
        },
        error: (error) => {
          this.handleFormErrors(error);
        }
      })
    }
  }

  handleFormErrors(error: any) {
    this.clearErrors();
    
    if (error.status === 400 && error.error) {
      if (error.error.errors) {
        this.formErrors = error.error.errors;
        this.showFieldErrors();
      
      } else if (error.error.message) {
          this.serverErrors.push(error.error.message);
          this.showServerErrors();
      } else if (Array.isArray(error.error)) {
        this.serverErrors = error.error;
        this.showServerErrors();
      }
    } else {
      this.serverErrors.push('Unexpecetd error, try again.');
      this.showServerErrors();
    }
  }

  private showFieldErrors() {
    Object.keys(this.formErrors).forEach(fieldName => {
      const control = this.clientForm.get(fieldName);
      if (control) {
        control.setErrors({ serverError: true });
        control.markAsTouched();
      }
    });
  }  

  private showServerErrors() {
    this.serverErrors.forEach((error: any) => {
      this.poNotification.error(error);
    });
  }    

  private clearErrors() {
    this.formErrors = {};
    this.serverErrors = [];
      
    Object.keys(this.clientForm.controls).forEach(key => {
      const control = this.clientForm.get(key);
      if (control && control.errors && control.errors['serverError']) {
        delete control.errors['serverError'];
        if (Object.keys(control.errors).length === 0) {
          control.setErrors(null);
        }
      }
    });
  }

  hasFieldError(fieldName: string): boolean {
    return this.formErrors[fieldName] && this.formErrors[fieldName].length > 0;
  }    

  getFieldErrorMessage(fieldName: string): string {
    if (this.hasFieldError(fieldName)) {
      return this.formErrors[fieldName][0];
    }
    return '';
  }
}
