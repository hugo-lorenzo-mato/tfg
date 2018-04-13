# Docker-compose

## Script borrado imágenes y contenedores

* `sudo sh removeCOMPLETO.sh`: elimina todas las imágenes y contenedores.
* `sudo sh removeNObasicas.sh`: NO elimina las imágenes iniciales como tomcat y postgres.

## Lanzar Docker

* `docker-compose up --build`
* `docker-compose stop`



# Plataforma base

## Prerequisitos

* Java 8
* PostgreSQL << Hugo 03/08/17 Funcionando con versión 9.5.7>> 
* Maven 3 << Hugo 03/08/17 Funcionando con versión 3.5>> 
* Gradle << Hugo 03/08/17 Funcionando con versión 4.0.2>> 
* Node v4.3 en adelante. << Hugo 03/08/17 Funcionando con versión 6.11.1. 
                         Con la última disponible a la fecha, 8.x, arrojaba 
			 errores.>>

## Configuración

* Asignar JDK 1.8 en Module Settings.
	<< Hugo 03/08/17 Importando el proyecto Gradle en Intellij, ciertos archivos
	 de configuración de Spring no son mapeados. La solución pasa por ir a File -> 
	 Project Structure -> Botón verde de más sobre "Application" y añadir las 5 
	 clases de GGAL1701_main -> Aplicar cambios. Quizá se desconfigure el JDK, volverlo
	 a asignar y todo funcionando correctamente referente a este apartado >>
* Crear la base de datos y configurar la conexión en el archivo `application-x.yml`.


### Server

* `gradlew bootRun -Pdev` despliegua la aplicación para desarrollo. También se puede ejecutar el fichero `build.gradle` directamente.
	
	<< Hugo 03/08/17 Con la opción de correr comando funciona correctamente, 
	ejecutando el fichero build.gradle no; revisar para debuggear>> 

* Para debuggear en IntelliJ/Eclipse ejecutar la clase `Application.java` en modo debug (**recomendado**)
    * Añadir `-Dspring.profiles.active=dev` a `JAVA_OPTS` o a la configuración de la ejecución (`VM options`).  

### Client

* `npm install` instala todas las dependencias necesarias.

	<< Hugo 03/08/17 Da opciones a seleccionar para la instalación. En mi caso escojo angular 1.6.5 >> 

* `npm run serve` despliegua la aplicación para desarrollo.
    * ¡Ojo! Lanzar siempre en [`http://localhost:9000`](http://localhost:9000).


* Para añadir nuevas dependencias de Bower se puede añadir a `bower.json` y luego hacer un `npm install` o directamente hacer `bower install <nombre> -S`.

### Probar en Tomcat

* Adaptar el archivo `lanzar_tomcat.bat` que esta en la raíz del proyecto con las rutas al WAR generado y a la carpeta de Tomcat.
* Ejecutar.

### JavaMelody

* La configuración se encuentra en el archivo `JavaMelodyConfiguration.java`. En él se indican el usuario, la contraseña y la URL de acceso ([`http://localhost:8080/management/monitoring`]()).

## Despliegue

* `npm run build` compila la aplicación para producción en la carpeta `dist`.
* `gradlew war` genera el war de la aplicación para producción en la carpeta `build/libs`.
* Para sistemas:
    * Instalación:
        * Utilizar Java 8
        * La conexión a la base de datos se encuentra en el archivo `/WEB-INF/classes/config/application-prod.yml` y los logs en `/WEB-INF/classes/logback-spring.xml`.
    * Para actualizaciones:
        * Desplegar el nuevo war manteniendo la carpeta `/upload` y los archivos: `/WEB-INF/classes/config/application-prod.yml`, `/WEB-INF/classes/logback-spring.xml`.
