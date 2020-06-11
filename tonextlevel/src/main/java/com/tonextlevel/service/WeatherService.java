package com.tonextlevel.service;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherService {

    public String getDurbanWeather() throws IOException {
        /*
            Basically creates an api wrapper to openweathermap using an api key, changes the units
            from default(Imperial) to metric and then sends a request to get current weather data.
            Passed in city name and country code for increased accuracy.
         */
        OpenWeatherMap owm = new OpenWeatherMap("f63e469338e4ca314690f4381aac6359");
        owm.setUnits(OpenWeatherMap.Units.METRIC);
        CurrentWeather cw = owm.currentWeatherByCityName("Durban", "Za");

        return "It is currently " + cw.getMainInstance().getTemperature() + " Degrees Celsius in " + cw.getCityName();
    }
}
