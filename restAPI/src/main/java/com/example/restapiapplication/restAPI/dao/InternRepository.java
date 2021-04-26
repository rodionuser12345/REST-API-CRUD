package com.example.restapiapplication.restAPI.dao;

import com.example.restapiapplication.restAPI.domain.Intern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternRepository extends JpaRepository<Intern, Integer> {
}
