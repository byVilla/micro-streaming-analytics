# Micro streaming analytics

[![GitHub tag](https://img.shields.io/badge/tag-v1.1.0-green.svg)](https://github.com/byVilla/micro-streaming-analytics/tags/1.1.0)

### Descripción:
Se trata de implementar una pequeña aplicación con Spring Boot que envíe cada segundo datos en formato JSON a una cola RabbitMQ y que a la vez lea cada 10 segundos dichos mensajes y genere información estadística descriptiva sobre los valores encontrados: media, mediana, moda, desviación típica, cuartiles, valor máximo y valor mínimo.
Se generará un mensaje por salida estándar cada vez que se envíe la información a la cola y cada vez que se lea información de la cola que vaya mostrando la actividad del servicio.  Los mensajes JSON de la cola de entrada han de tener el formato definido para la recolección de datos IoT en la API sur Data Collection / Datastream (no DMM) de OpenGate.
La naturaleza del dato simulado recolectado se deja a elección del programador, pero ha de ser numérico.  El resultado del cálculo estadístico se volcará, en formato JSON con una estructura a definir por el programador, a una base de datos MongoDB. El documento JSON deberá tener marca de tiempo y los datos estadísticos definidos anteriormente.

Se deberá utilizar Docker y docker-compose para arrancar toda la solución: aplicación Java micro-streaming-analytics, RabbitMQ, MongoDB.


