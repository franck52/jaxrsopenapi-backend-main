import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, tap, throwError } from 'rxjs';
import { commentaireModel } from '../models/commentaireModel';
import{API_URL} from'../configUrl';

@Injectable({
  providedIn: 'root'
})
export class CommentaireService {
  private apiUrl =API_URL ;
  constructor(private http:HttpClient) { }
  getAllCommentaire():Observable<commentaireModel[]>{
    return this.http.get<commentaireModel[]>(this.apiUrl);
  }
  getCommentaireById(id:number):Observable<commentaireModel>{
    return this.http.get<commentaireModel>(`${this.apiUrl}/${id}`)
  }
  //créer un nouveau commentaire
  createNewComment(newComment :commentaireModel):Observable<commentaireModel>{
    return this.http.post<commentaireModel>(`${this.apiUrl}/commentaires/createCommentaire/`,newComment)
  }

  // Mettre à jour un commentaire existant
  updateCommentaire(commentaire: commentaireModel): Observable<void> {
    const url = `${this.apiUrl}/${commentaire.idCommentaire}`;
    return this.http.put<void>(url, commentaire);
  }

  addComment(credentials: { userId: number, ticket_id_ticket: number,texteCommentaire: string }): Observable<{commentaire: commentaireModel }> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
  
    return this.http.post<{
         
        commentaire: commentaireModel
  
    }>(`${this.apiUrl}/commentaires/createCommentaire`, credentials, httpOptions).pipe(
      tap(response => console.log('Response from login API:', response)),
      catchError(error => {
        //console.log('Error from login API:', error);
        return throwError(error.error);
    })
     
    );
  }
  public async countCommentsByTicket(id: number): Promise<number> {
    const url = `${this.apiUrl}/tickets/comment/count-by-ticket/${id}`;
    const count = await this.http.get<number>(url).toPromise();
    if (count === undefined) {
      throw new Error("Le nombre de commentaires est indéfini.");
    }
    return count;
    console.log(count)
  }
  

}

