package com.smartparking.controller;

import com.smartparking.entity.Movimiento;
import com.smartparking.service.MovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private final MovimientoService service;

    public MovimientoController(MovimientoService service) {
        this.service = service;
    }

    // ✅ INGRESO
    @PostMapping("/ingreso")
    public ResponseEntity<Movimiento> ingreso(@RequestBody Movimiento movimiento) {
        return ResponseEntity.ok(service.registrarIngreso(movimiento));
    }

    // ✅ SALIDA (con tarifa)
    @PutMapping("/salida/{idMovimiento}")
    public ResponseEntity<?> salida(@PathVariable Integer idMovimiento,
                                    @RequestParam(defaultValue = "2500") int tarifaHora) {

        Movimiento salida = service.registrarSalida(idMovimiento, tarifaHora);
        if (salida == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(salida);
    }

    // ✅ LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<Movimiento>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // ✅ LISTAR ABIERTOS
    @GetMapping("/abiertos")
    public ResponseEntity<List<Movimiento>> listarAbiertos() {
        return ResponseEntity.ok(service.listarAbiertos());
    }
}

