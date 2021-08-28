package com.example.userservice.restcontroller;

import com.example.userservice.common.ApplicationVar;
import com.example.userservice.service.impl.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/population")
public class PopulationApi {
  @Autowired PopulationService populationService;

  @GetMapping("/country/{countryName}")
  public Object getPopulation(@PathVariable(value = "countryName") String countryName)
      throws IOException, InterruptedException {
      return populationService.getPopulation(countryName);
  }

  @GetMapping("/list")
  public Object getPopulationList(
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
      @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber)
      throws IOException, InterruptedException {
    return populationService.getList(pageSize, pageNumber);
  }

  @GetMapping("/top20")
  public Object getTop20() throws IOException, InterruptedException {
    return populationService.getList(20, 0);
  }

  /**
   * @return Current world population
   * @throws IOException
   * @throws InterruptedException
   */
  @GetMapping("/world")
  public Object getWorldPopulation() throws IOException, InterruptedException {
    return populationService.getWorldPopulation();
  }

  @GetMapping("/allCountry")
  public Object getCountry() throws IOException, InterruptedException {
    return populationService.getAllCountryName();
  }

  @GetMapping("/autocomplete/{key}")
  public Object autocompleteCountry(@PathVariable(value = "key", required = false) String key){
    return ApplicationVar.getInstance().getAllCountry().stream().filter(c -> c.startsWith(key)).collect(Collectors.toList());
  }
}
