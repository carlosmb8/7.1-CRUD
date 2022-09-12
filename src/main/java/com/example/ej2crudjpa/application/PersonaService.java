package com.example.ej2crudjpa.application;

import com.example.ej2crudjpa.dto.input.PersonaInputDTO;
import com.example.ej2crudjpa.dto.output.PersonaOutputDTO;
import com.example.ej2crudjpa.entity.Persona;

import java.util.List;

public interface PersonaService {
    public PersonaOutputDTO insertarPersona(PersonaInputDTO personaDTO);
    public void editarPersona(Integer id, Persona persona);
    public void eliminarPersona(Integer id);
    public Persona buscarPersonaPorId(Integer id);
    public List<PersonaOutputDTO> buscarPersonaPorName(String name);
    public List<Persona> dameAllPersonas() throws Exception;

}
