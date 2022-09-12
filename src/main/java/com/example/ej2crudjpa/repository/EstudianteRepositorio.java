package com.example.ej2crudjpa.repository;

import com.example.ej2crudjpa.dto.output.EstudianteOutputDTO;
import com.example.ej2crudjpa.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudianteRepositorio extends JpaRepository<Estudiante, String> {

//    List<EstudianteOutputDTO> findEstudiantesByPersona(String name);
}
