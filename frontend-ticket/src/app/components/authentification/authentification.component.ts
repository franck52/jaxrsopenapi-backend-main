import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-authentification',
  templateUrl: './authentification.component.html',
  styleUrls: ['./authentification.component.scss']
})
export class AuthentificationComponent {
  userName: any;
  password: any;
  errorMessage !:string;

  constructor(private router:Router, private service:UserService){}
  onClick(event: Event){
    const userName = this.userName;
    const password = this.password;
    const credentials = {
      userName: userName ,
      password: password,
    };
    event.preventDefault();
    this.service.login(credentials).subscribe(
      response=>{
       this.router.navigate(['home']) 
      },
      error => {
        this.errorMessage = error;
        
      }
    )
  }

}
