SmartParking - Módulo Móvil

Herramienta:
MIT App Inventor (AI2 Companion)

Funcionalidades implementadas:
- Registro de ingreso de vehículo (POST /api/movimientos/ingreso)
- Registro de salida de vehículo (PUT /api/movimientos/salida/{id}?tarifaHora=...)

Pruebas realizadas:
- Comunicación exitosa con API REST desarrollada en Spring Boot
- Persistencia correcta en base de datos MySQL
- Cambio de estado ABIERTO a CERRADO

El módulo móvil fue probado desde dispositivo físico usando AI Companion.
