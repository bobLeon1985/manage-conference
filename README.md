## Sobre arquitectura hexagonal


## Iniciando

Siga las siguientes instrucciones para iniciar el desarrollo de este proyecto.

### Pre-Requisitos

Plugins que deben estar instalados en su IDE:

* [Lombok](http://projectlombok.org/) - *Libreria de Bytecode que genera automaticamente los Getters y Setters*.
* [CheckStyle](http://www.checkstyle.com/) - *Plugin para poder comprobar el estilo del codigo usando las reglas de
  Google*
* FindBugs - *Plugin que realiza un análisis estático para buscar errores en el código en base a patrones de errores.*

---
Instalar JCE (Java Cryptography Extension)

* [JCE](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html)

### Estructura de paquetes

Para el manejo de la estructura de de los paquetes se tomará como
referencia [Domain driven design](https://es.wikipedia.org/wiki/Dise%C3%B1o_guiado_por_el_dominio), en donde tendremos
de ejemplo la siguiente estructura:

#### Estructura del proyecto

~~~
* src
  * main
    * java
      * com.manager.<domain>.<artifac>
        * application
        * domain                           
        * infrastructure
          * exception
          * input.adapter.rest
          * input.adapter.rest.bean
          * input.adapter.rest.config
          * input.adapter.rest.impl
          * input.adapter.rest.mapper
          * output.adapter
          * output.repository
          * output.repository.entity
          * output.repository.mapper
    * resources
      - application.yml
  * test
- azure-pipelines.yml
- .gitignore
- README.md
- pom.xml
- nombre-proyecto.yml (documentación API)
- settings.xml
- Dockerfile


~~~

## Flujo de desarrollo.

* Todo desarrollo debe iniciarse en una rama con la nomenclatura `feature/nombre-de-cambio` el cual debe crearse desde
  la rama `develop`.

* Cuando se completa el desarrollo, se deberá generar un `New Merge Request` desde la rama
  creada `feature/nombre-de-cambio` hacia la rama `develop`.

* Cuando los cambios son revisados y probados, se aceptará el Merge Request, con lo que los cambios quedarán listos en
  la rama `develop` para realizar el despliegue en el ambiente de desarrollo.

## Generacion de codigo

Para el caso de una arquitectura web, para poder aprovechar el codigo generado a partir del contrato se debe ejecutar lo
siguiente:

```
gradle clean generate-sources
```

## Ejecución de pruebas

Para la ejecución de pruebas `unitarias` se debe ejecutar lo siguiente:

```
gradle test
```

Para la ejecución de pruebas de `integración` se debe ejecutar lo siguiente:

```
gradle verify -Dskip.integration.tests=false -Dskip.unit.tests=true
```

# Conference Management

## Flujo de desarrollo
```
You are planning a big programming conference and have received many proposals which have passed the initial screen process but you're having trouble fitting them into the time constraints of the day -- there are so many possibilities! So you write a program to do it for you.
The conference has multiple tracks each of which has a morning and afternoon session.
Each session contains multiple talks.
Morning sessions begin at 9am and must finish before 12 noon, for lunch.
Afternoon sessions begin at 1pm and must finish in time for the networking event.
The networking event can start no earlier than 4:00 and no later than 5:00.
No talk title has numbers in it.
All talk lengths are either in minutes (not hours) or lightning (5 minutes).
Presenters will be very punctual; there needs to be no gap between sessions.

Note that depending on how you choose to complete this problem, your solution may give a different ordering or combination of talks into tracks. This is acceptable; you don’t need to exactly duplicate the sample output given here.

Test input:
Writing Fast Tests Against Enterprise Rails 60min
Overdoing it in Python 45min
Lua for the Masses 30min
Ruby Errors from Mismatched Gem Versions 45min
Common Ruby Errors 45min
Rails for Python Developers lightning
Communicating Over Distance 60min
Accounting-Driven Development 45min
Woah 30min
Sit Down and Write 30min
Pair Programming vs Noise 45min
Rails Magic 60min
Ruby on Rails: Why We Should Move On 60min
Clojure Ate Scala (on my project) 45min
Programming in the Boondocks of Seattle 30min
Ruby vs. Clojure for Back-End Development 30min
Ruby on Rails Legacy App Maintenance 60min
A World Without HackerNews 30min
User Interface CSS in Rails Apps 30min

Test output:
Track 1:
09:00AM Writing Fast Tests Against Enterprise Rails 60min
10:00AM Overdoing it in Python 45min
10:45AM Lua for the Masses 30min
11:15AM Ruby Errors from Mismatched Gem Versions 45min
12:00PM Lunch
01:00PM Ruby on Rails: Why We Should Move On 60min
02:00PM Common Ruby Errors 45min
02:45PM Pair Programming vs Noise 45min
03:30PM Programming in the Boondocks of Seattle 30min
04:00PM Ruby vs. Clojure for Back-End Development 30min
04:30PM User Interface CSS in Rails Apps 30min
05:00PM Networking Event

Track 2:
09:00AM Communicating Over Distance 60min
10:00AM Rails Magic 60min
11:00AM Woah 30min
11:30AM Sit Down and Write 30min
12:00PM Lunch
01:00PM Accounting-Driven Development 45min
01:45PM Clojure Ate Scala (on my project) 45min
02:30PM A World Without HackerNews 30min
03:00PM Ruby on Rails Legacy App Maintenance 60min
04:00PM Rails for Python Developers lightning
05:00PM Networking Event
```
