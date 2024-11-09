import { Component,ElementRef, ViewChild } from '@angular/core';
import { TicketService } from 'src/app/services/ticket.service';
import { TicketModel, Priority } from 'src/app/models/ticketModel';
import { commentaireModel } from 'src/app/models/commentaireModel';
import { CommentaireService } from 'src/app/services/commentaire.service';
import { TagModel } from 'src/app/models/tagModel';
import { TagService } from 'src/app/services/tag.service';

@Component({
  selector: 'app-liste',
  templateUrl: './liste.component.html',
  styleUrls: ['./liste.component.scss']
})
export class ListeComponent {
  commentaire:any
  tickets!: TicketModel[];
  taglabel:any;
  uid!:number;
 // ticketCommentCount: number = 0;
 ticketCommentCount:any = []
  errorMessage!:string;
  selectedTicket:TicketModel = new TicketModel()
  id = this.selectedTicket.id_ticket
  
  //selectTicket: TicketModel = new TicketModel()
  showEditForm: boolean = false;
  @ViewChild('editFormContainer', { static: false }) editFormContainer!: ElementRef;
  currentTicketId: any;
  selectedTicketId: number | null = null;



  constructor(private ticketService: TicketService,private serviceComm:CommentaireService, 
    private tagService:TagService) {
    this.selectedTicket = new TicketModel();
    this.selectedTicket= new TicketModel()
  }

  ngOnInit(): void {
    const idString = localStorage.getItem('id');
    if (idString !== null) {
      this.uid = parseInt(idString, 10);
    } else {
      this.errorMessage = "Impossible de créer le ticket";
    }
    this.ticketService.findAllTicketsByUser(this.uid).subscribe(tickets => {
      this.tickets = tickets;
      const promises = [];

      for (let i = 0; i < tickets.length; i++) {
        this.serviceComm.countCommentsByTicket(tickets[i].id_ticket)
          .then(count => {
            this.ticketCommentCount[tickets[i].id_ticket] = count;
          })
          .catch(error => {
            console.error(error);
          });
      }
    });
    
  
}


  
  onTicketClick(ticket: TicketModel): void {
    this.selectedTicket = ticket;
  }
  
  onEditButtonClick(ticket: any) {
    this.showEditForm = true;
    this.selectedTicket = ticket;
    //console.log(this.selectedTicket.id_ticket)
    
  }
  onSaveButtonClick() {
    // code pour enregistrer les modifications
    const update:TicketModel={
      id_ticket: this.selectedTicket.id_ticket,
      userId: this.uid,
      label: this.selectedTicket.label,
      title: this.selectedTicket.title
    }
    const addComment :commentaireModel={
      userId: this.uid,
      ticket_id_ticket: this.selectedTicket.id_ticket,
      texteCommentaire: this.commentaire,
      idCommentaire: 0
    };
    const newtag:TagModel={
      tagLab: this.taglabel
    }
    this.ticketService.updateTicket(this.selectedTicket.id_ticket,update).subscribe(
      res=>{
        console.log("ok")
      },
      err=>{
        this.errorMessage=err
        console.log(err)
      }
    );
    this.tagService.createNewTag(newtag,this.selectedTicket.id_ticket).subscribe(
      succes=>{
        console.log(succes)
      },
      errors=>{ console.log(errors)}
    );
    this.serviceComm.createNewComment(addComment).subscribe(
      response=>{
        console.log("ok pour le commentaire!")
        location.reload(); // actualiser la fenêtre
      },
      errors=>{
        console.log(errors)
      }
    )
    this.showEditForm = false;
  }

  onCancelButtonClick() {
    this.showEditForm = false;
  }
  onDeleteButtonClick(ticket: TicketModel): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce ticket ?')) {
      const index = this.tickets.indexOf(ticket);
      if (index !== -1) {
        this.tickets.splice(index, 1);
      }
      this.ticketService.deleteTicket(ticket.id_ticket).subscribe(() => {
        this.loadTickets();
      });
    }
  }
  loadTickets(): void {
    this.ticketService.findAllTicketsByUser(this.uid).subscribe(tickets => {
      this.tickets = tickets;
    });
  }
  
  
  confirmDelete(): void {
    if (window.confirm('Êtes-vous sûr de vouloir supprimer ce ticket ?')) {
        this.onDeleteButtonClick(this.selectedTicket);
    }
}

  


}
