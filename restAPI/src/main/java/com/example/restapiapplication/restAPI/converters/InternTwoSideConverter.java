package com.example.restapiapplication.restAPI.converters;

import com.example.restapiapplication.restAPI.domain.Intern;
import com.example.restapiapplication.restAPI.dto.InternDTO;
import com.example.restapiapplication.restAPI.enums.Specialization;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class InternTwoSideConverter {

    public final static Function<Intern, InternDTO> convertFromInternToInternDTO = intern -> {
        InternDTO internDTO = new InternDTO();
        internDTO.setId(intern.getId().toString());
        internDTO.setName(intern.getName());
        internDTO.setEmail(intern.getEmail());
        internDTO.setSpecialization(intern.getSpecialization().toString());
        internDTO.setAge(intern.getAge().toString());
        return internDTO;
    };

    public final static Function<InternDTO, Intern> convertFromInternDTOToIntern = internDTO -> {
        Intern intern = new Intern();
        intern.setId(Integer.parseInt(internDTO.getId()));
        intern.setName(internDTO.getName());
        intern.setEmail(internDTO.getEmail());
        intern.setSpecialization(Specialization.valueOf(internDTO.getSpecialization()));
        intern.setAge(Integer.parseInt(internDTO.getAge()));
        return intern;
    };
}
