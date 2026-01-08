# Sitio Web de Cocina "Recetas de la Abuela"

Proyecto final de la Tecnicatura Universitaria en Programación (UTN).
Consiste en un sitio web de cocina que permite visualizar recetas y simular la compra de productos gastronómicos, conectado a una API REST desarrollada en Java con Spring Boot.
El proyecto integra frontend y backend, trabajando con persistencia en base de datos.

## Tecnologías utilizadas

Backend:
- Java 17
- Spring Boot
- Maven
- JPA / Hibernate
- MySQL

Frontend:
- HTML5
- CSS3
- Javascript
- Bootstrap

## Funcionalidades principales

- Visualización de recetas almacenadas en la base de datos, con ingredientes, pasos, tiempo de preparación e imágenes ilustrativas.
- Tienda de productos gastronómicos obtenidos desde el backend mediante solicitudes REST.
- Buscador por nombre tanto en recetas como en productos.
- Simulación de compra a través de un carrito interactivo:
- Registro de usuarios mediante una sección de Suscripción.
- Registro de ventas asociadas a suscriptores.
- Navegación fluida entre las distintas secciones del sitio.
- Diseño visual intuitivo, atractivo y responsivo, utilizando el sistema de grillas de Bootstrap.
- Persistencia de datos en base de datos MySQL.
- Uso de arquitectura multicapa.
- Uso de DTOs para optimizar la comunicación frontend-backend, ocultar datos sensibles y centralizar la lógica de negocio.

## Funcionalidades del backend (API)

- CRUD completo de recetas, productos, suscriptores y ventas.
- Métodos adicionales de lógica de negocio, como:
      - Control y actualización de stock.
      - Cálculo de totales y subtotales.
      - Obtención de métricas de ventas.
- Endpoints REST invocados desde JavaScript en el frontend.
- Carga inicial de datos realizada mediante Postman, simulando acciones de un administrador del sistema.

En el repositorio se incluye la colección de Postman utilizada para la carga de recetas y productos.

