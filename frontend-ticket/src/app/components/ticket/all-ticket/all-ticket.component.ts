import { Component, ElementRef, ViewChild } from '@angular/core';
import { commentaireModel } from 'src/app/models/commentaireModel';
import { TicketModel } from 'src/app/models/ticketModel';
import { CommentaireService } from 'src/app/services/commentaire.service';
import { TicketService } from 'src/app/services/ticket.service';

@Component({
  selector: 'app-all-ticket',
  templateUrl: './all-ticket.component.html',
  styleUrls: ['./all-ticket.component.scss']
})
export class AllTicketComponent {

  commentaire:any
  tickets!: TicketModel[];
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



  constructor(private ticketService: TicketService,private serviceComm:CommentaireService) {
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
    this.ticketService.getAllTickets().subscribe(tickets => {
      this.tickets = tickets;
      console.log(tickets)
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
    this.serviceComm.createNewComment(addComment).subscribe(
      response=>{
        console.log("ok pour le commentaire!");
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
  onDeleteButtonClick(ticket: any) {
    const index = this.tickets.indexOf(ticket);
    if (index !== -1) {
      this.tickets.splice(index, 1);
    }
    this.ticketService.deleteTicket(ticket.id_ticket).subscribe(
      res=>{console.log(res)},
      errors=>{
        console.log(errors)
      }

    );
  }
  

}
