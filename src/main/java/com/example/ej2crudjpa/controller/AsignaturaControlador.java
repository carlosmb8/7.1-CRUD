package com.example.ej2crudjpa.controller;

import com.example.ej2crudjpa.application.AsignaturaServiceImpl;
import com.example.ej2crudjpa.dto.input.AsignaturaInputDTO;
import com.example.ej2crudjpa.dto.output.AsignaturaOutputDTO;
import com.example.ej2crudjpa.entity.Asignatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AsignaturaControlador {

    @Autowired
    AsignaturaServiceImpl asignaturaServiceImpl;

    @GetMapping("asignaturas/{id}")
    public Asignatura dameAsignatura(@PathVariable String id, @RequestParam String outputType) {
        return asignaturaServiceImpl.buscarAsignaturaPorId(id);
    }

//    @PostMapping("asignaturas")
//    public List<AsignaturaOutputDTO> dameAsignaturaPorNombre(@RequestParam String name) {
//
//        return asignaturaServiceImpl.buscarAsignatura_AsignaturasPorName(name);
//    }

    @GetMapping("asignaturas")
    public Iterable<Asignatura> dameAsignaturas() throws Exception{

        return asignaturaServiceImpl.dameAllAsignaturas();
    }

    @PostMapping("asignaturas/insertar")
    public String insertaAsignatura(@RequestBody AsignaturaInputDTO asignaturaDTO) {

        asignaturaServiceImpl.insertarAsignatura(asignaturaDTO);

        return "Asignatura insertada correctamente";

    }

    @PutMapping("asignaturas/editar")
    public AsignaturaOutputDTO editarAsignatura(@RequestParam String id, @RequestBody Asignatura asignatura) {

        return asignaturaServiceImpl.editarAsignatura(id, asignatura);
    }

    @DeleteMapping("asignaturas/eliminar")
    public String eliminarAsignatura(@RequestParam String id) {
        asignaturaServiceImpl.eliminarAsignatura(id);
        return "La asignatura con el id: " + id + " ha sido borrada correctamete";
    }




    @PostMapping("asignaturas/asociar/{id_asignatura}/{id_estudiante}")
    public void asociarAsignautra(@PathVariable String id_asignatura, @PathVariable String id_estudiante){
        asignaturaServiceImpl.asociarAsignatura(id_asignatura, id_estudiante);
    }

}
