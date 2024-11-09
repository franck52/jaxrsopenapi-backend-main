import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent {
  email!:string;
  password!:string;
  username!:string
  userId!:number;
  userAdress!:string;
  lastname!:string;
  successMessage!:string;
  errorMessage!: string;
  constructor(private http:HttpClient, private userService:UserService){}
  saveUser(event: Event){
    event.preventDefault();
    const userName = this.username;
    const password = this.password;
    const email = this.email;
    const userAdress = this.userAdress;
    const lastname =this.lastname
    const credentials = {
      userName: userName ,
      password: password,
      email:email,
      userAddress:userAdress,
      lastname : this.lastname
      
    };
    this.userService.addUser(credentials).subscribe(
      response =>{
        //console.log(response)
        this.successMessage ="votre compte a été créé, vous pouvez maintenant vous connecter ";
       // Hide the success message after 3 seconds
      setTimeout(() => {
        this.successMessage = '';
        this.email=""
        this.password=""
        this.userAdress=""
        this.username=""
        this.lastname=""
      }, 3000);
      },
      error=>{
        console.log(error)
      // Hide the error message after 3 seconds
      this.errorMessage = 'Erreur de création de compte.';
      setTimeout(() => {
        this.errorMessage = '';
      }, 3000);
      }
    )
  }
}
