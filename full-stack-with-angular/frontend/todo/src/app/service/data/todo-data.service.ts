import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL } from 'src/app/app.constants';
import { ToDo } from 'src/app/todos/todos.component';

@Injectable({
  providedIn: 'root'
})
export class TodoDataService {
  markAsComplete(user:string, id: number) {
    return this.client.get((`${API_URL}/user/${user}/todos/${id}/complete`));
  }

  constructor(private client: HttpClient) { }

  getAllTodos(user: string) {
    console.log("calling todo rest service");
    return this.client.get<ToDo[]>(`${API_URL}/user/${user}/todos`);
  }

  deleteTodo(user: string, id: number){
    return this.client.delete(`${API_URL}/user/${user}/todos/${id}`);
  }

  createToDo(user: string, todo: ToDo) {
    return this.client.post(`${API_URL}/user/${user}/todos`, todo);
  }

  updateToDo(user: string, id: number, todo: ToDo){
    return this.client.put(`${API_URL}/user/${user}/todos/${id}`, todo);
  }

  getTodo(user: string, id: number){
    return this.client.get<ToDo>(`${API_URL}/user/${user}/todos/${id}`);
  }

}
