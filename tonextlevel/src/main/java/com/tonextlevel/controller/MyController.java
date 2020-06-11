package com.tonextlevel.controller;

import com.tonextlevel.model.History;
import com.tonextlevel.service.HistoryService;
import com.tonextlevel.service.WeatherService;
import com.tonextlevel.util.ReportRequest;
import com.tonextlevel.util.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class MyController {

    @Autowired
    HistoryService historyService;

    @Autowired
    WeatherService weatherService;

    @PostMapping("/getExperienceNeeded")
    public String getExperienceNeeded(@org.springframework.web.bind.annotation.RequestBody RequestBody request){
        return historyService.calculateXP(request);
    }

    @GetMapping("/getHistoricalRecords")
    public List<History> getHistoricalRecords(){
        return historyService.findAll();
    }

    @PostMapping("/generateReport")
    public ResponseEntity<InputStreamResource> generateReport(@org.springframework.web.bind.annotation.RequestBody ReportRequest request){
        return historyService.generateReport(request);
    }

    @GetMapping("/getWeatherReport")
    public String getWeatherReport() throws IOException {
        return weatherService.getDurbanWeather();
    }
}
