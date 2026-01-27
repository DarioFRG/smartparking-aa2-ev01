// ✅ Cambia el puerto si tu backend NO está en 8082
const API_BASE = "http://localhost:8082/api";

function setMsg(id, text, ok = true) {
  const el = document.getElementById(id);
  if (!el) return;
  el.className = ok ? "success" : "error";
  el.textContent = text;
}

async function apiGet(path) {
  const res = await fetch(`${API_BASE}${path}`);
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

async function apiPost(path, data) {
  const res = await fetch(`${API_BASE}${path}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  });
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

async function apiPut(path) {
  const res = await fetch(`${API_BASE}${path}`, { method: "PUT" });
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

// ===== VEHICULOS =====
async function cargarVehiculos() {
  try {
    const list = await apiGet("/vehiculos");
    document.getElementById("vehiculosJson").textContent = JSON.stringify(list, null, 2);
  } catch (e) {
    setMsg("vehiculosMsg", "Error cargando vehículos: " + e.message, false);
  }
}

async function crearVehiculo() {
  try {
    const data = {
      placa: document.getElementById("placa").value.trim(),
      tipo: document.getElementById("tipo").value.trim(),
      marca: document.getElementById("marca").value.trim(),
      color: document.getElementById("color").value.trim(),
      idUsuario: Number(document.getElementById("idUsuario").value),
      estado: 1
    };

    const created = await apiPost("/vehiculos", data);
    setMsg("vehiculosMsg", "✅ Vehículo creado con ID: " + created.idVehiculo, true);
    await cargarVehiculos();
  } catch (e) {
    setMsg("vehiculosMsg", "❌ Error creando vehículo: " + e.message, false);
  }
}

// ===== MOVIMIENTOS =====
async function ingreso() {
  try {
    const data = {
      idVehiculo: Number(document.getElementById("idVehiculo").value),
      idEspacio: Number(document.getElementById("idEspacio").value),
      idUsuarioRegistra: Number(document.getElementById("idUsuarioRegistra").value)
    };

    const mov = await apiPost("/movimientos/ingreso", data);
    setMsg("movMsg", "✅ Ingreso OK. idMovimiento: " + mov.idMovimiento, true);
    await listarAbiertos();
  } catch (e) {
    setMsg("movMsg", "❌ Error en ingreso: " + e.message, false);
  }
}

async function salida() {
  try {
    const id = Number(document.getElementById("idMovimientoSalida").value);
    const tarifa = Number(document.getElementById("tarifaHora").value);

    const mov = await apiPut(`/movimientos/salida/${id}?tarifaHora=${tarifa}`);
    setMsg("movMsg", "✅ Salida OK. Total a pagar: " + mov.totalPagar, true);
    await listarAbiertos();
  } catch (e) {
    setMsg("movMsg", "❌ Error en salida: " + e.message, false);
  }
}

async function listarAbiertos() {
  try {
    const list = await apiGet("/movimientos/abiertos");
    document.getElementById("movJson").textContent = JSON.stringify(list, null, 2);
  } catch (e) {
    setMsg("movMsg", "❌ Error listando abiertos: " + e.message, false);
  }
}
