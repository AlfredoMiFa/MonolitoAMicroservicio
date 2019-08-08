# MonolitoAMicroservicio
Practica 8 de descomponer un monolito
1.- Abrir la terminal de su computadora y situarse en la carpeta que quiera bajar el proyecto.
2.- Clonar el siguiente repositorio MonolitoAMicroservicio, ejecutando la siguiente sentencia: git clone https://github.com/AlfredoMiFa/MonolitoAMicroservicio.git
3.- Abrir el IDE Spring Tools Suite, ir a file->import->maven project, buscar la dirección donde bajamos el proyecto. Repetir el mismo paso para el segundo proyecto.
4.- Abrir la clase Application de ambos proyectos, click derecho, click izquierdo run as spring boot app, realizar mismo paso para los dos proyectos.
5.- Abrir el navegador de su preferencia(Chrome, firefox, safari, etc).
6.- En una nueva pestaña poner la siguiente url para poder visualizar que tenemos nuestro proyecto ejecutándose: http://localhost:9093/acme-hr-employee/employee/list/1,
7.- Deberá ver algo como esto:  {"id":1,"firstName":"Ivan","lastName":"Garcia","employeeNumber":"00001","workstation":{"id":1,"vendor":"Mac","model":"Mac Book Pro 13 retina","facilitiesSerialNumber":"00101","employeeId":1}}
8.- Para poder agregar más workstation, poner la siguiente URL en el PostMan: http://localhost:9094/acme-hr-workstation/v1/workstations selección en el tipo de servicio en POST y poner en el cuerpo del mensaje algo como esto: 
{
    "id": 4,
    "vendor": "Asus",
    "model": "2019",
    "facilitiesSerialNumber": "00104",
    "employeeId": 1
}
9.- Para poder visualizar que se agrego la nueva workstation ir a la siguiente URL: http://localhost:9094/acme-hr-workstation/v1/workstations listará todas las workstation existentes.
10.- Para poder eliminar una workstation ejecutar la siguiente url en el postman: http://localhost:9094/acme-hr-workstation/v1/workstations/{id} (eliminar llaves y poner el id de la workstation que se quiere dar de baja.
11.- Para poder actualizar una workstation deberá ejecutar la siguiente URL http://localhost:9094/acme-hr-workstation/v1/workstations/{id} (quitar las llaves y el id y poner el id de la workstation que se desea actualizar).
12.- Para poder realizar el listado, baja, alta y actualización de emplomes o workstation se anexan las url para poder realizar dichas acciones.
Employees URL’s
http://localhost:9093/acme-hr-employee/employee/list  —> listar todos
http://localhost:9093/acme-hr-employee/employee/list{id} —> listar uno en especifico
http://localhost:9093/acme-hr-employee/employee/list -> dar de alta un nuevo employee
http://localhost:9093/acme-hr-employee/employee/list{id} -> actualizar employee
http://localhost:9093/acme-hr-employee/employee/list{id} -> eliminar employee
Workstation URL’s
http://localhost:9094/acme-hr-workstation/v1/workstations -> lista todas
http://localhost:9094/acme-hr-workstation/v1/workstations{id} -> listar una en especifico
http://localhost:9094/acme-hr-workstation/v1/workstations -> dar de alta nueva workstation
http://localhost:9094/acme-hr-workstation/v1/workstations{id} -> eliminar workstation
http://localhost:9094/acme-hr-workstation/v1/workstations{id} -> actualizar workstation

Colecciones postman: https://www.getpostman.com/collections/2218632e6055ec48bf4d
