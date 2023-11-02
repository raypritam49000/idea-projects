package com.rest.api.service;

import com.rest.api.dto.CityDto;
import java.util.List;

public interface CityService {
    public CityDto getCityById(String id);
    public CityDto saveCity(CityDto cityDto);
    public List<CityDto> getCities();
}
