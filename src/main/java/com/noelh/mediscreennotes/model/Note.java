package com.noelh.mediscreennotes.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Note {

    @Id
    private String id;

    private Long PatientId;
    private String noteOfThePractitioner;
}
