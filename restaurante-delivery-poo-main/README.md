# Restaurante Delivery POO

Sistema de gestión de operaciones, órdenes y repartos para un restaurante de
comida rápida con servicio de delivery. Proyecto final del curso de
Programación Orientada a Objetos (2026-1).

## Integrantes

| Nombre | GitHub | Rol / Aporte |
|---|---|---|
| Por definir | exc-dev-pix | Por definir |
| Por definir | Joaquin-cloud06 | Por definir |
| Por definir | Italo-Germana | Por definir |

## Tecnología

- Java (JDK 21)
- Swing (JFrame) para la interfaz gráfica
- IDE: NetBeans (proyecto Ant estándar)
- **Sin base de datos ni frameworks de persistencia**: todo el almacenamiento
  es en memoria durante la ejecución, usando arreglos simples de objetos
  (ej. `Empleado[]`, `Producto[]`) en cada Gestor, en vez de `ArrayList`/
  Collections ni genéricos, tal como exige el enunciado del proyecto y
  manteniendo el código al nivel básico del curso.

## Cómo abrir el proyecto

**NetBeans:** `File > Open Project...` y selecciona esta carpeta. NetBeans
reconoce el proyecto Ant automáticamente (genera `nbproject/build-impl.xml`
la primera vez que se abre o compila). Verifica que la plataforma Java activa
sea JDK 21 (`Tools > Java Platforms`).

**Línea de comandos** (sin NetBeans):
```
javac -d build/classes $(find src -name "*.java")
java -cp build/classes com.restaurante.Main
```

## Diagrama de clases

El diseño oficial del equipo está en `docs/diagrama-clases-v1.jpg`. Todos los
atributos de tipo "categoría"/"estado"/"rol" se modelan como `String` (no se
usan enums), tal como está dibujado en el diagrama.

## Estructura del código

Todas las clases viven directas en `src/com/restaurante/` (un solo paquete
`com.restaurante`, sin subcarpetas adicionales):

```
src/com/restaurante/
  Main.java             Punto de entrada
  Facturable.java       Interfaz: calcularSubtotal(cantidad) -> Producto, Combo, DetallePedido
  Autenticidad.java     Interfaz: login(usuario, contrasena), logout() -> Empleado
  ...                   Empleado, Cliente, Producto, Pedido, Gestores, GUI, etc.
                        (se van agregando aquí mismo en cada fase, con arreglos
                        simples del tipo correspondiente en cada Gestor)
```

## Roadmap del proyecto (por fases)

- [x] **Fase 1** — Estructura del proyecto, interfaces base (`Facturable`, `Autenticidad`)
- [x] **Fase 2** — Empleado (abstracta), Admin, Cajero, Repartidor, Vehiculos (RF01, RF10)
- [x] **Fase 3** — Cliente, Producto, Combo (`Facturable`) (RF02, RF03, RF04)
- [x] **Fase 4** — Pedido, DetallePedido, Incidente, flujo de estados (RF05, RF06, RF07)
- [x] **Fase 5** — Comprobante (abstracta), Boleta, Factura, cálculo de IGV (RF08)
- [x] **Fase 6** — Gestores en memoria: cada Gestor con su propio arreglo simple (sin genéricos ni ArrayList) (persistencia temporal)
- [x] **Fase 7** — Reportes gerenciales (RF09)
- [ ] **Fase 8** — GUI Swing: login y ventanas restringidas por rol (RF10, RF11)
- [ ] **Fase 9** — Pruebas documentadas + informe final
