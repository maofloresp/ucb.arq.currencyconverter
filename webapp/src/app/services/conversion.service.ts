import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Conversion } from '../models/conversion';
import { Observable, throwError } from 'rxjs';
//import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ConversionService {

  conversions: Conversion[] = [];

  constructor(private httpClient: HttpClient) { }

  postConversion(from: string, to: string, amount: number): Observable<Conversion>
  {
    const params = new HttpParams();

    return this.httpClient.post<any>(`http://localhost:8080/conversions?to=${to}&from=${from}&amount=${amount}`, params)

  }

  getConversions(page: number): Observable<Conversion[]>
  {
    const params = new HttpParams()
    .set('page', page);

    return this.httpClient.get<any>('http://localhost:8080/conversions', { params });
  }
}
