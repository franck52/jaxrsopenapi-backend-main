import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { UserIformationComponent } from '../components/main/user-iformation/user-iformation.component';
import { AdminLayoutComponent } from './admin-layout/admin-layout.component';



@NgModule({
  declarations: [
    
    AdminLayoutComponent,
    
  ],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
