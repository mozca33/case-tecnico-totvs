import { provideRouter, Routes } from '@angular/router';
import { ClientListComponent } from './clients/client-list/client-list.component';
import { ClientFormComponent } from './clients/client-form/client-form.component';

const routes: Routes = [
  { path: '', redirectTo: '/clients' },
  { path: 'clients', component: ClientListComponent },
  { path: 'form', component: ClientFormComponent },
  { path: 'form/:id', component: ClientFormComponent },
];

export const appRoutes = [provideRouter(routes)];
