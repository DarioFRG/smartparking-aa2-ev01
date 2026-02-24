package com.smartparking.service;

import com.smartparking.entity.Vehiculo;
import com.smartparking.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {

    private final VehiculoRepository repo;

    public VehiculoService(VehiculoRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public Vehiculo crear(Vehiculo vehiculo) {
        if (vehiculo.getEstado() == null) vehiculo.setEstado(1);
        return repo.save(vehiculo);
    }

    // READ (LIST)
    public List<Vehiculo> listar() {
        return repo.findAll();
    }

    // READ (BY ID)
    public Vehiculo buscarPorId(int id) {
        return repo.findById(id).orElse(null);
    }

    // UPDATE ✅
    public Vehiculo actualizar(int id, Vehiculo nuevo) {
        return repo.findById(id).map(actual -> {
            actual.setPlaca(nuevo.getPlaca());
            actual.setTipo(nuevo.getTipo());
            actual.setMarca(nuevo.getMarca());
            actual.setColor(nuevo.getColor());
            actual.setIdUsuario(nuevo.getIdUsuario());
            actual.setEstado(nuevo.getEstado());
            return repo.save(actual);
        }).orElse(null);
    }

    // DELETE ✅
    public boolean eliminar(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}


