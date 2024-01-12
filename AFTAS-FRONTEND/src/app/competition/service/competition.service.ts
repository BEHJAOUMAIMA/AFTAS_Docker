import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Competition} from "../interface/competition";

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {
  private apiUrl = "http://127.0.0.1:8080/api/v1/competitions";
  constructor(  private http : HttpClient ) { }

  public getCompetitions():Observable<Competition[]>{
    return this.http.get<Competition[]>(`${this.apiUrl}`);
  }
  public addCompetition(newCompetition: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/save`, newCompetition);
  }

}
