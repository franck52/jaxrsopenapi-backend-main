import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UpdatePasswordService } from 'src/app/services/update-password.service';

@Component({
  selector: 'app-create-new-password',
  templateUrl: './create-new-password.component.html',
  styleUrls: ['./create-new-password.component.scss']
})
export class CreateNewPasswordComponent {
  password:any;
  cpassword:any
  errorMessage:any;
  constructor(private router:Router, private service:UpdatePasswordService){}
  userUpdatePassword(event: Event){
    event.preventDefault();
    const credentials ={
      password:this.cpassword

    }
    if(this.cpassword==this.password){
      this.service.update(credentials).subscribe(
        response=>{
          alert("Felicitation votre mot de passe a été modifié")
          this.router.navigate(['login']);

        },
        error=>{
          this.errorMessage=error;
        }
      )
    }else{
      this.errorMessage="veillez saisir le même mot de passe";
    }
  }

}
