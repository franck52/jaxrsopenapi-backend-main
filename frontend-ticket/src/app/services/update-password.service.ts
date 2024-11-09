import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, tap, throwError } from 'rxjs';
import { userModel } from '../models/userModel';
import { API_URL } from '../configUrl';

@Injectable({
  providedIn: 'root'
})
export class UpdatePasswordService {

  private apiUrl =API_URL //`${API_URL}/`;
  constructor(private http:HttpClient) { }
  update(credentials: {password: string }): Observable<{ user: userModel }> {
    const id = localStorage.getItem('id')
    const url = `${this.apiUrl}/users/update-password/${id}`;
    return this.http.put<{
      userName: any;
      token: any;
      id: any; user: userModel 
    }>(url, credentials).pipe(
        tap(response => console.log('Response from login API:', response)),
       
        catchError((error: HttpErrorResponse) => {
          let errorMessage = '';
          if (error.error instanceof ErrorEvent) {
            // Une erreur côté client s'est produite. Gérer l'erreur localement.
            errorMessage = `Une erreur s'est produite : ${error.error.message}`;
          } else if(error.status==405) {
            // Le backend a renvoyé une erreur de réponse.
            errorMessage = `${error.error}`;
          } else{
            errorMessage=" erreur de modification."
          }
          console.error(errorMessage);
          return throwError(errorMessage);
        })
    );
}
}
