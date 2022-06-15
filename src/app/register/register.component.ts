import {Component, OnInit} from '@angular/core';
import {AuthGuardService} from "../login/auth-guard.service";
import {
    AbstractControl,
    FormBuilder,
    FormGroup,
    ValidationErrors,
    ValidatorFn,
    Validators
} from "@angular/forms";
import {MyErrorStateMatcher} from "./myErrorStateMatcher";

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    username = '';
    email = '';
    password = '';
    confirmPassword = '';
    result = '';
    userRegistrationForm!: FormGroup;
    errors = {
        username: 'Name must between 4-20 character, contains letter and number, and start with letter',
        email: 'This is not valid email address',
        password: 'Length must between 6-40 character, lower- or uppercase characters and numbers enabled.',
        confirmPassword: 'The passwords must be equal'
    };
    matcher = new MyErrorStateMatcher();


    constructor(
        private authService: AuthGuardService,
        private formBuilder: FormBuilder
    ) {
    }

    ngOnInit(): void {
        this.createForm();
        //console.log(this.userRegistrationForm.controls)
    }

    createForm() {
        this.userRegistrationForm = this.formBuilder.group({
            username: ['', [
                Validators.required,
                Validators.minLength(4),
                Validators.maxLength(20),
                Validators.pattern(/^[a-zA-Z][a-zA-Z0-9]{3,19}$/)
            ]],
            email: ['', [
                Validators.required,
                Validators.maxLength(50),
                Validators.email
            ]],
            password: ['', [
                Validators.required,
                Validators.minLength(6),
                Validators.maxLength(40),
                Validators.pattern(/^[a-zA-Z0-9]{6,40}$/),
            ]],
            confirmPassword: ['', [
                    Validators.required,
                    this.confirmPasswordValidator()
                ]
            ]
        });
    }


    confirmPasswordValidator(): ValidatorFn {

        return (control: AbstractControl): ValidationErrors | null => {

            if (this.password !== control.value) {
                return {notMatch: {value: true}};
            }

            return null;
        };
    }


    public async onSubmit() {

        if (this.password === this.confirmPassword && this.userRegistrationForm.valid) {

            const response = await this.authService.sendRegisterDataToBack(this.username, this.email, [], this.password);

            if (response !== null) {
                const jsonData = await JSON.parse(response);
                console.log(jsonData);
                this.result = jsonData['message'];
            } else {
                this.result = 'Error response'
            }

        } else {
            this.result = this.errors.confirmPassword;
            this.userRegistrationForm.markAllAsTouched();
        }

    }

}
