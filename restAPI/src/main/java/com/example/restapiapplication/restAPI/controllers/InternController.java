package com.example.restapiapplication.restAPI.controllers;

import com.example.restapiapplication.restAPI.dto.InternDTO;
import com.example.restapiapplication.restAPI.service.InternService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/interns")
public class InternController {

    private final InternService internService;

    @GetMapping("/")
    public ResponseEntity<Object> users(@RequestParam(required = false) Integer limit) {
        return new ResponseEntity<>(internService.listAll(limit), OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody @Valid InternDTO internDTO,
                                      BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            internService.saveIntern(null);
        }
        internService.saveIntern(internDTO);
        return new ResponseEntity<>("All good, user saved", CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> remove(@PathVariable @Valid Integer id,
                                         BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            internService.deleteById(null);
        }
        internService.deleteById(id);
        return new ResponseEntity<>("User deleted", OK);
    }

    @DeleteMapping("/delete/intern")
    public ResponseEntity<Object> remove(@RequestBody @Valid InternDTO internDTO,
                                         BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            internService.delete(null);
        }
        internService.delete(internDTO);
        return new ResponseEntity<>("User deleted", OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserDetails(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(internService.getById(id), OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUserDetails(@RequestBody @Valid InternDTO internDTO,
                                                    BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            internService.updateIntern(null);
        }
        internService.updateIntern(internDTO);
        return new ResponseEntity<>("User updated successfully", OK);
    }

}
