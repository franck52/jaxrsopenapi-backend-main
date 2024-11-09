import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, tap, throwError } from 'rxjs';
import { userModel } from '../models/userModel';
import{API_URL} from'../configUrl';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl =API_URL //`${API_URL}/`;
  authToken: any;

  constructor(private http:HttpClient) { }
  login(credentials: { userName: string, password: string }): Observable<{ user: userModel }> {
    const url = `${this.apiUrl}/api/login/`;
    return this.http.post<{
      userName: any;
      token: any;
      id: any; user: userModel 
    }>(url, credentials).pipe(
        tap(response => console.log('Response from login API:', response)),
        map(response => {
            this.authToken = response.token;
            const userN = response.userName;
            const userid = response.id;
            localStorage.setItem('access_token', (this.authToken));
            localStorage.setItem('name', userN);
            localStorage.setItem('id', userid);
            return this.authToken;
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

addUser(credentials: { userName: string, password: string }): Observable<{ user: userModel }> {
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  return this.http.post<{
      userName: any;
      token: any;
      id: any; 
      user: userModel

  }>(`${this.apiUrl}/users/create`, credentials, httpOptions).pipe(
    tap(response => console.log('Response from login API:', response)),
    catchError(error => {
      //console.log('Error from login API:', error);
      return throwError(error.error);
  })
   
  );
}

updateUser(user: userModel): Observable<userModel> {
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  const url = `${this.apiUrl}/users/update/`;
  return this.http.put<userModel>(url, user, httpOptions);
}

getUserById(userId: number): Observable<userModel> {
  const url = `${this.apiUrl}/users/findById/${userId}`;
  return this.http.get<userModel>(url).pipe(
    tap( response=>{console.log('reponse API',response)}),
    catchError( (error:HttpErrorResponse) =>{
      let errorMessage=""
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
      return throwError(error.error);
      
    })
  );
}
logout(): void {
  // Supprimez les données de connexion de l'utilisateur (par exemple, en supprimant le token d'authentification)
  localStorage.removeItem('access_token');
  localStorage.removeItem('id');
  localStorage.removeItem('name');
}
deleteUserById(userId: number): Observable<any> {
  const url = `${this.apiUrl}/users/delete/${userId}`;
  return this.http.delete(url);
}
// Récupérer tous les utilisateurs
getAllUsers(): Observable<userModel[]> {
  return this.http.get<userModel[]>(`${this.apiUrl}/users`);
}
// Méthode pour vérifier si l'utilisateur est connecté
isLoggedIn(): boolean {
  return this.authToken !== null && this.authToken !== undefined;
}
findUserByEmail(email: string): Observable<userModel> {
  const url = `${this.apiUrl}/users/by-email/${email}`;
  return this.http.get<userModel>(url);
}



}
