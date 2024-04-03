package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/flights")
    public String test(@RequestParam(value = "arrIcao", defaultValue = "LFBD") String arrIcao) {
      return this.apiAviationEdge.getFlightByArrivalAirport(arrIcao);
    }

    @GetMapping("/initializeDatabase")
    public String initializeDatabase() {
      return this.apiAviationEdge.initializeDatabaseAirplane();
    }
}