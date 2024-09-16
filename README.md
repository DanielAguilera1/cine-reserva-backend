# Sistema de Venta de Tiquetes de Cine

Este proyecto es una aplicación backend desarrollada en **Spring Boot** para la gestión y venta de tiquetes de cine, integrando bases de datos **MySQL** para la gestión de películas y salas, y **MongoDB** para el manejo de las funciones de las salas.

## Tabla de Contenidos

1. [Descripción](#descripción)
2. [Arquitectura del Proyecto](#arquitectura-del-proyecto)
3. [Tecnologías Utilizadas](#tecnologías-utilizadas)
4. [Instalación y Configuración](#instalación-y-configuración)
5. [Ejemplos de Uso](#ejemplos-de-uso)
6. [Estructura de la Base de Datos](#estructura-de-la-base-de-datos)
7. [Contribuciones](#contribuciones)
8. [Licencia](#licencia)

## Descripción

El sistema de venta de tiquetes de cine permite gestionar las funciones de cine, salas, películas y la disponibilidad de asientos en cada función. El proyecto maneja:

- **Películas** (almacenadas en MySQL).
- **Salas** y **funciones** (gestión de horarios, asientos y disponibilidad en MongoDB).
- **Manejo de tiquetes** para los usuarios, con opciones de métodos de pago definidos (`Paypal`, `Tarjeta`, `En Fisico`).

## Arquitectura del Proyecto

El backend está diseñado en **Spring Boot**, con controladores REST que exponen endpoints para la interacción con la aplicación.

- **MySQL**: Utilizado para almacenar los datos estáticos de las películas y las salas.
- **MongoDB**: Utilizado para almacenar funciones, que incluyen la relación entre las películas, salas y los asientos asignados en cada función.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **MySQL**
- **MongoDB**
- **Maven**
- **Lombok**
- **Spring Data JPA (para MySQL)**
- **Spring Data MongoDB (para MongoDB)**
- **Swagger** (para documentación de la API)
- **Postman** (para pruebas de API)

## Instalación y Configuración

### Prerrequisitos

- JDK 17+
- Maven 3.x
- MySQL 8.x o superior
- MongoDB 5.x o superior
