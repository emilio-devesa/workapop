# Workapop

## Objetivo
Crear un programa de gestión de los trabajadores donde el usuario puede Insertar, Borrar o listar los empleados registrados y, a mayores, listar también los departamentos existentes en la empresa. Cada empleado tendrá su código de empleado, nombre, puesto de trabajo, código de persona a cargo, fecha de contratación, salario y departamento asignados a su ficha. 

A mayores, los criterios de cuán bien ejecutado está el programa será determinados por: 
- Tener instaladas las bases de datos NetBeans.
- Tener configurado Hibernate.
- La ejecución de las sentencias DML.
- La realización del Join.
- La explicación de la tarea.


## Requisitos
- La creación de las tablas y sus relaciones se dan en el fichero tablas.sql que se adjunta.
- La base de datos que se utilizará será Oracle.
- Las tablas serán mapeadas utilizando Hibernate con NetBeans y realiza un proyecto Java llamado HibernateOracle que obtenga lo siguiente:
1. Crea la base de datos.
2. Configura y crea la ORM Hibernate.
3. Realiza una inserción y un borrado sobre la tabla EMP.
4. Obtener un listado sobre las tablas EMP y DEPT que visualice empno, ename, sal, dname y loc.
5. Redactar un documento donde se explique el proceso seguido para la realización de la práctica.


## Metodología
- Repartición del trabajo.
- Comunicación de todos los pasos.
- Registro de cada avance.
- Colaboración entre las partes.
- Empleo de herramientas para control de versiones (Git) y repositorio remoto (Github)
- Implementación de un primer prototipo y sucesivos refinamientos de código en cada apartado.
- Búsqueda y aprendizaje de cualquier función necesaria para el funcionamiento del proyecto y explicación a los demás integrantes.


## Cronología
### 24/01
- Creación repositorio en Github.
- Creación del servidor de base de datos MySQL en AWS.
- Creación proyecto de desarrollo en IntelliJ.
- Preparación para uso de Hibernate.

### 25/01
- Creadas las tablas.
- Introducidos los datos requeridos en las tablas.

### 26/01
- Añadidas las librerías de hibernate a través de Maven.
- Configuración de hibernate con los datos de acceso a nuestra BBDD.

### 28/01
- División de las clases entre los integrantes del proyecto.
- Creación del código base.
- Creación de las diferentes ramas de trabajo.

### 02/02
- Integración del código

### 09/02
- Refactorización y arreglo de bugs

### 11/02
- Retoques finales para la optimización del programa.
- Finalización del programa. 

### 13/02
- Finalización del README
- Entrega


## Infraestructura
- MySql
- Amazon Web Services
- InteliJ DataGrip
- InteliJ IDEA
- Hibernate ORM
- Maven
- JDK 1.7
- Github

## Funciones
Insertar empleado: Permite ingresar los datos de empleado paso a paso.
Borrar empleado: Borra toda la tupla de un empleado indicado por código de empleado.
Listar empleados: Muestra todos los empleados con sus datos.
Listar Departamentos: Muestra todos los departamentos y sus datos.
Relación Jefe - Empleado: 
	- Mostrar jefe: Muestra el jefe del empleado seleccionado.
	- Mostrar empleados: Muestra los empleados a los que está a cargo.
	- Salir al menú: Sale de Mostrar relación Jefe - Empleado.

## Cambios necesarios
Se ha usado mysql por disponibilidad en Amazon Web Services con cuenta gratuíta.
Se han reducido las requests a Amazon services para evitar cargos mayores.
