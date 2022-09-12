package com.example.ej2crudjpa.dto.input;

import com.example.ej2crudjpa.entity.Estudiante;
import lombok.Data;

import java.util.Date;

@Data
public class AsignaturaInputDTO {

    String id_asignatura;
    Estudiante estudiante;
    String asignatura;
    String coments;
    Date initial_date;
    Date finish_date;

}
