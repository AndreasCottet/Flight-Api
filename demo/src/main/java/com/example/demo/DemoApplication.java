package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class DemoApplication {
    public String apiKey = "693142-6238dd";
    public String apiUrl = "https://aviation-edge.com/v2/public";
    public ApiAviationEdge apiAviationEdge;

    public String slugFlights = "/flights";
    public static void main(String[] args) {
      SpringApplication.run(DemoApplication.class, args);
    }

    public DemoApplication()
    {
      this.apiAviationEdge = new ApiAviationEdge();
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
      return String.format("Hello %s!", name);
    }

    @GetMapping("/test")
    public String test(@RequestParam(value = "name", defaultValue = "World") String name) {
      return this.apiAviationEdge.getFlightByArrivalAirport("LFBD");
    }
}