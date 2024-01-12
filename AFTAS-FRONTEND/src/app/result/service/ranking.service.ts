import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RankingResponse} from "../interface/ranking-response";
import {Competition} from "../../competition/interface/competition";

@Injectable({
  providedIn: 'root'
})
export class RankingService {
  private baseUrl = 'http://127.0.0.1:8080/api/v1/ranking';

  constructor(private http: HttpClient) {}

  getPodium(competitionId: number): Observable<RankingResponse[]> {
    const url = `${this.baseUrl}/competition/${competitionId}/podium`;
    return this.http.get<RankingResponse[]>(url);
  }
  getCompetitions(): Observable<Competition[]> {
    const url = 'http://127.0.0.1:8080/api/v1/competitions';
    return this.http.get<Competition[]>(url);
  }
}
