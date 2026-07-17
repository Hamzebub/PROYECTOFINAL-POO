--drop database DB_ProyectoFM
CREATE DATABASE DB_ProyectoFM
go

use DB_ProyectoFM
go

CREATE TABLE TipoDocumento(
TipoDocumento_Id int primary key,
DocumentoDetalle varchar(50),
Abreviatura varchar(50),
Defecto int
)

INSERT INTO TipoDocumento (TipoDocumento_Id,DocumentoDetalle,Abreviatura,Defecto) VALUES 
(1,'OTROS TIPOS DE DOCUMENTOS','OTROS',0)
,(2,'DOCUMENTO NACIONAL DE IDENTIDAD (DNI)','DNI',1)
,(3,'CARNET DE EXTRANJERIA','C.EXT.',0)
,(4,'REGISTRO ÚNICO DE CONTRIBUYENTES','RUC',0)
,(5,'PASAPORTE','PASAPORTE',0)
go

CREATE TABLE Usuario(
    Usuario_Id int PRIMARY KEY identity(1,1),
    TipoDocumento_Id int references TipoDocumento,
    NumeroDocumento varchar(20),
    Nombre VARCHAR(100) NOT NULL,
    Telefono VARCHAR(20),
    Usuario VARCHAR(50) NOT NULL UNIQUE,
    Clave VARCHAR(255) NOT NULL,
    Rol VARCHAR(50) NOT NULL,
    Activo int
)
insert Usuario(TipoDocumento_Id,NumeroDocumento,Nombre,Usuario,Clave,Rol,Activo) 
values(1,'--','Admin','Admin','1234','Administrador',1)
go

CREATE TABLE Cliente(
    Cliente_Id int PRIMARY KEY identity(1,1),
    TipoDocumento_Id int references TipoDocumento,
    NumeroDocumento varchar(20) ,
    Nombre VARCHAR(100) NOT NULL,
    Telefono VARCHAR(20),
    PuntosAcumulados INT NOT NULL DEFAULT 0,
    Activo int
)
go
insert Cliente(TipoDocumento_Id,NumeroDocumento,Nombre,Activo) 
values(1,'--','Cliente varios',1)
go


CREATE TABLE Laboratorio(
Laboratorio_Id int identity primary key,
Laboratorio_Detalle varchar(150),
Activo int
)
insert  Laboratorio(Laboratorio_Detalle,Activo)values ('--',1)

CREATE TABLE Marca(
Marca_Id int identity primary key,
Marca_Detalle varchar(150),
Activo int
)
insert  Marca(Marca_Detalle,Activo)values ('--',1)

CREATE TABLE Categoria(
Categoria_Id int identity primary key,
Categoria_Detalle varchar(150),
Activo int
)
insert  Categoria(Categoria_Detalle,Activo)values ('--',1)
go

CREATE TABLE Producto(
Producto_Id INT IDENTITY PRIMARY KEY,
Codigo VARCHAR(20) UNIQUE,
Nombre VARCHAR(250) NOT NULL,
Precio DECIMAL(10,2) NOT NULL,
Stock INT NOT NULL,
FechaVencimiento DATE NULL,
Laboratorio_Id int references Laboratorio,
Marca_Id int references Marca,
Categoria_Id int references Categoria,
Activo int
);

CREATE TABLE TipoDocumentoVenta(
TipoDocumentoVenta_Id int PRIMARY KEY,
DocumentoVenta varchar(100),
Indicador varchar(4),
)
insert TipoDocumentoVenta values(1,'Boleta','B001')
insert TipoDocumentoVenta values(2,'Factura','F001')

go
CREATE TABLE Venta(
Venta_Id int IDENTITY PRIMARY KEY,
Serie varchar(4),
Correlativo int,
FechaCompleta datetime,
TipoDocumentoVenta_Id int references TipoDocumentoVenta,
Total decimal(9,2),
Cliente_Id int references Cliente, 
Usuario_Id int references Usuario,
Estado int, --activo - anulado
Activo int
)

CREATE TABLE VentaDetalle(
VentaDetalle_Id int IDENTITY PRIMARY KEY, 
Venta_Id int,
Producto_Id int, 
Cantidad int,
PrecioUnitario decimal(9,2),
Subtotal decimal(9,2),
Activo int
)
go
