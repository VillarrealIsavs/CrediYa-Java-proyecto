💳 CrediYa S.A.S. – Sistema de Gestión de Préstamos y Cobros

📌 Descripción del proyecto

CrediYa S.A.S. es un sistema de gestión desarrollado en Java que permite administrar los procesos de préstamos y cobros de una empresa dedicada a otorgar créditos personales.
El proyecto surge de la necesidad de reemplazar el manejo de información mediante hojas de cálculo por un sistema organizado que facilite el registro, consulta y seguimiento de empleados, clientes, préstamos y pagos.
La aplicación funciona mediante una interfaz de consola y utiliza archivos de texto y una base de datos MySQL para almacenar y consultar la información.

🎯 Objetivo general

Desarrollar un sistema modular en Java que permita gestionar préstamos y cobros de cartera, aplicando los principios de la programación orientada a objetos, el manejo de colecciones, la persistencia de información y la conexión a bases de datos mediante JDBC.

⚙️ Funcionalidades
El sistema cuenta con los siguientes módulos:

👥 1. Gestión de empleados

Registrar nuevos empleados.
Consultar empleados registrados.
Administrar información personal y laboral.
Guardar la información en archivos y base de datos.
Datos: ID, nombre, documento, rol, correo y salario.

🧑 2. Gestión de clientes

Registrar nuevos clientes.
Listar los clientes registrados.
Consultar los préstamos asociados a cada cliente.
Datos: ID, nombre, documento, correo y teléfono.

💰 3. Gestión de préstamos

Crear préstamos asociando un cliente y un empleado.
Calcular el monto total incluyendo intereses.
Calcular el valor de las cuotas mensuales.
Consultar los préstamos registrados.
Actualizar el estado del préstamo (pendiente o pagado).

💵 4. Gestión de pagos

Registrar abonos a préstamos.
Actualizar el saldo pendiente.
Consultar el historial de pagos.
Asociar cada pago con su respectivo préstamo.

📊 5. Módulo de reportes

Consultar préstamos activos.
Identificar préstamos vencidos.

Consultar clientes morosos.

Filtrar y organizar información mediante Lambda y Stream API.
