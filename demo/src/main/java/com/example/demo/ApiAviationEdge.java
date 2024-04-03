package com.example.demo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiAviationEdge {
    public String apiKey = "693142-6238dd";
    public String apiUrl = "https://aviation-edge.com/v2/public";

    public String slugFlights = "/flights";
    public String slugAirplaneDatabase = "/airplaneDatabase";

    public String getFlight(String arrivalIcao)
    {
      String url = this.slugFlights + "?arrIcao=" + arrivalIcao;
      JSONParser jsonParser = new JSONParser(this.get(url));
      try {
        jsonParser.list().forEach((object) -> {
            System.out.println(object.toString());
          });
    } catch (ParseException e) {
        e.printStackTrace();
    }
      return this.get(url);
    }

    public String getFlightByArrivalAirport(String arrivalIcao)
    {
      String url = this.slugFlights + "?arrIcao=" + arrivalIcao;
      JSONParser jsonParser = new JSONParser(this.get(url));
      try {
        jsonParser.list().forEach((object) -> {
            System.out.println(object.toString());
          });
    } catch (ParseException e) {
        e.printStackTrace();
    }
      return this.get(url);
    }

    public String initializeDatabaseAirplane()
    {
        System.out.println("Début de l'import : " + LocalDate.now());
        String dbAirplane = this.get(slugAirplaneDatabase);
        ObjectMapper objectMapper = new ObjectMapper();
        List<JsonNode> listAircrafts = new ArrayList<JsonNode>();

        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(dbAirplane);
            jsonNode.elements().forEachRemaining((object) -> {
                String aircraftModelCode = object.get("modelCode").asText(); 
                if (aircraftModelCode.startsWith("B")) {
                    listAircrafts.add(object);
                }
            });
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("Import de la database fini : " + LocalDate.now());

        System.out.println("Nombre de boeing : " + listAircrafts.size());
        try {
            return objectMapper.writeValueAsString(listAircrafts);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }  
    }

    public String get(String urlAndParams)
    {
        RestTemplate restTemplate = new RestTemplate();
        String apiKeyString = (urlAndParams.contains("?") ? "&" : "?") + "key=" + this.apiKey;
        System.out.println(this.apiUrl + urlAndParams + apiKeyString);
        return restTemplate.getForObject(this.apiUrl + urlAndParams + apiKeyString, String.class);
    }


    
}