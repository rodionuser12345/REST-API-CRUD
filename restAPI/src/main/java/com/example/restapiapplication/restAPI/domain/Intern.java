package com.example.restapiapplication.restAPI.domain;

import com.example.restapiapplication.restAPI.converters.InternTwoSideConverter;
import com.example.restapiapplication.restAPI.dto.InternDTO;
import com.example.restapiapplication.restAPI.enums.Specialization;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "intern_db")
@NoArgsConstructor
@Data
public class Intern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @NotNull Integer id;
    private @NotNull String name;
    private @NotNull String email;
    private @NotNull Specialization specialization;
    private @NotNull Integer age;

    public Intern(InternDTO internDTO) {
        this(InternTwoSideConverter.convertFromInternDTOToIntern.apply(internDTO));
    }

    public Intern(Intern intern) {
        this.id = intern.getId();
        this.name = intern.getName();
        this.email = intern.getEmail();
        this.specialization = intern.getSpecialization();
        this.age = intern.getAge();
    }
}

















