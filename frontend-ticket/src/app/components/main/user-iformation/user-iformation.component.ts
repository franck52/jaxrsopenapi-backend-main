import { Component, OnInit } from '@angular/core';
import { userModel } from 'src/app/models/userModel';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-iformation',
  templateUrl: './user-iformation.component.html',
  styleUrls: ['./user-iformation.component.scss']
})
export class UserIformationComponent implements OnInit {
  uid!:number
  errorMessage!:string;
  succesMessage!:string;
  user!:userModel;
  editMode = false;
  saved = false; // Ajoutez cette variable pour contrôler l'affichage du formulaire

  constructor(private service:UserService){}
  ngOnInit(): void {
    console.log(this.saved)
    const idString = localStorage.getItem('id');
    if (idString !== null) {
      this.uid = parseInt(idString, 10);
    } else {
      this.errorMessage = "Impossible de créer le ticket";
    }
    this.service.getUserById(this.uid).subscribe(
      userData=>{
        this.user=userData;
        console.log(userData)
      },
      errors=>{
        console.log(errors)
        this.errorMessage="impossible d'afficher les information, ereur serveur!";
        console.log(errors)
        
      }
    );
  }
  toggleEditMode() :void{
    this.editMode = !this.editMode;
    this.succesMessage = '';
}
saveChanges():void {
  const updateUser:userModel={
    userId: this.uid,
    userName: this.user.userName,
    lastname: this.user.lastname,
    password: this.user.password,
    email: this.user.email,
    userAddress: this.user.userAddress
  }
  this.service.updateUser(updateUser).subscribe(
    res=>{
     
      // Afficher le message de confirmation
      this.succesMessage = 'Modifications enregistrées avec succès.';
      // Réinitialiser editMode et saved
      this.editMode = false;
      this.saved = true;
    },
    errors=>{
      console.log(errors)
      this.errorMessage = 'Erreur lors de l\'enregistrement des modifications.';
    }
  )
  //this.saved = true; // Mettez saved à true pour que le formulaire disparaisse
}


}
