import {FlexLayoutModule} from '@angular/flex-layout';
import {FormBuilder, FormsModule, ReactiveFormsModule} from '@angular/forms';

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatTableModule} from '@angular/material/table';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatSelectModule} from '@angular/material/select';
import {MatOptionModule} from '@angular/material/core';

import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {LoggedInGuardService} from "./login/logged-in-guard.service";
import {GarageComponent} from './home/garage/garage.component';
import {RegisterComponent} from './register/register.component';
import {MatDividerModule} from "@angular/material/divider";
import {MatGridListModule} from "@angular/material/grid-list";


@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        HomeComponent,
        GarageComponent,
        RegisterComponent,
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        FlexLayoutModule,
        FormsModule,
        MatToolbarModule,
        MatInputModule,
        MatCardModule,
        MatMenuModule,
        MatIconModule,
        MatButtonModule,
        MatTableModule,
        MatSlideToggleModule,
        MatSelectModule,
        MatOptionModule,
        MatDividerModule,
        ReactiveFormsModule,
        MatGridListModule,
    ],
    providers: [LoggedInGuardService,
        FormBuilder,],
    bootstrap: [AppComponent]
})
export class AppModule {
}
