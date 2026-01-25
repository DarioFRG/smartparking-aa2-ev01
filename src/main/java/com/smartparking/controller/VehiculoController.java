package com.smartparking.controller;

import com.smartparking.entity.Vehiculo;
import com.smartparking.service.VehiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final VehiculoService service;

    public VehiculoController(VehiculoService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Vehiculo> crear(@RequestBody Vehiculo vehiculo) {
        Vehiculo creado = service.crear(vehiculo);
        return ResponseEntity.ok(creado);
    }

    // READ (LIST)
    @GetMapping
    public ResponseEntity<List<Vehiculo>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // READ (BY ID)
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id) {
        Vehiculo v = service.buscarPorId(id);
        if (v == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(v);
    }

    // UPDATE ✅ (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Vehiculo vehiculo) {
        Vehiculo actualizado = service.actualizar(id, vehiculo);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    // DELETE ✅
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        boolean ok = service.eliminar(id);
        return ok ? ResponseEntity.ok("✅ Eliminado") : ResponseEntity.notFound().build();
    }
}


