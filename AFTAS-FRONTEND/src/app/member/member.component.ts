import { Component, OnInit } from '@angular/core';
import {IdentityDocumentType, Member, MemberRequest} from "./interface/member";
import {MemberService} from "./service/member.service";
import {CompetitionService} from "../competition/service/competition.service";
import {Competition} from "../competition/interface/competition";

declare var $: any;
@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css']
})
export class MemberComponent implements OnInit{
  members: Member[] = [];
  competitions: Competition[] = [];
  selectedCompetitionId: number | undefined;
  selectedMemberId: number | undefined;

  newMember: MemberRequest = {
    name: '',
    familyName: '',
    accessionDate: new Date(),
    nationality: '',
    identityDocumentType: IdentityDocumentType.CIN,
    identityNumber: '',
  };
  constructor(
    private memberService: MemberService,
    private competitionService : CompetitionService
  ) {}


  ngOnInit() {
    this.loadMembers();
    this.loadCompetitions();


    $(() => {
      $('.js-check-all').on('click', () => {
        if ($(this).prop('checked')) {
          $('th input[type="checkbox"]').each(() => {
            $(this).prop('checked', true);
          });
        } else {
          $('th input[type="checkbox"]').each(() => {
            $(this).prop('checked', false);
          });
        }
      });
    });
  }
  loadMembers() {
    this.memberService.getMembers().subscribe(
      (data) => {
        this.members = data;
      },
      (error) => {
        console.error('Error fetching members:', error);
      }
    );
  }
  loadCompetitions() {
    this.competitionService.getCompetitions().subscribe(
      competitions => {
        this.competitions = competitions;
        this.filterCompetitionsByEndTime();
      },
      error => {
        console.error('Error fetching competitions:', error);
      }
    );
  }

  addMember() {
    this.memberService.addMember(this.newMember).subscribe(
      (response) => {
        console.log('Member added successfully:', response);
        this.loadMembers();
      },
      (error) => {
        console.error('Error adding member:', error);
      });

    this.newMember = {
      name: '',
      familyName: '',
      accessionDate: new Date(),
      nationality: '',
      identityDocumentType: IdentityDocumentType.CIN,
      identityNumber: '',
    };
  }
  addMemberToCompetition() {
    if (this.selectedCompetitionId && this.selectedMemberId) {
      const newCompetitionMember = {
        competitionId: this.selectedCompetitionId,
        memberId: this.selectedMemberId
      };

      this.memberService.addMemberToCompetition(newCompetitionMember).subscribe(
        response => {
          console.log('Member added to competition successfully', response);
        },
        error => {
          console.error('Error adding member to competition', error);
        }
      );
    }
  }
  filterCompetitionsByEndTime() {
    const currentDate = new Date();
    console.log('Current Date:', currentDate);

    this.competitions = this.competitions.filter(competition => {
      const competitionEndTime = new Date(competition.date);
      console.log('Competition End Time:', competitionEndTime);

      return competitionEndTime > currentDate;
    });

    console.log('Filtered Competitions:', this.competitions);
  }

  protected readonly IdentityDocumentType = IdentityDocumentType;
}
