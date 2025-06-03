import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import {
  PoPageModule,
  PoTableModule,
  PoButtonModule,
  PoFieldModule,
  PoLoadingModule,
  PoNotificationModule,
  PoModalModule,
  PoDialogModule,
  PoInfoModule,
  PoContainerModule,
  PoWidgetModule,
  PoTagModule,
  PoToolbarModule,
  PoMenuModule,
  PoIconModule,
  PoBreadcrumbModule,
  PoDividerModule
} from '@po-ui/ng-components';

const PO_MODULES = [
  PoPageModule,
  PoTableModule,
  PoButtonModule,
  PoFieldModule,
  PoLoadingModule,
  PoNotificationModule,
  PoModalModule,
  PoDialogModule,
  PoInfoModule,
  PoContainerModule,
  PoWidgetModule,
  PoTagModule,
  PoToolbarModule,
  PoMenuModule,
  PoWidgetModule,
  PoInfoModule,
  PoButtonModule,
  PoTagModule,
  PoLoadingModule,
  PoDialogModule,
  PoIconModule,
  PoBreadcrumbModule,
  PoDividerModule
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ...PO_MODULES
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ...PO_MODULES
  ]
})
export class SharedModule { }