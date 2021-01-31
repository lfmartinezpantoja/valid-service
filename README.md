# Valid  valid-service
Servicio para administrar clientes  y procesarlos.


## Documentación:
Además se está documentación  el servicio posee una documentación usando swagger que permite visualizar cada uno se los endpoints que expone el servicio, para ello es necesario correr el servicio en local  y pegar la url en el navegador de su preferencia:
	* http://localhost:8080/swagger-ui.html
## Construido con:

* 	[Maven] - Administracion de dependencias
* 	[JDK] - Java™ Platform, Standard  Development Kit
Spring Applications
* 	[Lombok](https://projectlombok.org/) - Para generar automatocamente metodos getter, setter y construtores para  	entidades y DTOs.



## Herramientas de prueba:
*   Pruebas de integracion con JUnit y MockMvc en el directorio: src/test/java en el package: controller, clase: ClientControllerIntegrationTest.class, el cual contiene pruebas de integración para creación de cliente, obtener todos los clientes, procesar clientes de manera exitoso y procesar un cliente fallido para provocar una excepción controlada, estas pueden ser ejecutadas de la siguiente forma: Click derecho en la clase -> Run as -> Junit Test.
* 	[Postman](https://www.getpostman.com/) - API Development Environment (La colleccion se postman se encuentra en la carpeta fuente resources del proyecto (src/main/resources))



## Correr el proyecto localmente:
Hay varias maneres de correr el proyecto en local. Una de las maneras es ejecutar el metodo `main`  en la clase `com.valid.clients.ValidServiceApplication` desde el IDE de su preferencia.

Para correr el proyecto se procede a realizar los siguientes pasos:
* 	Descargar el repositorio de GitHub o clonarlo.
* 	Open Eclipse o el IDE de su preferencia.
	* File -> Import -> Existing Maven Project -> Navigate to the folder.
	* Select the project
* 	Escoger la clase Spring Boot Application (Buscar por  @SpringBootApplication)
* 	click derecho en la clase y  Run as Java Application.

Alternativamente usted puede usar el Spring boot Maven plugin, ejecutando:

```shell
mvn spring-boot:run
```

## Arquitectura, patroneres de diseño y directorios

El servicio está orientado a trabajar bajo la arquitectura de miscroservicios, es decir la única funcionalidad que debería exponer este debería ser la administración de clientes, además debido a que el requerimiento actual está acotado a unas determinadas funciones el servicio de encuentra abierto para extensión (pricipio open-close), el servicio presenta una arquitectura interna por capas bien definida y tiene la estrcuctura de paquetes que se muestra a continuación:

```text
.
├── Spring Elements
├── src
│   └── main
│       └── java
│           ├── com.valid.clients
│           ├── com.valid.clients.config
│           ├── com.valid.clients.controller
|           ├── com.valid.clients.dto
│           ├── com.valid.clients.exception
│           ├── com.valid.clients.error
│           ├── com.valid.clients.model
│           ├── com.valid.clients.repository
│           └── com.valid.clients.service
│           └── com.valid.clients.service.imp
├── src
│   └── main
│       └── resources
│           ├── application.properties
│           ├── data.sql
│           ├── VALID.postman_collection.json
├── src
│   └── test
│       └── java
|           ├── com.valid.clients.controller
├── JRE System Library
├── Maven Dependencies
├── bin
├── src
├── target
│   └──valid-service:0.0.1-SNAPSHOT
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

Cada paquete tiene asociada unas clases que tiene una funcionalida bien definida, lo cual cumple con el principio de única responsabilidad (única razón para cambiar), en seguida se describe la funcionalidad o patrón de diseño de cada uno de los paquetes o clases asociadas

## packages

* 	`models` — Es donde se encuentran las entidades de domino, en esta caso tienen las anotaciones que permiten mapear la 	   tabla asociada en base de datos, con lo cual nos permite usar JPA como DAO para la capa de persitencia;
* 	`repositories` — Implementan el patron DAO, es decir la capa de acceso a datos, para este caso se usa JPA como ORM;
* 	`services y su implementacion` — Se encuentran las clases que implementan la lógica de la aplicacion, también tienen 	  una interface que implementa el patrón Fachada (Facade) con lo cual no se depende de implementaciones si no de  			 abstracciones;
* 	`controllers` — Es la capa REST del nuestro servicio y nos permite recibir las peticiones HTTP y  transferir 	  las 	   información recibida desde la capa REST a la capa de servicio donde se procesa la información;
*    `dtos` - Sus clases implementan el patrón DTO, el cual representa un objeto plano cuya finalidad es ser usado en la 	   capa de presentación y transefir la información a la capa de servicio;
*   `error` - Contiene un Enum que permite mantener centralizado todos los mensajes de error del servicio.
*   `exception` - contiene  una clase que maneja de manera global las exepciones producidas por el servico y además      	  una clase custom para generar expeciones de manera controlada.
*    `config` - contiene clases de configuración del proyecto, para este caso algunos beans utilitarios.
* 	`pom.xml` - Contiene todas las dependencias del proyecto
