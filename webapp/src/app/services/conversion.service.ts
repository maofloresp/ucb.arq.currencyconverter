import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Conversion } from '../models/conversion';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { ConversionList } from '../models/conversionList';

@Injectable({
  providedIn: 'root'
})
export class ConversionService {

  constructor(private httpClient: HttpClient) { }

  postConversion(from: string, to: string, amount: number): Observable<Conversion>
  {
    const params = new HttpParams();

    return this.httpClient.post<any>(`${environment.BACKEND}/conversions?to=${to}&from=${from}&amount=${amount}`, params)

  }

  getConversions(page: number): Observable<ConversionList>
  {
    const params = new HttpParams()
    .set('page', page);

    return this.httpClient.get<any>(`${environment.BACKEND}/conversions`, { params });
  }
}
