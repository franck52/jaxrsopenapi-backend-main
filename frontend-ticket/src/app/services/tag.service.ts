import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { API_URL } from '../configUrl';
import { TagModel } from '../models/tagModel';

@Injectable({
  providedIn: 'root'
})
export class TagService {
  private apiUrl= API_URL;
  constructor(private http:HttpClient) { }
  getAllTag():Observable<TagModel[]>{
    return this.http.get<TagModel[]>(`${this.apiUrl}/tags`);
  }
  getTagById(idTag:number):Observable<TagModel>{
    return this.http.get<TagModel>(`${this.apiUrl}/tags/${idTag}`);
  }
  createNewTag(tag:TagModel,idTicket:number):Observable<TagModel>{
    return this.http.post<TagModel>(`${this.apiUrl}/tags/create/${idTicket}`,tag)
  }
  delete(id:number):Observable<HttpResponse<any>>{
    return this.http.delete<any>(`${this.apiUrl}/tags/delete/${id}`,{observe:'response'})
    .pipe(
      tap(response => {return response}),
      catchError(error => {
        //console.log('Error from login API:', error);
        return throwError(error.error);
    })

    );
  }
  update(update:TagModel):Observable<TagModel>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.patch<TagModel>(`${this.apiUrl}/update/${update.idTag}`, update, httpOptions)
    .pipe( 
      tap(response=>{return response}),
      catchError(error => {
        //console.log('Error from login API:', error);
        return throwError(error.error);
    })
    );
  }
}
