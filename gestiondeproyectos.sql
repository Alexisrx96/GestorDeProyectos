DROP DATABASE IF EXISTS gestiondeproyectos;
CREATE DATABASE gestiondeproyectos DEFAULT CHARSET UTF8;
USE gestiondeproyectos;

CREATE TABLE departamento (
    id_departamento char(3) not null,
    nombre_departamento varchar(50) not null,
    primary key (id_departamento)
);

CREATE TABLE rol(
    id_rol int not null auto_increment,
    nombre_rol varchar(25),
    primary key (id_rol)
);

CREATE TABLE estado_empleado(
    id_estado_empleado int not null auto_increment,
    nombre_estado_empleado varchar(25),
    primary key (id_estado_empleado)
);

CREATE TABLE empleado(
    id_empleado int not null auto_increment,
    nombre_empleado varchar(100) not null,
    id_departamento char(3),
    id_jefe int,
    id_rol int not null,
    id_estado_empleado int not null,
    usuario_empleado varchar(25) not null,
    password_empleado varchar(25) not null,
    PRIMARY KEY (id_empleado),
    UNIQUE(usuario_empleado),
    FOREIGN KEY (id_jefe) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_departamento) REFERENCES departamento(id_departamento),
    FOREIGN KEY (id_estado_empleado) REFERENCES estado_empleado(id_estado_empleado),
    FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);

CREATE TABLE estado_solicitud(
    id_estado_solicitud int not null auto_increment,
    nombre_estado_solicitud varchar(22) not null,
    PRIMARY KEY (id_estado_solicitud)
);

CREATE TABLE tipo_solicitud(
    id_tipo_solicitud int not null auto_increment,
    nombre_tipo_solicitud varchar(19) not null,
    primary key (id_tipo_solicitud)
);

CREATE TABLE solicitud(
	id_solicitud int not null auto_increment,
    id_estado_solicitud int not null,
    id_departamento char(3) not null,
    id_tipo_solicitud int not null,
    descripcion_solicitud varchar(2184) not null,
    direccion_pdf_solicitud varchar(2184),
    PRIMARY KEY  (id_solicitud),
    FOREIGN KEY (id_departamento) REFERENCES departamento(id_departamento),
    FOREIGN KEY (id_tipo_solicitud) REFERENCES tipo_solicitud(id_tipo_solicitud),
    FOREIGN KEY (id_estado_solicitud) REFERENCES estado_solicitud(id_estado_solicitud)
);

CREATE TABLE razon_rechazo_solicitud(
	id_solicitud int not null,
    descripcion_razon_rechazo_solicitud varchar(2184) not null,
    PRIMARY KEY  (id_solicitud),
    FOREIGN KEY (id_solicitud) REFERENCES solicitud(id_solicitud)
);

CREATE TABLE estado_caso(
    id_estado_caso int not null auto_increment,
    nombre_estado_caso varchar(41) not null,
    constraint pk_estado_caso primary key (id_estado_caso)
);

CREATE TABLE caso(
	id_solicitud int not null,
	id_caso char(8) not null,
    id_programador int not null,
    id_probador int not null,
    id_estado_caso int not null,
    fecha_inicio date not null,
    fecha_entrega date not null,
    fecha_puesta_produccion date,
    requerimentos_caso varchar(2184) not null,
    PRIMARY KEY (id_caso),
    UNIQUE (id_solicitud),
    FOREIGN KEY (id_estado_caso) REFERENCES estado_caso(id_estado_caso),
    FOREIGN KEY (id_solicitud) REFERENCES solicitud(id_solicitud),
    FOREIGN KEY (id_programador) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_probador) REFERENCES empleado(id_empleado)
);

CREATE TABLE observacion_caso(
    id_observacion_caso int not null auto_increment,
    id_caso char(8) not null,
    fecha_observacion_caso date not null,
    descripcion varchar(2184) not null,
    direccion_pdf_observacion_caso varchar(2184),
    PRIMARY KEY (id_observacion_caso),
    FOREIGN KEY (id_caso) REFERENCES caso(id_caso)
);

CREATE TABLE bitacora(
	id_bitacora bigint not null auto_increment,
    id_caso char(8) not null,
    porcentaje int not null,
    fecha_avance datetime not null,
    descripcion_avance varchar(2184) not null,
    primary key(id_bitacora),
    FOREIGN KEY (id_caso) REFERENCES caso(id_caso)
);

SET GLOBAL event_scheduler = ON;

CREATE EVENT expire_time_event
ON SCHEDULE EVERY 1 day
STARTS CURRENT_TIMESTAMP
ENDS current_date + INTERVAL 1 year
DO
	UPDATE caso SET id_estado_caso = 4 
    where fecha_entrega < curdate() and id_estado_caso between 1 and 2;

INSERT INTO estado_empleado(nombre_estado_empleado) VALUES('Disponible');
INSERT INTO estado_empleado(nombre_estado_empleado) VALUES('Ocupado');
INSERT INTO estado_empleado(nombre_estado_empleado) VALUES('Inactivo');

INSERT INTO tipo_solicitud(nombre_tipo_solicitud) VALUES('Nuevo sistema');
INSERT INTO tipo_solicitud(nombre_tipo_solicitud) VALUES('Nueva funcionalidad');
INSERT INTO tipo_solicitud(nombre_tipo_solicitud) VALUES('Corrección');

INSERT INTO estado_solicitud(nombre_estado_solicitud) VALUES('En espera de respuesta');
INSERT INTO estado_solicitud(nombre_estado_solicitud) VALUES('Solicitud rechazada');
INSERT INTO estado_solicitud(nombre_estado_solicitud) VALUES('Solicitud aprobada');

INSERT INTO rol(nombre_rol) VALUES('Administrador');
INSERT INTO rol(nombre_rol) VALUES('Jefe área funcional');
INSERT INTO rol(nombre_rol) VALUES('Empleado área funcional');
INSERT INTO rol(nombre_rol) VALUES('Jefe de desarrollo');
INSERT INTO rol(nombre_rol) VALUES('Programadores');

INSERT INTO departamento(id_departamento,nombre_departamento) VALUES('DFS','Finanzas');
INSERT INTO departamento(id_departamento,nombre_departamento) VALUES('DVS','Ventas');
INSERT INTO departamento(id_departamento,nombre_departamento) VALUES('DFF','Facturación fija');
INSERT INTO departamento(id_departamento,nombre_departamento) VALUES('DFM','Facturación móvil');

INSERT INTO estado_caso(nombre_estado_caso) VALUES('En desarrollo');
INSERT INTO estado_caso(nombre_estado_caso) VALUES('Devuelto con observaciones');
INSERT INTO estado_caso(nombre_estado_caso) VALUES('Esperando aprobación del área solicitante');
INSERT INTO estado_caso(nombre_estado_caso) VALUES('Vencido');
INSERT INTO estado_caso(nombre_estado_caso) VALUES('Finalizado');

/*
    usuario_empleado,password_empleado */
/*Admin*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Daphne Newman',null,null,1,1,'D0001','D0001');

/*Finanzas*/
/*jefe de departamento*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Zoe Kline','DFS',null,2,2,'Z0002','Z0002');
/*Empleados*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Daquan Merrill','DFS',2,3,1,'D0003','D0003');
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Ashely Ward','DFS',2,3,1,'A0004','A0004');
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Baker Leonard','DFS',2,3,1,'B0005','B0005');
/*Jede de desarrollo*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Walker Contreras','DFS',null,4,2,'W0006','W0006');
/*Programadores*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Duncan Norris','DFS',6,5,1,'D0007','D0007');
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Prescott Hess','DFS',6,5,1,'P0008','P0008');
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Connor Hensley','DFS',6,5,1,'C0009','C0009');

/*Ventas*/
/*jefe de departamento*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Alfreda Molina','DVS',null,2,2,'A0010','A0010');
/*Empleados*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Graham Bush','DVS',10,3,1,'G0011','G0011');
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Dora Ashley','DVS',10,3,1,'D0012','D0012');
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Chadwick Zimmerman','DVS',10,3,1,'C0013','C0013');
/*Jede de desarrollo*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Steven Mendez','DVS',null,4,2,'S0014','S0014');
/*Programadores*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Larissa Owens','DVS',14,5,1,'L0015','L0015');
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Chelsea Porter','DVS',14,5,1,'C0016','C0016');
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Garth Marshall','DVS',14,5,1,'G0017','G0017');

/*Facturación fija*/
/*jefe de departamento*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Jana Long','DFF',null,2,2,'J0018','J0018');
/*Empleados*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Paki Wright','DFF',18,3,1,'P0019','P0019');
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Juliet Mclean','DFF',18,3,1,'J0020','J0020');
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Wallace Osborn','DFF',18,3,1,'W0021','W0021');
/*Jede de desarrollo*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Orli Fox','DFF',null,4,2,'O0022','O0022');
/*Programadores*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Gavin Burnett','DFF',22,5,1,'G0023','G0023');
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Chelsea Lindsey','DFF',22,5,1,'C0024','C0024');
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Hillary Hodges','DFF',22,5,1,'H0025','H0025');

/*Facturación móvil*/
/*jefe de departamento*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Lesley Whitehead','DFM',null,2,2,'L0026','L0026');
/*Empleados*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Doris Savage','DFM',26,3,1,'D0027','D0027');
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Byron Kelley','DFM',26,3,1,'B0028','B0028');
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Caryn Saunders','DFM',26,3,1,'C0029','C0029');
/*Jede de desarrollo*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Liberty Fisher','DFM',null,4,2,'L0030','L0030');
/*Programadores*/
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Christine Vasquez','DFM',30,5,1,'C0031','C0031');
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Oliver Blackwell','DFM',30,5,1,'O0032','O0032');
INSERT INTO empleado (nombre_empleado,id_departamento,id_jefe,id_rol,id_estado_empleado,usuario_empleado,password_empleado) 
VALUES('Kylie Aguirre','DFM',30,5,1,'K0033','K0033');


SELECT 
    e.id_empleado AS 'ID',
    e.nombre_empleado AS 'Empleado',
    r.nombre_rol AS 'Rol',
    d.nombre_departamento AS 'Departamento',
    j.nombre_empleado AS 'Jefe',
    ee.nombre_estado_empleado AS 'Estado'
FROM
    empleado e
        LEFT JOIN
    empleado j ON e.id_jefe = j.id_empleado
        LEFT JOIN
    rol AS r ON e.id_rol = r.id_rol
        LEFT JOIN
    departamento d ON e.id_departamento = d.id_departamento
		left join
    estado_empleado ee ON ee.id_estado_empleado = e.id_estado_empleado;