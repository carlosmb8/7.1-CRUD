package com.example.ej2crudjpa.repository;

import com.example.ej2crudjpa.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfesorRepositorio extends JpaRepository<Profesor, String> {

}
