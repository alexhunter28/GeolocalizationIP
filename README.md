# Appgate GeolocalizationIP
[![Java](https://img.shields.io/badge/Java_17-red)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) 
[![Spring boot](https://img.shields.io/badge/Spring%20boot-3.2.3-green)](https://spring.io/)
[![H2 embedded DB](https://img.shields.io/badge/H2-DB-blue)](https://spring.io/) 

## Desarollado por    

[Alexander Martinez](https://www.linkedin.com/in/ing-h-alexander-martinez-m/)

## Preguntas

**¿Cuál sería para usted una forma óptima de leer los datos en el archivo de entrada
para su almacenamiento?**

Este proyecto utiliza un BufferedReader para leer el archivo línea por línea de manera eficiente, se crea un BufferedReader que lee directamente del archivo, lo que evita cargar todo el contenido en la memoria. Esto es especialmente útil cuando trabajas con archivos muy grandes, ya que minimiza el uso de memoria y mejora la eficiencia de la lectura.

![image](https://github.com/alexhunter28/GeolocalizationIP/assets/36106982/20e26511-d59a-4235-8ce4-bff9b40f7b54)

* el codigo compelto lo puedes encontarar [aqui](https://github.com/alexhunter28/GeolocalizationIP/blob/main/src/main/java/com/appgate/geolocalizationip/service/FileIPReaderService.java)



**¿Cuál sería el diseño de los objetos en la capa de persistencia?**

El modelo de persistencia para esta solucion es sencillo tiene 2 entidades la primera es la entidad Location

![image](https://github.com/alexhunter28/GeolocalizationIP/assets/36106982/0fa1f7f2-d13a-4749-8d10-3cbccd007700)

La segunda entidad es IPAdress

![image](https://github.com/alexhunter28/GeolocalizationIP/assets/36106982/21942b35-7649-4e8c-bba2-2265171b53a4)

Estas entidades fueron creadas de acuerdo a los datos que fueron provistos por la prueba y su relacion se basa en que una localizacion puede tener muchas direcciones IP. El colido completo esta [Aqui](https://github.com/alexhunter28/GeolocalizationIP/tree/main/src/main/java/com/appgate/geolocalizationip/entity)



**¿Cuál sería para usted una forma óptima de almacenar los datos leídos en la capa
de persistencia?**

Esta solucion implementa un forma de persistencia de una sola transaccion usando los metodo de la interface JPARepository SaveAllAndFlush, ademas que que se ha activado el segundo nivel de chache de hibernate



¿Cuál sería para usted la forma correcta de exponer la operación de consulta de
datos para una IP?
