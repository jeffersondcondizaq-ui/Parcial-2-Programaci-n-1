# Datos de Prueba — PixelRent (video de 8 minutos)

Datos mínimos organizados en cuadros. Sigue el orden de las tablas — la de Reservas es
cronológica y cada fila depende de la anterior.

> La empresa se crea automáticamente al iniciar: `Empresa("900123456-7", "PixelRent", "Calle 10 # 20-30")`

---

## 1. Sedes

| Código | Nombre | Dirección | Acción |
|---|---|---|---|
| SED01 | Sede Norte | Cra 15 # 100-20 | Registrar → Actualizar (dejar o variar datos) |
| SED02 | Sede Centro | Calle 45 # 10-30 | Registrar → **Eliminar** (demo de borrado) |

---

## 2. Equipos (registrar los 3 en `SED01`)

| Código | Tipo | Marca | Modelo | Campo especial 1 | Campo especial 2 | Tarifa / hora | Recargo |
|---|---|---|---|---|---|---|---|
| CAM01 | Cámara | Sony | A7III | Resolución: 4K | Estabilizador: Sí | $12.000 | +$15.000 |
| DRO01 | Dron | DJI | Mavic 3 | Autonomía: 45 min | Batería adicional: Sí | $18.000 | +$20.000 |
| KIT01 | Kit Iluminación | Godox | SL60 | Luces: 4 | Control inalámbrico: Sí | $10.000 | +$12.000 |

**Después de registrar:** Mostrar Cámaras / Drones / Kits · Actualizar `CAM01` (cambiar marca) ·
Consultar Equipos Disponibles por Fecha/Turno `F1` → deben salir los 3.

---

## 3. Clientes

| Identificación | Nombre | Teléfono | Correo |
|---|---|---|---|
| C001 | Laura Gómez | 3001112233 | laura@mail.com |
| C002 | Andrés Pérez | 3012223344 | andres@mail.com |

Registrar ambos → Mostrar Clientes (**no hay opción "Actualizar" — es el requisito de inmutabilidad**) → Buscar `C001`.

---

## 4. Operadores

| Identificación | Nombre | Certificación | Años exp. | Nota |
|---|---|---|---|---|
| OP001 | Carlos Ruiz | Piloto Avanzado | 5 | Se usa en las reservas |
| OP002 | Sofía Herrera | Piloto Básico | 2 | Solo para probar "ya tiene operador" |

Registrar ambos → Actualizar `OP002` → Mostrar Operadores.

---

## 5. Fechas/turnos de reserva

| Código | Fecha | Turno |
|---|---|---|
| F1 | 2026-11-05 | MAÑANA |
| F2 | 2026-11-06 | TARDE |

---

## 6. Reservas — secuencia cronológica (núcleo del video)

| # | Reserva | Cliente | Fecha/Turno | Acción | Resultado esperado |
|---|---|---|---|---|---|
| 1 | RES01 | C001 | F1 | Crear + agregar `CAM01` (2h). **No confirmar aún.** | Queda `SOLICITADA` |
| 2 | RES03 | C002 | F1 | Crear + agregar `CAM01` (1h) | ✅ se permite (RES01 aún no confirmada) |
| 3 | RES01 | — | — | Confirmar | ✅ `CONFIRMADA` |
| 4 | RES03 | — | — | Confirmar | ❌ `CAM01` ya está en una reserva **confirmada** en F1 |
| 5 | RES02 | C002 | F2 | Crear + agregar `DRO01` (2h) | — |
| 6 | RES02 | — | — | Confirmar (sin operador) | ❌ dron sin operador asignado |
| 7 | RES02 | — | — | Asignar Operador `OP001` → Confirmar de nuevo | ✅ `CONFIRMADA` |
| 8 | RES02 | — | — | Asignar Operador `OP002` otra vez | ❌ la reserva ya tiene un operador |
| 9 | RES02 | — | — | Agregar `DRO01` otra vez | ❌ equipo ya incluido en la reserva |
| 10 | RES04 | C001 | F2 | Crear → Confirmar sin equipos | ❌ reserva sin equipos |
| 11 | KIT01 | — | — | Cambiar Disponibilidad → `EN_MANTENIMIENTO` → agregar a RES04 | ❌ equipo no disponible |
| 12 | KIT01 / RES04 | — | — | Volver `KIT01` a `DISPONIBLE` → agregar a RES04 | ✅ agregado |
| 13 | RES04 | — | — | Cancelar | ✅ `CANCELADA` |
| 14 | RES04 | — | — | Confirmar de nuevo | ❌ cancelada no vuelve a confirmada |
| 15 | RES01 | — | — | Finalizar (ya confirmada) | ✅ `FINALIZADA` |
| 16 | RES01 | — | — | Confirmar de nuevo | ❌ finalizada no vuelve a confirmada |

---

## 7. Consultas y cierre finales

| Acción | Resultado esperado |
|---|---|
| Consultar Valor Total de `RES02` | `$56.000` (18000×2 + 20000) |
| Consultar Reservas Confirmadas por Cliente de `C002` | Lista `RES02` |
| Eliminar Cliente `C001` | ✅ eliminado (demo de borrado) |
| Eliminar Operador `OP002` | ✅ eliminado (demo de borrado) |

---

**Regla clave a narrar en el video:** un equipo solo bloquea a otra reserva cuando la que lo
contiene está **CONFIRMADA** (filas 1–4 de la tabla de Reservas la demuestran: dos reservas
`SOLICITADA` pueden compartir equipo/fecha/turno, pero al confirmar la segunda, falla).
