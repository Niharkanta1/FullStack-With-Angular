import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HelloWorldBean, HelloWorldDataService } from '../service/data/hello-world-data.service';
//import { AppComponent } from '../app.component';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})

// 'export' suggest that the class can be used outside the boundary of the module
export class WelcomeComponent implements OnInit {

  //member variables
  title = 'To-Do';
  user = '';
  restServiceMessage = '';

  //ActivatedRoute
  constructor(private route: ActivatedRoute, private service: HelloWorldDataService) { }

  ngOnInit(): void {
    this.user = this.route.snapshot.params['name'];
    console.log("Title: " + this.title + " User: "+ this.user);
  }

  callHelloWorldService(){
    console.log(this.service.executeHelloWorldDataService());
    this.service.executeHelloWorldDataService().subscribe(
      response => this.handleSuccessResponse(response)
    );
  }

  callHelloWorldServiceWithName(){
    let user = this.route.snapshot.params['name'];
    console.log(" Calling helloWorld with user " + user);
    this.service.executeHelloWorldDataServiceWithUser(user).subscribe(
      response => this.handleSuccessResponse(response)
    );
  }

  handleSuccessResponse(response: HelloWorldBean){
    console.log(response.message);
    this.restServiceMessage = response.message;
  }

}
