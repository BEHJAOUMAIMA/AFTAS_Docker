import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Hunting} from "../interface/hunting";
import {Observable} from "rxjs";
import {Competition} from "../../competition/interface/competition";
import {Member} from "../../member/interface/member";
import {Fish} from "../interface/fish";

@Injectable({
  providedIn: 'root'
})
export class HuntingService {
  private apiUrl = 'http://127.0.0.1:8080/api/v1';

  constructor(private http: HttpClient) {}

  addHunting(newHunting: Hunting): Observable<any> {
    return this.http.post(`${this.apiUrl}/hunting/save`, newHunting);
  }

  getCompetitions(): Observable<Competition[]> {
    return this.http.get<Competition[]>(`${this.apiUrl}/competitions`);
  }

  getMembers(): Observable<Member[]> {
    return this.http.get<Member[]>(`${this.apiUrl}/members`);
  }

  getFishes(): Observable<Fish[]> {
    return this.http.get<Fish[]>(`${this.apiUrl}/fishes`);
  }
}
