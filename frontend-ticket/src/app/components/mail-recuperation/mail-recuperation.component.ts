import { Component, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { EmailVericationService } from 'src/app/services/email-verication.service';

@Component({
  selector: 'app-mail-recuperation',
  templateUrl: './mail-recuperation.component.html',
  styleUrls: ['./mail-recuperation.component.scss']
})
export class MailRecuperationComponent {
  name = 'Angular';

  public isCollapsed = true;
  email!:string;
  errorMessage?: string;
  constructor(private router:Router, private service:EmailVericationService){}
  sendEmail(event: Event){
    event.preventDefault()
    const credentials = {
      email: this.email
    };
    event.preventDefault();
    this.service.emailVerification(credentials).subscribe(
      response=>{
        alert("vous allez être redirigé vers une autre page");
       this.router.navigate(['create/new-password']) 
      },
      error => {
        this.errorMessage = error;
        
      }
    )
  }
}




