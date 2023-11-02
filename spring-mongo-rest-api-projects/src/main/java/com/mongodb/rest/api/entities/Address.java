package com.mongodb.rest.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Id
    private String addressId;
    private String city;
    private String state;
    private String country;
}
