import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthentificationComponent } from './components/authentification/authentification.component';
import { CommentaireComponent } from './components/commentaire/commentaire.component';
import { ContactComponent } from './components/contact/contact.component';
import { CreateNewPasswordComponent } from './components/create-new-password/create-new-password.component';
import { CreateUserComponent } from './components/create-user/create-user.component';
import { MainComponent } from './components/main/main.component';
import { TagComponent } from './components/tag/tag.component';
import { TicketComponent } from './components/ticket/ticket.component';
import { AuthGuard } from './services/auth-guard.service';
import { MailRecuperationComponent } from './components/mail-recuperation/mail-recuperation.component';
import { UserIformationComponent } from './components/main/user-iformation/user-iformation.component';
import { ListeComponent } from './components/ticket/liste/liste.component';
import { UserlistComponent } from './components/users/userlist/userlist.component';
import { AllTicketComponent } from './components/ticket/all-ticket/all-ticket.component';



const routes: Routes = [
  {path:'',redirectTo:"login",pathMatch:'full'},
  {path:"home", component: MainComponent , canActivate: [AuthGuard]},
  {path: "login", component: AuthentificationComponent},
  {path:'create/user',component:CreateUserComponent},
  {path:'create/ticket',component:TicketComponent, canActivate: [AuthGuard]},
  {path:'create/new-commentaire',component:CommentaireComponent, canActivate: [AuthGuard]},
  {path:'create/new-tag',component:TagComponent, canActivate: [AuthGuard]},
  {path:'about',component:ContactComponent, canActivate: [AuthGuard]},
  {path:'create/new-password',component:CreateNewPasswordComponent},
  {path:'user/email-verication',component:MailRecuperationComponent},
  {path:'home/user/profil',component:UserIformationComponent,canActivate: [AuthGuard]},
  {path:'home/list-tickets',component:ListeComponent,canActivate: [AuthGuard]},
  {path:'home/list-all-tickets',component:AllTicketComponent,canActivate: [AuthGuard]},
  {path:'home/list-users',component:UserlistComponent,canActivate: [AuthGuard]},
  {path:'admin',loadChildren:()=> import('./admin/admin.module').then(m=>m.AdminModule), canActivate:[AuthGuard] },
  
  
  
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
