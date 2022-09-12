package com.example.ej2crudjpa.application;

import com.example.ej2crudjpa.dto.input.EstudianteInputDTO;
import com.example.ej2crudjpa.dto.output.EstudianteOutputDTO;
import com.example.ej2crudjpa.entity.Estudiante;
import com.example.ej2crudjpa.entity.Persona;
import com.example.ej2crudjpa.entity.Profesor;
import com.example.ej2crudjpa.excepciones.UnprocessableEntityException;
import com.example.ej2crudjpa.repository.EstudianteRepositorio;
import com.example.ej2crudjpa.repository.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EstudianteServiceImpl implements EstudianteService{

    @Autowired
    PersonaServiceImpl personaServiceImpl;
    @Autowired
    EstudianteRepositorio estudianteRepo;

    @Autowired
    ProfesorServiceImpl profesorServiceImpl;


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
    public void editarEstudiante(String id, Estudiante estudiante){
        try {

            List<Estudiante> listaEstudiantes = estudianteRepo.findAll();
            for (int i = 0; i < listaEstudiantes.size(); i++) {
                Estudiante e = listaEstudiantes.get(i);
                if (e.getId_estudiante().equals(id)) {
                    listaEstudiantes.set(i, estudiante);
                    estudianteRepo.save(listaEstudiantes.get(i));
                }
            }
        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
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
    public List<Estudiante> dameAllEstudiantes() throws Exception {
        try {

            return estudianteRepo.findAll();
        } catch (Exception e) {
            throw new Exception("No hay registros");
        }
    }
}
