import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, tap, throwError } from 'rxjs';
import { userModel } from '../models/userModel';
import { API_URL } from '../configUrl';

@Injectable({
  providedIn: 'root'
})
export class EmailVericationService {
  private apiUrl =API_URL //`${API_URL}/`;
  

  constructor(private http:HttpClient) { }
  emailVerification(credentials: { email: string}): Observable<{ user: userModel }> {
    const url = `${this.apiUrl}/api/user/email-verication`;
    return this.http.post<{
      email: any;
      userName: any;
      token: any;
      id: any; user: userModel 
    }>(url, credentials).pipe(
        tap(response => console.log('Response from login API:', response)),
        map(response => {
            
            const userN = response.userName;
            const userid = response.id;
            const email = response.email;
            localStorage.setItem('email', email);
            localStorage.setItem('name', userN);
            localStorage.setItem('id', userid);
            return email;
        }),
        catchError((error: HttpErrorResponse) => {
          let errorMessage = '';
          if (error.error instanceof ErrorEvent) {
            // Une erreur côté client s'est produite. Gérer l'erreur localement.
            errorMessage = `Une erreur s'est produite : ${error.error.message}`;
          } else if(error.status==401) {
            // Le backend a renvoyé une erreur de réponse.
            //errorMessage = `Erreur ${error.status}: ${error.statusText}\n${error.error}`;
            errorMessage = `${error.error}`;
          } else{
            errorMessage=" Le serveur est indisponible."
          }
          console.error(errorMessage);
          return throwError(errorMessage);
        })
    );
}
}
