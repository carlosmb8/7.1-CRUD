package com.example.ej2crudjpa.application;

import com.example.ej2crudjpa.dto.input.AsignaturaInputDTO;
import com.example.ej2crudjpa.dto.output.AsignaturaOutputDTO;
import com.example.ej2crudjpa.entity.Asignatura;

import java.util.List;

public interface AsignaturaService {
    public AsignaturaOutputDTO insertarAsignatura(AsignaturaInputDTO asignaturaDTO);
    public  AsignaturaOutputDTO editarAsignatura(String id, Asignatura asignatura);
    public void eliminarAsignatura(String id);
    public Asignatura buscarAsignaturaPorId(String id);
//    public List<AsignaturaOutputDTO> buscarAsignaturasPorName(String name);
    public List<Asignatura> dameAllAsignaturas() throws Exception;

    public void asociarAsignatura(String id_asignatura, String id_estudiante);
}
