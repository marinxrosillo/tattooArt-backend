# TattooArt Web App 

TattooArt is a web application designed to manage appointments and users in a tattoo studio. 

## Features

- User Management: Allows administrators to create, edit and delete users, as well as assign roles and permissions.
- Dating Management: Users can book appointments with artists available in the tattoo studio.
- Security: Uses role-based authentication and authorization to protect sensitive application features.

## Technologies

- Spring Boot: Framework de desarrollo de aplicaciones basado en Java.
- Spring Security: Para la autenticación y autorización de usuarios.
- Spring Data JPA: Para el acceso a la base de datos.
- Hibernate: Implementación de JPA para el mapeo objeto-relacional.
- Angular: Framework de desarrollo de aplicaciones web del lado del cliente.
- Bootstrap: Framework de diseño web para la interfaz de usuario.
- Postgres: Sistema de gestión de bases de datos relacional.

## Installation

1. Clone this repository on your local machine.
2. Configure the Postgres database with the docker-compose file provided and update the credentials in the application.properties.
3. Run the Spring Boot application.
4. Navigate to the http://localhost:8080 URL in your browser to access the application.
