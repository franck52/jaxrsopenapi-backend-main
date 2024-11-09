import { Component,ElementRef,ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  name = 'Angular';
  userName:any;

  public isCollapsed = true;
  hide:boolean=true;
  constructor(private router:Router, private service:UserService){
    this.userName=localStorage.getItem('name');
  }
  onShowToogle(){
    this.hide=!this.hide;
  }
  
  logout(){
    this.service.logout()
    this.router.navigate(["login"]);
  }
}
