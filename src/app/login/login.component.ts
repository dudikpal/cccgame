import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {AuthGuardService} from "./auth-guard.service";
import {AppComponent} from "../app.component";
import {Router} from "@angular/router";


@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit, OnDestroy {

    public username = '';
    public password = '';
    public loginValid = true;

    isAuthenticated: boolean = false;

    constructor(
        private authService: AuthGuardService,
        private appComponent: AppComponent,
        private router: Router
    ) {
    }

    public ngOnInit(): void {

    }

    public ngOnDestroy(): void {
    }

    public async onSubmit() {

        this.isAuthenticated = await this.authService.sendLoginDataToBack(this.username, this.password);
        this.appComponent.isAuthenticated = this.isAuthenticated;
    }

    toRegistration() {
        this.router.navigate(['/register']);
    }
}
