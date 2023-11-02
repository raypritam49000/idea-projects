package com.mongodb.rest.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contact")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentContacts {
    @Id
    private String contactId;
    private String email;
    private String phone;
}
