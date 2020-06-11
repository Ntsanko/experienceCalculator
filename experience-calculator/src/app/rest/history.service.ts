import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })
  export class HistoryService{

    constructor(private http: HttpClient){}

    getAllHistory(){
        return this.http.get<History[]>(`${API_URL}/getHistoricalRecords`);
    }

    calculateExperience(request){
      return this.http.post(`${API_URL}/getExperienceNeeded`, request, {responseType: 'text'});
    }

    downloadReport(request){
      return this.http.post(`${API_URL}/generateReport`, request, {responseType: 'blob'});
    }

    getDurbanWeather(){
      let headers = new Headers();
      return this.http.get(`${API_URL}/getWeatherReport`, {responseType: 'text'});
    }

  }

  export class History{
      id: number;
      mFromLevel: number;
      mToLevel: number;
      mExperienceNeeded: number;
      mResultingRank: string;
  }

  export const API_URL = 'http://localhost:8080';