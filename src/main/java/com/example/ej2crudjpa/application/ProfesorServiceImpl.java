package com.example.ej2crudjpa.application;

import com.example.ej2crudjpa.dto.input.ProfesorInputDTO;
import com.example.ej2crudjpa.dto.output.ProfesorOutputDTO;
import com.example.ej2crudjpa.entity.Persona;
import com.example.ej2crudjpa.entity.Profesor;
import com.example.ej2crudjpa.repository.ProfesorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    PersonaServiceImpl ps;
    @Autowired
    ProfesorRepositorio profesorRepo;

    @Override
    public ProfesorOutputDTO insertarProfesor(ProfesorInputDTO profesorDTO) {

        Persona persona = ps.buscarPersonaPorId(profesorDTO.getId_persona());

        Profesor profesor = new Profesor(profesorDTO);
        profesor.setPersona(persona);
        profesorRepo.save(profesor);
        ProfesorOutputDTO saveOutputDTO = new ProfesorOutputDTO(profesor);
        return saveOutputDTO;

    }

    @Override
    public ProfesorOutputDTO editarProfesor(String id, Profesor profesor) {
        try {

            List<Profesor> listaProfesors = profesorRepo.findAll();
            for (int i = 0; i < listaProfesors.size(); i++) {
                Profesor e = listaProfesors.get(i);
                if (e.getId_profesor().equals(id)) {
                    listaProfesors.set(i, profesor);
                    profesorRepo.save(listaProfesors.get(i));
                }
            }
            Profesor p = (profesorRepo.findById(id)).get();
            return new ProfesorOutputDTO(p);
        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void eliminarProfesor(String id) {
        try {

            profesorRepo.deleteById(id);

        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Profesor buscarProfesorPorId(String id) {

//            Profesor p;
//            Optional<Profesor> profesorOptional = profesorRepo.findById(id);
//
//            if (profesorOptional.isPresent()) {
//                p = profesorOptional.get();
//                return new ProfesorOutputDTO(p);
//
//            } else {
//                throw new EntityNotFoundException();
//            }

        //        return Optional .ofNullable(profesorRepo.findById(id)) .orElseThrow(EntityNotFoundException::new).get();

                return profesorRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }


//    @Override
//    public List<ProfesorOutputDTO> buscarProfesorsPorName(String name) {
//        try {
//            List<ProfesorOutputDTO> listaProfesors = profesorRepo.findProfesorsByPersona(name);
//            return listaProfesors;
//        } catch (Exception e) {
//            throw new EntityNotFoundException();
//        }
//    }

        @Override
        public List<Profesor> dameAllProfesors () throws Exception {
            try {

                return profesorRepo.findAll();
            } catch (Exception e) {
                throw new Exception("No hay registros");
            }
        }
    }
