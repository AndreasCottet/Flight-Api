package com.example.demo;
import org.springframework.web.client.RestTemplate;

public class ApiAviationEdge {
    public String apiKey = "693142-6238dd";
    public String apiUrl = "https://aviation-edge.com/v2/public";

    public String slugFlights = "/flights";

    public String getFlightByArrivalAirport(String arrivalIcao)
    {
      String url = this.slugFlights + "?arrIcao=" + arrivalIcao;
      return this.get(url);
    }

    public String get(String urlAndParams)
    {
        RestTemplate restTemplate = new RestTemplate();
        String apiKeyString = (urlAndParams.contains("?") ? "&" : "?") + "key=" + this.apiKey;
        System.out.println(this.apiUrl + urlAndParams + apiKeyString);
        return restTemplate.getForObject(this.apiUrl + urlAndParams + apiKeyString, String.class);
    }

    
}