# SISTEMA DE GESTION DE VENTAS PARA FARMACIA
El presente sistema fue elaborado para cubrir la necesidad de gestionar clientes, productos, ventas, entre otros. utiles para el funcionamiento de registro de ventas.

# TECNOLOGIAS USADAS
1.	JAVA 
2.	JAVA SWING
3.	SQL SERVER
4.	GIT

# PASOS PARA CONFIGURAR
## CONFIGURAR BASE DE DATOS EN SQL SERVER
En la ruta "src\Script" o "Farmacia\src\Script" se ubican 2 archivos con extension sql. Ejecutar en el siguiente orden:  
1."BD_pr_farmacia.sql" - contiene la creacion y tablas de la base de datos. El nombre de la base de datos se encuentra por defecto como "DB_ProyectoFM".  
2."BD_FR_SP.sql" - contiene los store procedures necesarios para el funcionamiento correcto del sistema.

## CONFIGURAR CADENA DE CONEXION
1.En la ruta "src" o "Farmacia\src", se encontrara un archivo de nombre "db.properties".  
2.En caso de no existir, existira otro archivo de nombre "db.properties.example" en la misma ruta, realizar una copia del archivo y editar su nombre eliminando el ".example".  
3.Abrir el archivo con un block de notas y modificar la cadena de conexion: nombre de la instancia del sql o dejarlo en localhost, cambiar nombre de bd en caso hayan modificado a la hora de ejecutar la base de datos, cambiar el usuario y clave del inicio de sesion de su sql server.  
<img width="1463" height="136" alt="image" src="https://github.com/user-attachments/assets/10e316ea-8655-43c7-ad9c-056c237c0300" />
IMPORTANTE: De no existir el archivo "db.properties", el sistema no funcionara debido a que le faltara la cadena de conexion a la base de datos.

## COMPILADO
En la ruta "lib" o "Farmacia\lib", se encontraran 2 archivos .jar:  
1."jcalendar-1.4.jar" -> utilizado para añadir un input de tipo calendario para seleccionar fechas.  
2."mssql-jdbc-13.4.0.jre11.jar" -> utilizado para realizar la conexion jdbc hacia SQL SERVER.  
Si al momento de compilar se genera un error, validar que la referencia a esos 2 archivos exista o en su defecto, regularizarlo en el IDE Netbeans a travez de la carpeta "Libraries".

## INICIO DE SESION
El usuario por defecto es: "Admin"  
La clave por defecto es "1234".  
Los parametros anteriormente mencionados son los necesarios para ingresar al sistema una vez iniciado.


