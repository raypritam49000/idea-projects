package com.mongodb.rest.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {
    private String addressId;
    private String city;
    private String state;
    private String country;
}
