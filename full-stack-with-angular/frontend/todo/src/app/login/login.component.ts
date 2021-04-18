import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BasicAuthanticationService, LoginBean } from '../service/basic-authantication.service';
import { HardcodedAuthenticationService } from '../service/hardcoded-authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = '';
  password = '';
  invalidLogin = false;
  errorMessage = "Invalid Login!!"
  login = {} as LoginBean;

  //dependancy injection 
  constructor(private router: Router, 
    private hardcodedAuthenticationService: HardcodedAuthenticationService,
    private basicAuthService: BasicAuthanticationService ) { }

  ngOnInit(): void {
  }

  handleLogin() {
    console.log(this.username);
    //if(this.username == 'nihar' && this.password == '1234'){
    if(this.hardcodedAuthenticationService.authenticate(this.username, this.password)){
      this.router.navigate(['welcome', this.username])
      this.invalidLogin = false;
    }
    else{
      this.invalidLogin = true;
    }
  }

  handleBasicAuthLogin() {
    this.login = new LoginBean('', this.username, this.password);
    console.log(this.login.username);
    this.basicAuthService.authenticate(this.login).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['welcome', this.login.username]);
        this.invalidLogin = false;
      },
      error => {
        console.log(error);
        this.invalidLogin = true;
      }
    );
  }

  handleJwtAuthLogin(){
    this.login = new LoginBean('', this.username, this.password);
    this.basicAuthService.jwtAuthenticate(this.login).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['welcome', this.login.username]);
        this.invalidLogin = false;
      },
      error => {
        console.log(error);
        this.invalidLogin = true;
      }
    );
  }

}
