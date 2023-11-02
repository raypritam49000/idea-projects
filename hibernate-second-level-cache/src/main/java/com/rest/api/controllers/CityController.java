package com.rest.api.controllers;

import com.rest.api.dto.CityDto;
import com.rest.api.entity.City;
import com.rest.api.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {


    private CityService cityService;

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(cityService.getCityById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CityDto> saveCity(@RequestBody CityDto cityDto) {
        return new ResponseEntity<>(cityService.saveCity(cityDto), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CityDto>> getCities() {
        return new ResponseEntity<>(cityService.getCities(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CityDto> createCity(CityDto cityDto) {
        return new ResponseEntity<>(cityService.saveCity(cityDto), HttpStatus.CREATED);
    }

}
