export interface Competition {
  id: number;
  code: string;
  date: string;
  startTime:string;
  endTime: string;
  NumberOfParticipants: number;
  Location: string;
  amount: number;
  status?: string;

}
export interface CompetitionRequest {
  date: string;
  startTime:string;
  endTime: string;
  numberOfParticipants: number;
  Location: string;
  amount: number;
}
