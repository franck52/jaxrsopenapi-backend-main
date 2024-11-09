import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { commentaireModel } from 'src/app/models/commentaireModel';
import { TicketModel } from 'src/app/models/ticketModel';
import { CommentaireService } from 'src/app/services/commentaire.service';
import { TicketService } from 'src/app/services/ticket.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent {
  id!:number
  ticket!:TicketModel
  label:any;
  title:any;
  uid!:number
  errorMessage:any
  texteCommentaire!: string ;
  constructor(private router:Router,private service:TicketService,
    private serviceCom:CommentaireService){}
    ngOnInit(): void {
      const idString = localStorage.getItem('id');
      if (idString !== null) {
        this.uid = parseInt(idString, 10);
      } else {
        this.errorMessage = "Impossible de créer le ticket";
      } 
    }
  afficher(){
    this.service.getTicketById(this.id).subscribe(
      (data: TicketModel)=>{
        this.ticket = data;
        this.label=data.label;
        this.title=data.title;
        console.log(data)
      }
    )

  }
  editTicket(event:Event){
    event.preventDefault()
    const newCommentaire:commentaireModel={
      userId: this.uid,
      ticket_id_ticket: this.id,
      texteCommentaire: this.texteCommentaire,
      idCommentaire:0,
    }
   
    const ticket:TicketModel={
      id_ticket: this.id,
      userId: this.uid,
      label: this.label,
      title: this.title 
    }
    //modifier le ticket si les condition sont verifier
    if(this.label!=null || this.title!=null){
      this.service.updateTicket(this.id,ticket).subscribe(
        response=>{console.log(response)},
        error=>{
          console.log(error)
        }
      );
    }
    //créer le commentaire si condition vérifiée
    if(this.texteCommentaire!=null){
      console.log(newCommentaire)
      this.serviceCom.createNewComment(newCommentaire).subscribe(
        res=>{
          //console.log('ok')
          alert("Commentaire et modification prise en compte")
        },
        errors=>{
          console.log(errors)
        }
      );
    }
  }

}
