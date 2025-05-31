import { HttpClientModule, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule, bootstrapApplication } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import {
  PoToolbarModule,
  PoMenuModule,
  PoPageModule,
  PoHttpRequestModule,
  PoButtonModule,
  PoFieldModule,
  PoInfoModule,
  PoLoadingModule,
  PoNotificationModule,
  PoTableModule,
  PoModalModule,
  PoDialogModule
} from '@po-ui/ng-components';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    PoToolbarModule,
    PoMenuModule,
    PoPageModule,
    PoHttpRequestModule,
    PoButtonModule,
    PoFieldModule,
    PoInfoModule,
    PoLoadingModule,
    PoNotificationModule,
    PoTableModule,
    PoModalModule,
    PoDialogModule,
  ],
  providers: [provideHttpClient(withInterceptorsFromDi())],
  bootstrap: [AppComponent]
})
export class AppModule {
 }
