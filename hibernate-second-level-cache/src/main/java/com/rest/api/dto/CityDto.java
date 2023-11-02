package com.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CityDto {
    private String cityId;
    private String name;
    private Long population;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDto cityDto = (CityDto) o;
        return Objects.equals(cityId, cityDto.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId);
    }
}
