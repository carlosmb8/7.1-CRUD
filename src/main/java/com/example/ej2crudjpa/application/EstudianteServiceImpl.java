package com.example.ej2crudjpa.application;

import com.example.ej2crudjpa.dto.input.EstudianteInputDTO;
import com.example.ej2crudjpa.dto.output.EstudianteOutputDTO;
import com.example.ej2crudjpa.entity.Estudiante;
import com.example.ej2crudjpa.entity.Persona;
import com.example.ej2crudjpa.entity.Profesor;
import com.example.ej2crudjpa.excepciones.UnprocessableEntityException;
import com.example.ej2crudjpa.repository.EstudianteRepositorio;
import com.example.ej2crudjpa.repository.PersonaRepositorio;
import com.example.ej2crudjpa.repository.ProfesorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService{

    @Autowired
    PersonaServiceImpl personaServiceImpl;

    @Autowired
    PersonaRepositorio personaRepo;
    @Autowired
    EstudianteRepositorio estudianteRepo;

    @Autowired
    ProfesorServiceImpl profesorServiceImpl;

    @Autowired
    ProfesorRepositorio profesorRepo;

    @Override
    public EstudianteOutputDTO insertarEstudiante(EstudianteInputDTO estudianteDTO){

            Persona persona = personaServiceImpl.buscarPersonaPorId(estudianteDTO.getId_persona());
            Profesor profesor = profesorServiceImpl.buscarProfesorPorId(estudianteDTO.getId_profesor());
            Estudiante estudiante = new Estudiante(estudianteDTO);
            estudiante.setPersona(persona);
            estudiante.setProfesor(profesor);

            estudianteRepo.save(estudiante);
            EstudianteOutputDTO saveOutputDTO = new EstudianteOutputDTO(estudiante);

            return saveOutputDTO;

    }

    @Override
    public EstudianteOutputDTO editarEstudiante(String id, EstudianteInputDTO estudianteInputDTO){

        Optional<Estudiante> estudianteOptional =  estudianteRepo.findById(id);
        if(estudianteOptional.isEmpty()){
            throw new EntityNotFoundException();
        }

        Estudiante estudiante = estudianteOptional.get();

        Persona persona = personaRepo.findById(estudianteInputDTO.getId_persona()).orElseThrow(EntityNotFoundException::new);
        Profesor profesor = profesorRepo.findById(estudianteInputDTO.getId_profesor()).orElseThrow(EntityNotFoundException::new);

        estudiante.setPersona(persona);
        estudiante.setNum_hours_week(estudianteInputDTO.getNum_hours_week());
        estudiante.setComents(estudianteInputDTO.getComents());
        estudiante.setProfesor(profesor);
        estudiante.setBranch(estudianteInputDTO.getBranch());

        estudianteRepo.save(estudiante);
        return new EstudianteOutputDTO(estudiante);
    }

    @Override
    public String eliminarEstudiante(String id) {
        try {

            estudianteRepo.deleteById(id);
            return "El estudiante con el id: " + id + ", ha sido borrado correctamete";

        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Estudiante buscarEstudiantePorId(String id) {

//        return Optional .ofNullable(estudianteRepo.findById(id)) .orElseThrow(EntityNotFoundException::new).get();
        Estudiante e = estudianteRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        return e;
    }

//    @Override
//    public List<EstudianteOutputDTO> buscarEstudiantesPorName(String name) {
//        try {
//            List<EstudianteOutputDTO> listaEstudiantes = estudianteRepo.findEstudiantesByPersona(name);
//            return listaEstudiantes;
//        } catch (Exception e) {
//            throw new EntityNotFoundException();
//        }
//    }

    @Override
    public List<EstudianteOutputDTO> dameAllEstudiantes() throws Exception {
        try {
            List<EstudianteOutputDTO> listaEstudiantesDTO = new ArrayList<>();
            List<Estudiante> listaEstudiantes = estudianteRepo.findAll();
            listaEstudiantes.forEach(estudiante ->{listaEstudiantesDTO.add(new EstudianteOutputDTO(estudiante));});
            return listaEstudiantesDTO;
        } catch (Exception e) {
            throw new Exception("No hay registros");
        }
    }
}
