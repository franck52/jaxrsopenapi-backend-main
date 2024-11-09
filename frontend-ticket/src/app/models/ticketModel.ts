export enum Priority {
    Haute = 1,
    Moyenne = 2,
    Basse = 3
  }
export class TicketModel{
    id_ticket!: number;
    userId!:number;
    label!:string;
    title!:string;
    bug_Description?:string;
    priority?:Priority 
}