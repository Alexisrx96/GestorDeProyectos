
USE gestiondeproyectos;

INSERT INTO solicitud
(id_estado_solicitud,
id_departamento,
id_tipo_solicitud,
descripcion_solicitud,
direccion_pdf_solicitud)
VALUES
(1,'DFS',1,'11Nuevo sistema de finanzas interno',null),
(1,'DFS',2,'12Nuevo sistema de finanzas interno',null),
(1,'DFS',3,'13Nuevo sistema de finanzas interno',null),
(2,'DFS',1,'21Nuevo sistema de finanzas interno',null),
(2,'DFS',2,'22Nuevo sistema de finanzas interno',null),
(2,'DFS',3,'23Nuevo sistema de finanzas interno',null),
(3,'DFS',1,'31Nuevo sistema de finanzas interno',null),
(3,'DFS',2,'32Nuevo sistema de finanzas interno',null),
(3,'DFS',3,'33Nuevo sistema de finanzas interno',null),
(1,'DFS',1,'Nuevo sistema de finanzas interno',null),
(1,'DFS',1,'Nuevo sistema de finanzas otra áreas',null),
(1,'DVS',1,'Nuevo sistema de ventas',null);

INSERT INTO caso
(id_solicitud,
id_caso,
id_programador,
id_probador,
id_estado_caso,
fecha_inicio,
fecha_entrega,
fecha_puesta_produccion,
requerimentos_caso)
VALUES
(7,'DFS21001',7,3,5,CURDATE()-10,CURDATE()-7,null,"Una cosa con cosas para realizar cosas"),
(8,'DFS21002',8,4,1,CURDATE()-10,CURDATE()-7,null,"Una cosa con cosas para realizar cosas 2"),
(9,'DFS21003',9,5,1,CURDATE()-10,CURDATE()+7,null,"Una cosa con cosas para realizar cosas 3");



INSERT INTO razon_rechazo_solicitud
(id_solicitud,
descripcion_razon_rechazo_solicitud)
VALUES
(4,"No me gustó"),
(5,"No me gustó"),
(6,"No me gustó");


INSERT INTO bitacora
(id_caso,
porcentaje,
fecha_avance,
descripcion_avance)
VALUES
('DFS21001',10,CURDATE(),'Se hicieron cosas'),
('DFS21001',20,CURDATE(),'Se hicieron cosas'),
('DFS21001',30,CURDATE(),'Se hicieron cosas'),
('DFS21001',40,CURDATE(),'Se hicieron cosas'),
('DFS21001',50,CURDATE(),'Se hicieron cosas');


INSERT INTO bitacora
(id_caso,
porcentaje,
fecha_avance,
descripcion_avance)
VALUES
('DFS21001',100,CURDATE(),'Se hicieron cosas y se terminó'),
('DFS21001',60,CURDATE()+1,'Se Corrigieron cosas'),
('DFS21001',80,CURDATE()+1,'Se Corrigieron cosas'),
('DFS21001',100,CURDATE()+1,'Se Corrigieron cosas y se terminó');


INSERT INTO observacion_caso
(id_caso,
fecha_observacion_caso,
descripcion,
direccion_pdf_observacion_caso)
VALUES
("DFS21001",
curdate(),
"Hay varios errores",
null);

UPDATE caso SET id_estado_caso = 4 where fecha_entrega < curdate() and id_estado_caso between 1 and 2;
select * from solicitud where id_solicitud between 1 and 2;
select * from empleado; 
select * from estado_empleado;
select * from empleado where id_departamento = 'DFS' ; 
select * from  (select id_departamento, count(*)as rep  from empleado group by id_departamento) m where m.rep > 1;
select * from caso;
update caso set id_estado_caso = 5 where id_caso = 'DFS21001';
select * from solicitud where id_estado_solicitud = 2;
select * from observacion_caso;
select porcentaje,fecha_avance,descripcion_avance from bitacora;
select * from bitacora where id_caso = 'DFS21001'
order by fecha_avance desc;