import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TodoDataService } from '../service/data/todo-data.service';
import { ToDo } from '../todos/todos.component';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {
  id = {} as number;
  todo = {} as ToDo;

  constructor(private todoService: TodoDataService, 
    private route: ActivatedRoute,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    if(this.id != -1){
      this.getToDo();
    }
  }

  getToDo(){
    this.todoService.getTodo('nihar', this.id).subscribe(
      response => {
        this.todo = response;
      }
    );
  }

  saveTodo(){
    if(this.id == -1){
      this.todoService.createToDo('nihar', this.todo).subscribe(
        response => {
          this.router.navigate(['todos'])
        }
      );
    }
    else{
      this.todoService.updateToDo('nihar', this.todo.id, this.todo).subscribe(
        response => {
          console.log("Updated todo ", response);
          this.router.navigate(['todos'])
        }
      );
    }
  }

}
