# TP_DAOO
Resumen del Proyecto:

El objetivo de este proyecto es diseñar e implementar un sistema de gestión de nóminas utilizando los principios de la programación orientada a objetos.
El sistema deberá permitir la gestión de datos de empleados, el cálculo de salarios y la generación de informes de nómina.
Los estudiantes aprenderán sobre diseño orientado a objetos, encapsulamiento, herencia y manipulación de datos mientras implementan el sistema de nóminas.

Tareas del Proyecto:
1. Diseño:
    * Los empleados cuentan con atributos comunes como por ejemplo (por ejemplo, ID, nombre, detalles de contacto).
    * Implementar diferentes tipos de empleados o contratos (por ejemplo, tiempo completo, medio tiempo, contrato) con atributos específicos únicos para cada tipo.
    * Considerar el uso de herencia, delegación y estructuras de datos apropiadas para gestionar eficientemente los datos de los empleados.
2. Implementar el Cálculo de Nóminas:
    * Desarrollar un módulo encargado de calcular los salarios basado en los datos de los empleados y las reglas de salario predefinidas.
    * Incluir funcionalidad para manejar diferentes frecuencias de pago (por ejemplo, mensual, quincenal).
    * Considerar factores como las horas trabajadas, horas extras, deducciones e impuestos en el proceso de cálculo de salarios.
    * Debe ser posible navegar en el tiempo para ver los cambios salariales y contractuales.
    * Permitir hacer undo y redo.
3. Gestión de Empleados:
    * Diseñar un módulo para gestionar los datos de los empleados, incluyendo características para agregar nuevos empleados, actualizar registros existentes y recuperar información de los empleados.
    * Incluir comprobaciones de validación para garantizar la integridad de los datos y manejar cualquier regla de negocio relevante (por ejemplo, elegibilidad de empleo).
    * Debe ser posible navegar en el tiempo para ver los cambios.
    * Permitir hacer undo y redo.
4. Generar Informes de Nómina:
    * Implementar funcionalidad para generar informes de nómina para períodos de tiempo específicos, como mensuales o anuales.
    * Incluir características para mostrar detalles salariales de los empleados, deducciones fiscales y otra información relevante.
    * Considerar proporcionar opciones para exportar informes en diferentes formatos (por ejemplo, PDF, CSV).
5. Interfaz de Usuario:
    * Crear una interfaz fácil de usar para interactuar con el sistema de gestión de nóminas.
    * Utilizar mecanismos de entrada apropiados (por ejemplo, comandos en línea, interfaz gráfica) para que los usuarios ingresen datos de empleados, realicen cálculos y generen informes.
    * Mostrar menús claros, indicaciones e información relevante para guiar a los usuarios a través del sistema.
6. Pruebas y Documentación:
    * Escribir casos de prueba exhaustivos para garantizar la precisión de los cálculos, la gestión de datos y la generación de informes.
    * Documentar la funcionalidad del sistema, incluyendo descripciones de las clases, explicaciones de métodos y cualquier suposición o limitación.
    * Proporcionar documentación de usuario que explique cómo utilizar el sistema y sus diferentes características.

Contrato puede ser por cantidad de horas totales, cantidad de hs por mes, cantidad de horas por semana. Siempre el contrato tiene una fecha de comienzo y fin

El calculo de nóminas se hace por un periodo dado sobre todos los empleados