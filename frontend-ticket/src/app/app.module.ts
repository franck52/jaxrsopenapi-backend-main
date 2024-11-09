import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule} from '@angular/forms';
import { RouterModule } from '@angular/router';



import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';


import { NgModule } from '@angular/core';
import { HttpClientModule } from'@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { MainComponent } from './components/main/main.component';
import { ContactComponent } from './components/contact/contact.component';
import { AuthentificationComponent } from './components/authentification/authentification.component';
import { MailRecuperationComponent } from './components/mail-recuperation/mail-recuperation.component';
import { CreateNewPasswordComponent } from './components/create-new-password/create-new-password.component';
import { CreateUserComponent } from './components/create-user/create-user.component';
import { TicketComponent } from './components/ticket/ticket.component';
import { TagComponent } from './components/tag/tag.component';
import { CommentaireComponent } from './components/commentaire/commentaire.component';
import { BugComponent } from './components/bug/bug.component';
import { HeaderComponent } from './components/header/header.component';
import { ToogleBarMenuComponent } from './components/toogle-bar-menu/toogle-bar-menu.component';
import { UserService } from './services/user.service';
import { TokenService } from './services/token.service';
import { SuccessMessageDirective } from './mesDirective/success-message.directive';
import { MenuComponent } from './components/menu/menu.component';
import { UserIformationComponent } from './components/main/user-iformation/user-iformation.component';
import { ListeComponent } from './components/ticket/liste/liste.component';
import { UserlistComponent } from './components/users/userlist/userlist.component';
import { AllTicketComponent } from './components/ticket/all-ticket/all-ticket.component';




@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    MainComponent,
    ContactComponent,
    AuthentificationComponent,
    MailRecuperationComponent,
    CreateNewPasswordComponent,
    CreateUserComponent,
    TicketComponent,
    TagComponent,
    CommentaireComponent,
    BugComponent,
    HeaderComponent,
    ToogleBarMenuComponent,
    SuccessMessageDirective,
    MenuComponent,
    UserIformationComponent,
    ListeComponent,
    UserlistComponent,
    AllTicketComponent,
    
    
    
    
    
  ],
  imports: [
    
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule,
    
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    AppRoutingModule,
    NgbModule,
    MatFormFieldModule,
    MatInputModule
  ],
  providers: [
    UserService,
    TokenService 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
