package com.example.ej2crudjpa.repository;

import com.example.ej2crudjpa.dto.output.PersonaOutputDTO;
import com.example.ej2crudjpa.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {

    List<Persona> findByName(String name);
}
