package com.smartparking.service;

import com.smartparking.entity.Movimiento;
import com.smartparking.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoService {

    private final MovimientoRepository repo;

    public MovimientoService(MovimientoRepository repo) {
        this.repo = repo;
    }

    // ✅ Registrar ingreso
    public Movimiento registrarIngreso(Movimiento movimiento) {
        movimiento.setFechaIngreso(LocalDateTime.now());
        movimiento.setEstado("ABIERTO");
        movimiento.setTotalPagar(null);
        movimiento.setFechaSalida(null);
        return repo.save(movimiento);
    }

    // ✅ Registrar salida y calcular pago
    public Movimiento registrarSalida(int idMovimiento, int tarifaHora) {

        Movimiento mov = repo.findById(idMovimiento).orElse(null);
        if (mov == null) return null;

        mov.setFechaSalida(LocalDateTime.now());
        mov.setEstado("CERRADO");

        // cálculo simple por horas (mínimo 1 hora)
        long minutos = Duration.between(mov.getFechaIngreso(), mov.getFechaSalida()).toMinutes();
        long horas = (minutos / 60);
        if (minutos % 60 != 0) horas++;
        if (horas == 0) horas = 1;

        int total = (int) (horas * tarifaHora);
        mov.setTotalPagar(total);

        return repo.save(mov);
    }

    public List<Movimiento> listar() {
        return repo.findAll();
    }

    public List<Movimiento> listarAbiertos() {
        return repo.findByEstado("ABIERTO");
    }
}

