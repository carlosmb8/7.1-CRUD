package com.example.ej2crudjpa.controller;

import com.example.ej2crudjpa.application.PersonaServiceImpl;
import com.example.ej2crudjpa.application.ProfesorServiceImpl;
import com.example.ej2crudjpa.dto.input.PersonaInputDTO;
import com.example.ej2crudjpa.dto.output.PersonaOutputDTO;
import com.example.ej2crudjpa.dto.output.ProfesorOutputDTO;
import com.example.ej2crudjpa.entity.Persona;
import com.example.ej2crudjpa.feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class PersonaControlador {

    @Autowired
    PersonaServiceImpl personaServiceImpl;

    @Autowired
    ProfesorServiceImpl profesorServiceImpl;

    @Autowired
    RestTemplate clienteRest;

    @Autowired
    Feign clienteFeign;


    @GetMapping("personas/{id}")
    public PersonaOutputDTO damePersona(@PathVariable Integer id) {
            return new PersonaOutputDTO(personaServiceImpl.buscarPersonaPorId(id));
    }

    @PostMapping("personas")
    public List<PersonaOutputDTO> damePersonaPorNombre(@RequestParam String name) {

        return personaServiceImpl.buscarPersonaPorName(name);
    }

    @GetMapping("personas")
    public Iterable<Persona> damePersonas() throws Exception{

        return personaServiceImpl.dameAllPersonas();
    }

    @PostMapping("personas/insertar")
    public String insertaPersona(@RequestBody PersonaInputDTO personaDTO) {

        personaServiceImpl.insertarPersona(personaDTO);

        return "Persona insertada correctamente";

    }

    @PutMapping("personas/editar")
    public void editarPersona(@RequestParam Integer id, @RequestBody Persona persona) {
        personaServiceImpl.editarPersona(id, persona);
    }

    @DeleteMapping("personas/eliminar")
    public String eliminarPersona(@RequestParam Integer id) {
        personaServiceImpl.eliminarPersona(id);
        return "La persona con el id: " + id + " ha sido borrada correctamete";
    }



    //Usando RestTemplate
//    @GetMapping("personas/profesor/{id}")
//    public ProfesorOutputDTO getProfesorRest(@PathVariable String id){
//
//        return clienteRest.getForObject("http://localhost:8081/profesores/" + id, ProfesorOutputDTO.class);
//    }

    @GetMapping("personas/profesor/{id}")
    public ProfesorOutputDTO getProfesorFeing(@PathVariable String id){

        return clienteFeign.dameProfesor(id);
    }
}
