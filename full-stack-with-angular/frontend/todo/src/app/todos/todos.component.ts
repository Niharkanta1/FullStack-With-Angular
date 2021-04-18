import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TodoDataService } from '../service/data/todo-data.service';

@Component({
  selector: 'app-todos',
  templateUrl: './todos.component.html',
  styleUrls: ['./todos.component.css']
})
export class TodosComponent implements OnInit {
  message: string = '';
  todos: ToDo[] = [];
  constructor(private todoData: TodoDataService, private router: Router) { }

  ngOnInit(): void {
     this.refreshToDos();
  }

  refreshToDos(){
    this.todoData.getAllTodos('nihar').subscribe(
      response => this.todos = response
    );
  }

  deleteTodo(id: number){
    console.log('delete todo ${id}');
    this.todoData.deleteTodo('nihar', id).subscribe(
      response => {
        console.log(response);
        this.message = `Delete of todo with id ${id} is successful!`;
        this.refreshToDos();
      });
      
  }
  updateTodo(id: number) {
    this.router.navigate([`todo/${id}`]);
  }

  markCompleteTodo(id: number) {
    this.todoData.markAsComplete('nihar',id).subscribe(
      response => {
        console.log(response);
        this.message = `Todo with id ${id} is completed!`;
        this.refreshToDos();
      });
  }

  addTodo(){
    this.router.navigate(['todo/-1']);
  }
  
}

export class ToDo {
  constructor(
    public id: number,
    public desc: string,
    public isCompleted: boolean,
    public targetDate: Date
  ){}
}
