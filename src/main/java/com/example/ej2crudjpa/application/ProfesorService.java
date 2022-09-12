package com.example.ej2crudjpa.application;

import com.example.ej2crudjpa.dto.input.ProfesorInputDTO;
import com.example.ej2crudjpa.dto.output.ProfesorOutputDTO;
import com.example.ej2crudjpa.entity.Profesor;

import java.util.List;

public interface ProfesorService {
    public ProfesorOutputDTO insertarProfesor(ProfesorInputDTO profesorDTO);
    public ProfesorOutputDTO editarProfesor(String id, Profesor profesor);
    public void eliminarProfesor(String id);
    public Profesor buscarProfesorPorId(String id);
//    public List<ProfesorOutputDTO> buscarProfesorsPorName(String name);
    public List<Profesor> dameAllProfesors() throws Exception;
}
