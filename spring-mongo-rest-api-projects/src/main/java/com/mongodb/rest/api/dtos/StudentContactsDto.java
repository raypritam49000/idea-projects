package com.mongodb.rest.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentContactsDto {
    private String contactId;
    private String email;
    private String phone;
}
