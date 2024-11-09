import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-toogle-bar-menu',
  templateUrl: './toogle-bar-menu.component.html',
  styleUrls: ['./toogle-bar-menu.component.scss']
})
export class ToogleBarMenuComponent implements OnInit  {
  name = 'Angular';

  public isCollapsed = true;
  userName:any;
  constructor(private service :UserService, private router:Router){

  }
  ngOnInit(): void {
    this.userName= localStorage.getItem('name')
  }
  logout(){
    this.service.logout()
    this.router.navigate(["login"]);
  }
}
