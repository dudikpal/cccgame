import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {LoggedInGuardService} from "./login/logged-in-guard.service";
import {GarageComponent} from "./home/garage/garage.component";
import {RegisterComponent} from "./register/register.component";

const routes: Routes = [
  {path: '/', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'home', component: HomeComponent, canActivate: [LoggedInGuardService]},
  {path: 'garage', component: GarageComponent, canActivate: [LoggedInGuardService]},
  //{path: '**', component: LoginComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
