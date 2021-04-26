package com.example.restapiapplication.restAPI.service;

import com.example.restapiapplication.restAPI.converters.InternTwoSideConverter;
import com.example.restapiapplication.restAPI.dao.InternRepository;
import com.example.restapiapplication.restAPI.domain.Intern;
import com.example.restapiapplication.restAPI.dto.InternDTO;
import com.example.restapiapplication.restAPI.exceptions.CustomInternException;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Service
@Data
public class InternServiceImpl implements InternService {

    private final InternRepository internRepository;

    @Override
    public List<InternDTO> listAll(Integer limit) {
        List<Intern> internList = internRepository.findAll();
        List<InternDTO> internDTOS = internRepository
                .findAll()
                .stream()
                .map(InternTwoSideConverter.convertFromInternToInternDTO)
                .collect(Collectors.toList());
        List<InternDTO> dtoList = new ArrayList<>();
        if (limit != null) {
            for (int i = 0; i < limit; i++) {
                dtoList.add(internDTOS.get(i));
                if (i == internList.size() - 1) {
                    break;
                }
            }
            return dtoList;
        } else
            return internDTOS;
    }

    @Override
    public InternDTO getById(Integer id) throws CustomInternException {
        Optional<InternDTO> internDTO = Optional.ofNullable(InternTwoSideConverter
                .convertFromInternToInternDTO
                .apply(internRepository.findById(id)
                        .orElse(null)));
        if (internDTO.isPresent()) {
            return new InternDTO(internDTO.get());
        } else
        throw new CustomInternException("The user could not be found", NOT_FOUND);
    }

    @Override
    public Intern saveIntern(InternDTO intern) throws CustomInternException {
        try {
            return internRepository.save(new Intern(intern));
        } catch (Exception e) {
            throw new CustomInternException("Data Source issue, user could not be saved", INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Intern updateIntern(InternDTO internDTO) throws CustomInternException {
        Optional<Intern> intern = internRepository.findById(Integer.parseInt(internDTO.getId()));
        if (intern.isPresent()) {
            Intern internToBeUpdated = new Intern(internDTO);
            return internRepository.save(internToBeUpdated);
        } else
            throw new CustomInternException("The user could not be found", UNPROCESSABLE_ENTITY);
    }

    @Override
    public void deleteById(Integer id) throws CustomInternException {
        Optional<InternDTO> internDTO = Optional.ofNullable(InternTwoSideConverter
                .convertFromInternToInternDTO
                .apply(internRepository.findById(id)
                        .orElse(null)));
        if (internDTO.isPresent()) {
            internRepository.deleteById(id);
        } else
            throw new CustomInternException("The user could not be found", NO_CONTENT);
    }

    public void delete(InternDTO internDTO) throws CustomInternException {
        Optional<InternDTO> internDTOToDelete = Optional.ofNullable(InternTwoSideConverter
                .convertFromInternToInternDTO
                .apply(internRepository.findById(Integer.parseInt(internDTO.getId()))
                        .orElse(null)));
        if (internDTOToDelete.isPresent()) {
            internRepository.delete(InternTwoSideConverter.convertFromInternDTOToIntern.apply(internDTO));
        } else
            throw new CustomInternException("The user could not be found", NO_CONTENT);
    }
}
