package com.noelh.mediscreennotes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {

    private String PatientName;
    private String noteOfThePractitioner;
}
