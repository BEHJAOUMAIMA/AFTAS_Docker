import {Component, OnInit} from '@angular/core';
import {RankingResponse} from "./interface/ranking-response";
import {Competition} from "../competition/interface/competition";
import {RankingService} from "./service/ranking.service";

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit{

  competitions: any[] = [];
  selectedCompetitionId!: number;
  podiumList: RankingResponse[] = [];

  constructor(private rankingService: RankingService) {}

  ngOnInit() {
    this.loadCompetitions();
  }

  loadCompetitions() {
    this.rankingService.getCompetitions().subscribe((data) => {
      this.competitions = data;
    });
  }

  updatePodium() {
    if (this.selectedCompetitionId) {
      this.rankingService.getPodium(this.selectedCompetitionId).subscribe((data) => {
        this.podiumList = data;
      });
    }
  }
}
