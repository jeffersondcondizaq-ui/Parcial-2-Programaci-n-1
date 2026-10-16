# Datos de Prueba — PixelRent

Datos listos para cargar manualmente en la aplicación (vía los menús de `Main.java`) y verificar
cada CRUD y cada validación de reservas.

> La empresa ya se crea automáticamente al iniciar el programa:
> `Empresa("900123456-7", "PixelRent", "Calle 10 # 20-30")`

---

## 1. Sedes

| Código | Nombre              | Dirección                  |
|--------|---------------------|-----------------------------|
| SED01  | Sede Norte          | Cra 15 # 100-20             |
| SED02  | Sede Centro         | Calle 45 # 10-30            |
| SED03  | Sede Sur            | Av. Boyacá # 5-60           |

**Pruebas sugeridas:** registrar las 3, mostrar listado, buscar `SED02`, actualizar `SED03`
(nombre/dirección), eliminar `SED03` y confirmar que ya no aparece en el listado.

---

## 2. Equipos

### Cámaras (registrar en `SED01`)

| Código | Marca   | Modelo    | Resolución máxima | ¿Estabilizador? |
|--------|---------|-----------|--------------------|------------------|
| CAM01  | Sony    | A7III     | 4K                 | Sí               |
| CAM02  | Canon   | R6        | 4K                 | No               |

### Drones (registrar en `SED01`)

| Código | Marca | Modelo   | Autonomía (min) | ¿Batería adicional? |
|--------|-------|----------|-------------------|-----------------------|
| DRO01  | DJI   | Mavic 3  | 45                | Sí                    |
| DRO02  | DJI   | Mini 4   | 30                | No                    |

### Kits de iluminación (registrar en `SED02`)

| Código | Marca    | Modelo | Cantidad de luces | ¿Control inalámbrico? |
|--------|----------|--------|---------------------|--------------------------|
| KIT01  | Godox    | SL60   | 4                   | Sí                       |
| KIT02  | Aputure  | 300d   | 2                   | No                       |

**Costos esperados (según tarifas fijas del modelo):**
- Cámara: `12000 × horas` (+15000 si tiene estabilizador)
- Dron: `18000 × horas` (+20000 si tiene batería adicional)
- Kit iluminación: `10000 × horas` (+12000 si tiene control inalámbrico)

**Pruebas sugeridas:** registrar cada equipo en la sede indicada, listar por tipo, buscar por
código, actualizar `CAM02` (marca/modelo/resolución/estabilizador), eliminar `KIT02`.

---

## 3. Clientes

| Identificación | Nombre completo     | Teléfono    | Correo                     |
|-----------------|----------------------|-------------|------------------------------|
| C001            | Laura Gómez          | 3001112233  | laura.gomez@mail.com        |
| C002            | Andrés Pérez         | 3012223344  | andres.perez@mail.com       |
| C003            | María Torres         | 3023334455  | maria.torres@mail.com       |

**Pruebas sugeridas:** registrar los 3, mostrar listado, buscar `C002` por ID, eliminar `C003`.
Confirmar que **no existe** opción de actualizar datos de un cliente (requisito: inmutable tras
el registro).

---

## 4. Operadores

| Identificación | Nombre completo | Certificación      | Años de experiencia |
|-----------------|-------------------|---------------------|------------------------|
| OP001           | Carlos Ruiz       | Piloto Dron Avanzado| 5                      |
| OP002           | Sofía Herrera     | Piloto Dron Básico  | 2                      |

**Pruebas sugeridas:** registrar los 2, mostrar listado, buscar `OP001`, actualizar certificación
y años de `OP002`, eliminar `OP002` y verificar que ya no aparece.

---

## 5. Reservas (casos de prueba de validaciones)

| Código | Cliente | Fecha        | Turno  | Equipos a agregar      | Operador asignado | Resultado esperado al Confirmar |
|--------|---------|--------------|--------|--------------------------|---------------------|-----------------------------------|
| RES01  | C001    | 2026-11-05   | MANANA | CAM01 (3 h)              | —                   | ✅ Se confirma (tiene equipo, sin dron) |
| RES02  | C002    | 2026-11-06   | TARDE  | DRO01 (2 h)               | *(ninguno)*         | ❌ Falla: "contiene un dron sin operador asignado" |
| RES03  | C002    | 2026-11-06   | TARDE  | DRO01 (2 h)               | OP001               | ✅ Se confirma (dron + operador asignado) |
| RES04  | C003    | 2026-11-05   | MANANA | CAM01 (1 h)              | —                   | ❌ Falla al confirmar: `CAM01` ya está ocupada por `RES01` en esa fecha/turno |
| RES05  | C001    | 2026-11-10   | NOCHE  | *(sin equipos)*          | —                   | ❌ Falla: "reserva sin equipos" |
| RES06  | C003    | 2026-11-12   | TARDE  | KIT01 (4 h)              | —                   | Crear, luego **Cancelar**. Verificar que ya no se puede volver a Confirmar |

### Secuencia de prueba recomendada

1. Crear `RES01` → agregar `CAM01` → **Confirmar** → debe quedar `CONFIRMADA`.
2. Crear `RES02` → agregar `DRO01` → **Confirmar** (sin operador) → debe **rechazar**.
3. Sobre `RES02`, asignar operador `OP001` → **Confirmar** de nuevo → ahora debe aceptar
   (equivale al caso `RES03`).
4. Crear `RES04` con el mismo equipo/fecha/turno que `RES01` ya confirmada → **Confirmar** →
   debe **rechazar** por choque de disponibilidad.
5. Crear `RES05` sin agregar equipos → **Confirmar** → debe **rechazar** por falta de equipos.
6. Crear `RES06` → **Cancelar** → intentar **Confirmar** → debe **rechazar** porque una reserva
   cancelada no puede volver a `CONFIRMADA`.

### Costos esperados de ejemplo

- `RES01`: `CAM01` 3 h con estabilizador → `12000×3 + 15000 = 51000`
- `RES03`: `DRO01` 2 h con batería adicional → `18000×2 + 20000 = 56000`
