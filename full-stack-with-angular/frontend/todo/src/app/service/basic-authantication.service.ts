import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { API_URL } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthanticationService {

  constructor(private client: HttpClient) { }

  // Basic Authentication
  authenticate(login: LoginBean) {
    console.log("calling basic authentication...");
    let header = this.createBasicAuthenticationHttpHeader(login);
    let token : string = header.get('Authorization') || '';
    return this.client.get<LoginBean>(`${API_URL}/basic-auth`, {headers: header})
    .pipe(
      map(
        data => {
          sessionStorage.setItem('authenticatedUser', login.username);
          sessionStorage.setItem('token', token);
          return data;
        }
      )
    );
  }

  // JWT Token Authentication
  jwtAuthenticate(login: LoginBean){
    return this.client.post<any>(`${API_URL}/authenticate`, login).pipe(
      map(
        data => {sessionStorage.setItem('authenticatedUser', login.username);
        sessionStorage.setItem('token', `Bearer ${data.token}`);
        return data;
      }
      )
    )
  }

  createBasicAuthenticationHttpHeader(login: LoginBean){
    let username = login.username;
    let password = login.password;
    let basicAuthHttpHeaderString = 'Basic ' + window.btoa(username+':'+password);
    let headers = new HttpHeaders({
      Authorization: basicAuthHttpHeaderString
    })
    return headers;
  }

  isUserLoggedIn(){
    let user = sessionStorage.getItem('authenticatedUser');
    return !(user === null);
  }

  getAuthenticatedUser(){
    return sessionStorage.getItem('authenticatedUser');
  }

  getAuthenticatedToken(){
    if(this.getAuthenticatedUser()){
      return sessionStorage.getItem('token');
    }
    return '';
  }

  logout() {
   sessionStorage.removeItem('authenticatedUser');
   sessionStorage.removeItem('token');
  }
}

export class LoginBean {
  constructor(public message: string, public username: string, public password: string){}
}
