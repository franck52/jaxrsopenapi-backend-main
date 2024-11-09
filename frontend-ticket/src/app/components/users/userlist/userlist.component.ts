import { Component, OnInit } from '@angular/core';
import { userModel } from 'src/app/models/userModel';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.scss']
})
export class UserlistComponent implements OnInit  {
  userEmail!:string
  user!:userModel
  users:userModel[]=[]
  constructor(private service:UserService){}
  ngOnInit(): void {
    this.service.getAllUsers().subscribe(
      data=>{
        this.users=data;
        console.log(data)

      },
      errors=>{
        console.log(errors)
      }
    );
  }
  getUserByEmail(){
    this.service.findUserByEmail(this.userEmail).subscribe(
      data=>{
        this.user=data
        console.log(this.user)
      },
      errors=>{
        console.log(errors)
      }

    );
  }

}
