import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent} from "./dashboard/dashboard.component";
import {MemberComponent} from "./member/member.component";
import {CompetitionComponent} from "./competition/competition.component";
import {HuntingComponent} from "./hunting/hunting.component";
import {ResultComponent} from "./result/result.component";

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'member', component: MemberComponent },
  { path: 'competition', component: CompetitionComponent },
  { path: 'hunting', component: HuntingComponent },
  { path: 'result', component: ResultComponent },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
