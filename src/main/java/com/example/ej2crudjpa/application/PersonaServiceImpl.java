package com.example.ej2crudjpa.application;

import com.example.ej2crudjpa.dto.input.PersonaInputDTO;
import com.example.ej2crudjpa.dto.output.PersonaOutputDTO;
import com.example.ej2crudjpa.entity.Persona;
import com.example.ej2crudjpa.excepciones.UnprocessableEntityException;
import com.example.ej2crudjpa.repository.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    PersonaRepositorio personaRepo;

    @Override
    public PersonaOutputDTO insertarPersona(PersonaInputDTO personaDTO){
        if (personaDTO.getUsuario().length() > 10) {
            throw new UnprocessableEntityException();
        } else {
            Persona persona = new Persona(personaDTO);
            personaRepo.save(persona);
            PersonaOutputDTO saveOutputDTO = new PersonaOutputDTO(persona);
            return saveOutputDTO;
        }
    }

    @Override
    public void editarPersona(Integer id, Persona persona){
        try {

            List<Persona> listapersonas = personaRepo.findAll();
            for (int i = 0; i < listapersonas.size(); i++) {
                Persona p = listapersonas.get(i);
                if (p.getId_persona().equals(id)) {
                    listapersonas.set(i, persona);
                    personaRepo.save(listapersonas.get(i));
                }
            }
        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void eliminarPersona(Integer id) {
        try {

            personaRepo.deleteById(id);

        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Persona buscarPersonaPorId(Integer id) {

//        return Optional .ofNullable(personaRepo.findById(id)) .orElseThrow(EntityNotFoundException::new).get();

        return personaRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<PersonaOutputDTO> buscarPersonaPorName(String name) {
        try {
            List<Persona> listaPersonas = personaRepo.findByName(name);
            List<PersonaOutputDTO> listaPersonasDTO = new ArrayList<>();
            listaPersonas.forEach((p)-> {
                listaPersonasDTO.add(new PersonaOutputDTO(p));
            });
            return listaPersonasDTO;
        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<Persona> dameAllPersonas() throws Exception {
        try {

            return personaRepo.findAll();
        } catch (Exception e) {
            throw new Exception("No hay registros");
        }
    }
}
