package com.example.userservice;

import com.example.userservice.common.ApplicationVar;
import com.example.userservice.dto.CountryDto;
import com.example.userservice.dto.CountryNameListDto;
import com.example.userservice.entity.Country;
import com.example.userservice.repository.CountryRepository;
import com.example.userservice.service.impl.PopulationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application implements CommandLineRunner {
  @Autowired private PopulationService populationService;
  @Autowired private CountryRepository countryRepository;
  ThreadPoolExecutor pool =
      new ThreadPoolExecutor(30, 50, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(300));

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  /**
   * Sync population when startup
   *
   * @param args
   * @throws Exception
   */
  @Override
  public void run(String... args) throws Exception {
    CompletableFuture.runAsync(
        () -> {
          try {
            this.syncData();
          } catch (IOException e) {
            e.printStackTrace();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        });
  }

  private void syncData() throws IOException, InterruptedException {
    String body = (String) populationService.getAllCountryName();
    ObjectMapper objectMapper = new ObjectMapper();
    List<String> listCountries =
        objectMapper.readValue(body, CountryNameListDto.class).getBody().getCountries();
    List<Country> countryList = new ArrayList<>();
    // Cache country list
    ApplicationVar.getInstance()
        .setAllCountry(listCountries);
    for (String countryName : listCountries) {
      CompletableFuture.runAsync(
          () -> {
            CountryDto dto = null;
            try {
              dto =
                  objectMapper.readValue(
                      (String) populationService.getPopulation(countryName), CountryDto.class);
            } catch (IOException e) {
              e.printStackTrace();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            if (dto == null) {
              return;
            }
            Country country = new Country();
            country.setCountryName(dto.getBody().getCountryName());
            country.setRanking(dto.getBody().getRanking());
            country.setPopulation(dto.getBody().getPopulation());
            countryRepository.save(country);
          },
          pool);
    }
  }
}
