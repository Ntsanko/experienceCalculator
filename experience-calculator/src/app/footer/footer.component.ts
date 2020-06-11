import { Component, OnInit } from '@angular/core';
import { HistoryService} from '../rest/history.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  weather: string;
  constructor(private rest:HistoryService) { }

  ngOnInit(): void {
    this.getWeatherReport();
  }

  getWeatherReport(){
    this.rest.getDurbanWeather().subscribe(
      response =>{
        this.weather = response;
      }
    )
  }

}
