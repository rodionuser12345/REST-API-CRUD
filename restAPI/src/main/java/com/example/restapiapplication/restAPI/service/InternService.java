package com.example.restapiapplication.restAPI.service;

import com.example.restapiapplication.restAPI.domain.Intern;
import com.example.restapiapplication.restAPI.dto.InternDTO;
import com.example.restapiapplication.restAPI.exceptions.CustomInternException;

import java.util.List;

public interface InternService {

    List<InternDTO> listAll(Integer limit);

    InternDTO getById(Integer id) throws CustomInternException;

    Intern saveIntern(InternDTO intern) throws CustomInternException;

    Intern updateIntern(InternDTO intern) throws CustomInternException;

    void deleteById(Integer id) throws CustomInternException;

    void delete(InternDTO internDTO) throws CustomInternException;
}
