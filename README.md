# Sistema de Venta de Tiquetes de Cine

Este proyecto es una aplicación backend desarrollada en **Spring Boot** para la gestión y venta de tiquetes de cine, integrando bases de datos **MySQL** para la gestión de películas y salas, y **MongoDB** para el manejo de las funciones de las salas.

## Tabla de Contenidos

1. [Descripción](#descripción)
2. [Arquitectura del Proyecto](#arquitectura-del-proyecto)
3. [Tecnologías Utilizadas](#tecnologías-utilizadas)
4. [Instalación y Configuración](#instalación-y-configuración)
5. [Ejemplos de Uso](#ejemplos-de-uso)

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

- **Java 20**
- **Spring Boot 3.3.3**
- **MySQL**
- **MongoDB**
- **Maven**
- **Lombok**
- **Spring Data JPA (para MySQL)**
- **Spring Data MongoDB (para MongoDB)**
- **Postman** (para pruebas de API)

## Instalación y Configuración

#### **INSTALACION : **
Ejecutar con CMD en el proyecto el siguiente comando para descargar todas las dependencias
```
mvnw install
```

####**CONFIGURACIÓN : **
Si queremos usar este repositorio una vez clonado y no pasar tu infrormacion de tu base de datos por si haces pruebas desde tu maquina o en otro host crea un archivo .properties
**application-dev.properties** recomiendo llamarlo asi ya que asi fue como lo configure en el gitIgnore para evitar pasar informacion al hacer push al repositorio una vez creado cambiar los siguientes valores que dice:
**PUERTO, {NOMBRE_EJEMPLO}, USERNAME, CONTRASEÑA, NOMBRE_EJEMPLO**
```
# Mysql
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:PUERTO/{NOMBRE_EJEMPLO}createDatabaseIfNotExist=true
spring.datasource.username=USERNAME
spring.datasource.password=CONTRASEÑA
spring.jpa.hibernate.ddl-auto=update
# MongoDB
spring.data.mongodb.uri=mongodb://localhost:PUERTO/NOMBRE_EJEMPLO
spring.data.mongodb.database=NOMBRE_EJEMPLO
```

### Prerrequisitos

- JDK 20
- Maven 3.x
- MySQL 8.x o superior
- MongoDB 5.x o superior
