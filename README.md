# FootballApi

API RESTful desarrollada en Java para la gestión completa de datos futbolísticos, similar a los sistemas utilizados por organizaciones como la FIFA.

## Descripción

FootballApi es un backend robusto y escalable que proporciona una plataforma completa para la gestión de entidades del mundo del fútbol. Esta API permite administrar asociaciones de fútbol, clubes, competiciones, jugadores y entrenadores a través de endpoints RESTful.

La API está diseñada para manejar las complejas relaciones jerárquicas del mundo del fútbol: desde las asociaciones de alto nivel hasta los jugadores individuales, permitiendo consultas detalladas y actualizaciones eficientes de la información.

## Características Técnicas

- **Java con Spring Boot**: Implementado utilizando la última versión de Spring Boot para el desarrollo rápido y eficiente de aplicaciones
- **Hibernate/JPA**: Utiliza Hibernate como proveedor de JPA para la persistencia de datos y mapeo objeto-relacional
- **MySQL**: Base de datos relacional para almacenar toda la información futbolística
- **Patrón Builder**: Implementado para la creación de objetos complejos, especialmente en entidades con múltiples atributos
- **Relaciones entre Entidades**: Modelado completo de relaciones OneToMany, ManyToOne y ManyToMany entre las entidades del sistema
- **Proyecciones JPA**: Implementadas para optimizar las consultas y devolver solo los datos necesarios
- **SQL Queries Nativas**: Consultas SQL personalizadas para operaciones complejas y reportes especializados
- **Paginación**: Implementación de paginación para manejar grandes volúmenes de datos de manera eficiente
- **Patrón DTO**: Separación clara entre entidades de dominio y objetos de transferencia de datos
- **Mappers Personalizados**: Conversión eficiente entre entidades y DTOs utilizando mappers personalizados
- **Manejo de Excepciones**: Sistema robusto de manejo de excepciones con respuestas HTTP apropiadas
- **Maven**: Gestión de dependencias
- **Lombok**: Reducción de código

Esta API está diseñada como un sistema backend puro, enfocado en proporcionar servicios RESTful para ser consumidos por aplicaciones frontend o móviles.
