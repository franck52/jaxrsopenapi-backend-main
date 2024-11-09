import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import{TicketModel} from '../models/ticketModel'
import{API_URL} from'../configUrl';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private apiUrl =API_URL ;
  tickets: TicketModel[] = []
  constructor(private http:HttpClient) { }
   // Créer un nouveau ticket
   createTicket(ticket: TicketModel): Observable<TicketModel> {
    return this.http.post<TicketModel>(`${this.apiUrl}/tickets/create`, ticket);
  }

  // Récupérer tous les tickets
  getAllTickets(): Observable<TicketModel[]> {
    return this.http.get<TicketModel[]>(`${this.apiUrl}/tickets`);
  }

  // Récupérer un ticket par son ID
  getTicketById(id: number): Observable<TicketModel> {
    const url = `${this.apiUrl}/tickets/${id}`;
    return this.http.get<TicketModel>(url);
  }

  // Mettre à jour un ticket existant
  updateTicket(id: number, ticket: TicketModel): Observable<TicketModel> {
    const url = `${this.apiUrl}/tickets/update/${id}`;
    return this.http.patch<TicketModel>(url, ticket);
  }
  
  updateTicketById(ticket: TicketModel): Observable<TicketModel> {
    const url = `${this.apiUrl}/${ticket.id_ticket}`;
    return this.http.put<TicketModel>(url, ticket);
  }

  // Supprimer un ticket existant
  deleteTicket(id: number): Observable<any> {
    const url = `${this.apiUrl}/tickets/deleted/${id}`;
    return this.http.get(url);
  }

   // returns a list of tickets filtered by a user ID
   findAllTicketsByUser(userId: number): Observable<TicketModel[]> {
    const url = `${this.apiUrl}/tickets/user/${userId}`;
    return this.http.get<TicketModel[]>(url);
  }
}
