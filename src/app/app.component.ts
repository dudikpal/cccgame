import {Component, OnChanges, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {environment} from "../environments/environment";


@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit, OnChanges {
    title = 'Car Cards Fighting';

    isAuthenticated: boolean = false;


    constructor(private router: Router) {

    }

    ngOnInit(): void {

    }

    ngOnChanges(): void {
        console.log('app-compban: ' + this.isAuthenticated)
    }


    public logout(): void {
        /*sessionStorage.removeItem('AuthToken');
        this.isAuthenticated = false;
        this.router.navigate(['/login']);*/
        const response = fetch(environment.urlPrefix + "/api/auth/signout", {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            }
        });

        this.router.navigate(['/login']);
    }
}
