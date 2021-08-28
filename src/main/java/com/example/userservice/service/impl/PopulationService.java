package com.example.userservice.service.impl;

import com.example.userservice.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class PopulationService {
  @Autowired private CountryRepository countryRepository;

  public Object getPopulation(String countryName) throws IOException, InterruptedException {
    countryName = URLEncoder.encode(countryName, StandardCharsets.UTF_8);
    String url =
        String.format(
            "https://world-population.p.rapidapi.com/population?country_name=%s", countryName);
    Object result = null;
    try {
      HttpResponse<String> response = this.doRequest(url);
      result  = response.body();
    } catch (Exception ex) {

    }
    return result;
  }

  public Object getWorldPopulation() throws IOException, InterruptedException {
    String url = "https://world-population.p.rapidapi.com/worldpopulation";
    Object result = null;
    try {
      HttpResponse<String> response = this.doRequest(url);
      result  = response.body();
    } catch (Exception ex) {

    }
    return result;
  }

  public Object getAllCountryName() throws IOException, InterruptedException {
    String url = "https://world-population.p.rapidapi.com/allcountriesname";
    Object result = null;
    try {
      HttpResponse<String> response = this.doRequest(url);
      result  = response.body();
    } catch (Exception ex) {

    }
    return result;
  }
  public Object getList(Integer pageSize, Integer pageNumber){
    Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("ranking"));
    return countryRepository.findAll(pageable);
  }

  private HttpResponse<String> doRequest(String url) throws IOException, InterruptedException {
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("x-rapidapi-host", "world-population.p.rapidapi.com")
            .header("x-rapidapi-key", "ed74e2be46msha0be35e5f345febp17ac10jsn4262a33cbe07")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
    HttpResponse<String> response =
        HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.body());
    return response;
  }


}
