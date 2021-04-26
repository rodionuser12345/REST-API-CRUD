package com.example.restapiapplication.restAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternDTO {
    private String id;
    private String name;
    private String email;
    private String specialization;
    private String age;

    public InternDTO(InternDTO internDTO) {
        this.id = internDTO.getId();
        this.name = internDTO.getName();
        this.email = internDTO.getEmail();
        this.specialization = internDTO.getSpecialization();
        this.age = internDTO.getAge();
    }
}

