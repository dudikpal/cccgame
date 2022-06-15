import { Component, OnInit } from '@angular/core';
import {AuthGuardService} from "../../login/auth-guard.service";
import {DomSanitizer, SafeHtml} from "@angular/platform-browser";
import {environment} from "../../../environments/environment";


@Component({
  selector: 'app-garage',
  templateUrl: './garage.component.html',
  styleUrls: ['./garage.component.css']
})
export class GarageComponent implements OnInit {

  userRole!: string;
  adminRole!: string;
  publicRole!: string;
  adminButton!: SafeHtml;
  playerCards = 'Default content';
  urlPrefix = environment.urlPrefix;

  constructor(private authService: AuthGuardService,
              private sanitizer: DomSanitizer) { }

  ngOnInit(): void {

    this.tryRequest();
    this.getPlayerCards();

  }

  async getPlayerCards() {
    const response = await fetch(this.urlPrefix + '/api/cards');
    console.log(response);
    const responseData = await response.json();
    console.log(responseData);
    this.playerCards = JSON.stringify(responseData);
    console.log(this.playerCards);
  }

  async tryRequest() {

    const payLoad = `ccgamer=${sessionStorage.getItem('AuthToken')!.replace(/\"/g, '')}`;

    const responsePublic = await fetch(this.urlPrefix + '/api/test/all');
    const publicText = await responsePublic.text();
    this.publicRole = publicText;

    const responseUserRole = await fetch(this.urlPrefix + '/api/test/user', {
      headers: {
        'Authorization': payLoad,
      }
    });
    const userText = await responseUserRole.text();
    this.userRole = userText;

    const responseAdminRole = await fetch(this.urlPrefix + '/api/test/admin', {
      headers: {
        'Authorization': payLoad
      }
    });
    const adminText = await responseAdminRole.text();
    this.adminRole = adminText;

    const responseAdminPage = await fetch(this.urlPrefix + '/api/cards/admin', {
      headers: {
        'Authorization': payLoad
      }
    });
    const adminPage = await responseAdminPage.text();

    this.adminButton = this.sanitizer
        .bypassSecurityTrustHtml(adminPage);
  }
}
