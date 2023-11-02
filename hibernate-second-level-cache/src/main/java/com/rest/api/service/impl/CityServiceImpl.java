package com.rest.api.service.impl;

import com.rest.api.dto.CityDto;
import com.rest.api.entity.City;
import com.rest.api.repository.CityRepository;
import com.rest.api.service.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;
    private ModelMapper modelMapper;

    @Autowired
    public void setCityRepository(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Cacheable(value = "cities", key = "#cityId")
    public CityDto getCityById(String id) {
        return modelMapper.map(cityRepository.findById(id).get(), CityDto.class);
    }

    public CityDto saveCity(CityDto cityDto) {
        return modelMapper.map(cityRepository.save(modelMapper.map(cityDto, City.class)), CityDto.class);
    }

    @Cacheable(value = "cities")
    @Override
    public List<CityDto> getCities() {
        return cityRepository.findAll().stream().map(city -> modelMapper.map(city, CityDto.class)).collect(Collectors.toList());
    }
}
