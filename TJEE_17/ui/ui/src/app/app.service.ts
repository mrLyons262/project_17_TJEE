import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) {}

  generateHash(value: string): Observable<string> {
    const params = new HttpParams().set('value', value);

    return this.http.post<string>('/WCY20KC1S0_Michta_hash-generator/api/generate-hash', null,
      {
        params,
        responseType: 'text' as 'json'
      }
    );
  }

  getHashList(): Observable<[]> {
    return this.http.get<[]>('/WCY20KC1S0_Michta_data-provider/api/hash');
  }

  saveHash(hash: string): Observable<void> {
    const params = new HttpParams().set('value', hash);
    return this.http.post<void>('/WCY20KC1S0_Michta_data-provider/api/hash', null,
      {
        params
      });
  }

  deleteHash(hash: string): Observable<void> {
    const params = new HttpParams().set('value', hash);
    return this.http.delete<void>('/WCY20KC1S0_Michta_data-provider/api/hash',
      {
        params
      });
  }
}
