package com.example.ej2crudjpa.application;

import com.example.ej2crudjpa.dto.input.EstudianteInputDTO;
import com.example.ej2crudjpa.dto.output.EstudianteOutputDTO;
import com.example.ej2crudjpa.entity.Estudiante;

import java.util.List;

public interface EstudianteService {
    public EstudianteOutputDTO insertarEstudiante(EstudianteInputDTO estudianteDTO);
    public EstudianteOutputDTO editarEstudiante(String id, EstudianteInputDTO estudiante);
    public String eliminarEstudiante(String id);
    public Estudiante buscarEstudiantePorId(String id);

//    public List<EstudianteOutputDTO> buscarEstudiantesPorName(String name);

    public List<EstudianteOutputDTO> dameAllEstudiantes() throws Exception;
}
