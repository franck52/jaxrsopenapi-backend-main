import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserIformationComponent } from '../components/main/user-iformation/user-iformation.component';
import { AdminLayoutComponent } from './admin-layout/admin-layout.component';

const routes: Routes = [

  {path:'',component: AdminLayoutComponent},
  {path:'admin/user/profil',component:UserIformationComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
