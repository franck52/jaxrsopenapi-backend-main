import { Component, OnInit } from '@angular/core';
import { TicketService } from 'src/app/services/ticket.service';
import { TicketModel, Priority } from 'src/app/models/ticketModel';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.scss']
})
export class TicketComponent implements OnInit {
  errorMessage: string = ''; // Initialisation des messages d'erreur et de succès
  successMessage: string = '';
  title: string = ''; // Initialisation des variables du formulaire
  label: string = '';
  priority: Priority = Priority.Basse;
  bugdescription: string = '';

  //priorities = Priority; // Utilisation de l'énumération Priority
  priorities = [Priority.Haute, Priority.Moyenne, Priority.Basse];
  selectedPriority!: Priority;
  uid!: number;

  constructor(private service: TicketService) {}

  ngOnInit(): void {
    const idString = localStorage.getItem('id');
    if (idString !== null) {
      this.uid = parseInt(idString, 10);
    } else {
      this.errorMessage = "Impossible de créer le ticket";
    } 
  }

  getPriorityValues() {
    return Object.values(this.priorities);
  }

  saveTicket(event: Event) {
    event.preventDefault();
    const newTicket: TicketModel = {
      userId: this.uid,
      title: this.title,
      label: this.label,
      bug_Description: this.bugdescription,
      priority: this.selectedPriority,
      id_ticket: 0
    };

    this.service.createTicket(newTicket).subscribe(
      res => {
        this.successMessage = "Données enregistrées";
        this.errorMessage = '';
        this.title = '';
        this.label = "";
        this.bugdescription=""
        
      },
      err => {
        this.errorMessage = err.error.message;
        this.successMessage = "";
      }
    );
  }
}
