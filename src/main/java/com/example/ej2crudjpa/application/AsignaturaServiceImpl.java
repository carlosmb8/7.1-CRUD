package com.example.ej2crudjpa.application;

import com.example.ej2crudjpa.dto.input.AsignaturaInputDTO;
import com.example.ej2crudjpa.dto.output.AsignaturaOutputDTO;
import com.example.ej2crudjpa.entity.Asignatura;
import com.example.ej2crudjpa.entity.Estudiante;
import com.example.ej2crudjpa.repository.AsignaturaRepositorio;
import com.example.ej2crudjpa.repository.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    AsignaturaRepositorio asignaturaRepo;

    @Autowired
    EstudianteRepositorio estudianteRepo;

    @Override
    public AsignaturaOutputDTO insertarAsignatura(AsignaturaInputDTO asignaturaDTO){
            
            Asignatura asignatura = new Asignatura(asignaturaDTO);
            asignaturaRepo.save(asignatura);
            AsignaturaOutputDTO saveOutputDTO = new AsignaturaOutputDTO(asignatura);
            return saveOutputDTO;
    }

    /*
    * Lo podria haber hecho directamente con findById en lugar de traerme todos los registros y luego recorrerlos
    * hasta encontrar el que tiene la id pasada por parametro pero para usar findAll en algún lugar lo he hecho así
    */
    @Override
    public AsignaturaOutputDTO editarAsignatura(String id, Asignatura asignatura){
        try {

            List<Asignatura> listaAsignaturas = asignaturaRepo.findAll();
            for (int i = 0; i < listaAsignaturas.size(); i++) {
                Asignatura e = listaAsignaturas.get(i);
                if (e.getId_asignatura().equals(id)) {
                    listaAsignaturas.set(i, asignatura);
                    asignaturaRepo.save(listaAsignaturas.get(i));

                }
            }
            Asignatura a = (asignaturaRepo.findById(id)).get();
            return new AsignaturaOutputDTO(a);

        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void eliminarAsignatura(String id) {
        try {

            asignaturaRepo.deleteById(id);

        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Asignatura buscarAsignaturaPorId(String id) {

//        return Optional .ofNullable(asignaturaRepo.findById(id)) .orElseThrow(EntityNotFoundException::new).get();

        return asignaturaRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

//    @Override
//    public List<AsignaturaOutputDTO> buscarAsignaturaPorName(String name) {
//        try {
//            List<AsignaturaOutputDTO> listaAsignaturas = asignaturaRepo.findAsignaturas(name);
//            return listaAsignaturas;
//        } catch (Exception e) {
//            throw new EntityNotFoundException();
//        }
//    }

    @Override
    public List<Asignatura> dameAllAsignaturas() throws Exception {
        try {

            return asignaturaRepo.findAll();
        } catch (Exception e) {
            throw new Exception("No hay registros");
        }
    }

    @Override
    @Transactional
    public void asociarAsignatura(String id_asignatura, String id_estudiante) {

        Asignatura asignatura = asignaturaRepo.findById(id_asignatura).orElseThrow(EntityNotFoundException::new);
        Estudiante estudiante = estudianteRepo.findById(id_estudiante).orElseThrow(EntityNotFoundException::new);

//        estudiante.getAsignaturas().add(asignatura);
//        estudianteRepo.save(estudiante);

        asignatura.getEstudiantes().add(estudiante);
//        asignaturaRepo.save(asignatura);

//
    }
}
