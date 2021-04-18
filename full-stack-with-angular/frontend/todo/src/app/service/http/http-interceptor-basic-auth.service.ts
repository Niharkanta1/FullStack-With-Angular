import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BasicAuthanticationService } from '../basic-authantication.service';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorBasicAuthService implements HttpInterceptor{

  constructor(private basicAuthService: BasicAuthanticationService) { }
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let headerStr = this.basicAuthService.getAuthenticatedToken() || '';
    let username = this.basicAuthService.getAuthenticatedUser();
    if(headerStr && username){
      request = request.clone({
        setHeaders: {
          Authorization: headerStr
        }
      })
    }
    return next.handle(request);
  }
}
