export interface Member {
  id: number,
  name: string,
  familyName: string,
  accessionDate: Date,
  nationality: string,
  identityDocumentType: IdentityDocumentType;
  identityNumber: string,
}
export interface MemberRequest {
  name: string;
  familyName: string;
  accessionDate: Date;
  nationality: string;
  identityDocumentType: IdentityDocumentType;
  identityNumber: string;
}

export enum IdentityDocumentType {
  CIN = 'CIN',
  CARTE_RESIDENCE = 'CARTE_RESIDENCE',
  PASSPORT = 'PASSPORT'
}
export interface AddMemberRequest {
  competitionId: number;
  memberId: number;
}
