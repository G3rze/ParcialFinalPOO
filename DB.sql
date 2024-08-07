drop database ParcialFinal
create database ParcialFinal;
use ParcialFinal;

create table Tarjeta(
id int primary key auto_increment not null,
numeroTarjeta varchar(25),
fechaExpiracion Date,
tipoTarjeta varchar(1),
facilitadorTarjeta varchar(9),
id_cliente int,
index(id_cliente),
foreign key (id_cliente) references Cliente (id) on delete cascade on update cascade
);

create table Compra(
id int primary key auto_increment not null,
fechaCompra Date,
montoTotal decimal (10,2),
descripcion varchar(40),
id_tarjeta int,
index(id_tarjeta),
foreign key (id_tarjeta) references Tarjeta(id) on delete cascade on update cascade
);

create table Cliente (
id int primary key auto_increment not null,
nombreCompleto varchar(60),
direccion varchar(40),
telefono varchar(9)
);
