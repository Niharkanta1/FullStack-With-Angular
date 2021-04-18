import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { API_URL } from 'src/app/app.constants';

export class HelloWorldBean {
  constructor(public message: string){}
}


@Injectable({
  providedIn: 'root'
})
export class HelloWorldDataService {
  
  constructor(private client: HttpClient) { }
  executeHelloWorldDataService() {
    console.log("calling hello-world service");
    return this.client.get<HelloWorldBean>(`${API_URL}/hello-world`);
  }

  executeHelloWorldDataServiceWithUser(name: string) {
    console.log("calling hello-world service with name {}", name);
    return this.client.get<HelloWorldBean>(`${API_URL}/hello-world/${name}`, {headers: this.createBasicAuthenticationHttpHeader()});
  }

  createBasicAuthenticationHttpHeader(){
    let username = 'nihar';
    let password = 'password';
    let basicAuthHttpHeaderString = 'Basic ' + window.btoa(username+':'+password);
    let headers = new HttpHeaders({
      Authorization: basicAuthHttpHeaderString
    })
    return headers;
  }
}
