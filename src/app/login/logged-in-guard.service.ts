import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable()
export class LoggedInGuardService implements CanActivate {

    public token!: string | null;
    public isAuthenticated = false;
    urlPrefix = environment.urlPrefix;

    constructor(private _router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {

        if (!this.isAuthenticated) {
            this._router.navigate(['/login']);
            return false;
        }

        return true;
    }


    async sendLoginDataToBack(username: string, password: string) {

        const reqBody = JSON.stringify({"username": username, "password": password});
        const response = await fetch( this.urlPrefix + "/api/auth/signin", {
            method: 'POST',
            body: reqBody,
            headers: {
                "Content-Type": "application/json"
            }
        });

        if (response.status < 400) {

            const jsonData = await response.json();
            this.token = jsonData['jwt'].match(/(?<=ccgamer=).*?(?=;)/g).toString();

            sessionStorage.setItem('AuthToken', JSON.stringify(this.token))
            this.isAuthenticated = true;
            this._router.navigate(['/home']);
            return true;

        } else {
            return false;
        }

    }


    async sendRegisterDataToBack(username: string, email: string, roles: [], password: string) {

        const reqBody = JSON.stringify({"username": username, "email": email, "password": password});
        const response = await fetch(this.urlPrefix + "/api/auth/signup", {
            method: 'POST',
            body: reqBody,
            headers: {
                "Content-Type": "application/json"
            }
        });

        return response.text();
    }


    parseJwt(token: string) {
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));

        return JSON.parse(jsonPayload);
    };
}
