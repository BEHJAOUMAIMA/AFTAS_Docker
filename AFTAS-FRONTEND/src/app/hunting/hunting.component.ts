import { Component, OnInit } from '@angular/core';
import { Hunting } from './interface/hunting';
import { HuntingService } from './service/hunting.service';
import { Competition } from '../competition/interface/competition';
import { Member } from '../member/interface/member';
import { Fish } from './interface/fish';
import { MemberService } from '../member/service/member.service';

@Component({
  selector: 'app-hunting',
  templateUrl: './hunting.component.html',
  styleUrls: ['./hunting.component.css']
})
export class HuntingComponent implements OnInit {
  competitions: Competition[] = [];
  members: Member[] = [];
  fishes: Fish[] = [];
  filteredMembers: Member[] = [];

  selectedCompetitionId: number | undefined;
  selectedMemberId: number | undefined;
  selectedFishName: string | undefined;
  weight: number | undefined;

  newHunting: Hunting = {
    competition: null,
    member: null,
    fish: null,
    weight: null,
  };

  loading: boolean = false;

  constructor(
    private huntingService: HuntingService,
    private memberService: MemberService
  ) {}

  ngOnInit(): void {
    this.loading = true;

    this.huntingService.getCompetitions().subscribe(
      competitions => this.competitions = competitions,
      error => console.error('Error fetching competitions', error),
      () => this.loading = false
    );

    this.onCompetitionSelected();
    this.huntingService.getFishes().subscribe(
      fishes => this.fishes = fishes,
      error => console.error('Error fetching fishes', error)
    );
  }


  onCompetitionSelected(): void {
    if (this.selectedCompetitionId) {
      this.loading = true;
      this.memberService.getMembersByCompetition(this.selectedCompetitionId).subscribe(
        members => {
          this.members = members;
          this.filteredMembers = members;
        },
        error => console.error('Error fetching members', error),
        () => this.loading = false
      );
    }
  }

  addHunting() {
    if (
      this.selectedCompetitionId !== undefined &&
      this.selectedMemberId !== undefined &&
      this.selectedFishName &&
      this.weight !== undefined
    ) {
      const newHunting: Hunting = {
        competition: this.selectedCompetitionId,
        member: this.selectedMemberId,
        fish: this.selectedFishName,
        weight: this.weight,
      };

      this.huntingService.addHunting(newHunting).subscribe(
        (response) => {
          console.log('Hunting added successfully', response);
        },
        (error) => {
          console.error('Error adding hunting', error);
        }
      );
    }
  }

}
